package eu.righettod.poc.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.righettod.poc.detector.DocumentDetector;
import eu.righettod.poc.detector.ExcelDocumentDetectorImpl;
import eu.righettod.poc.detector.PdfDocumentDetectorImpl;
import eu.righettod.poc.detector.WordDocumentDetectorImpl;
import eu.righettod.poc.sanitizer.DocumentSanitizer;
import eu.righettod.poc.sanitizer.ImageDocumentSanitizerImpl;

/**
 * Servlet to receive the uploaded file.<br>
 * For Excel/Word/Pdf document: It verify if the uploaded document is safe and if it's OK then continue processing...<br>
 * For Image: Try to sanitize the uploaded document and if it succeed to sanitize it then continue processing...<br>
 * Try to use, as much as possible, file upload feature provided by JEE >= 7
 *
 */
@SuppressWarnings({ "serial", "boxing" })
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class DocumentUpload extends HttpServlet {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentUpload.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File tmpFile = null;
		Path tmpPath = null;
		try {

			/* Step 1: Retrieve upload information (file type + file content) */
			// File type: Word / Excel / Pdf
			String fileType = req.getParameter("fileType") == null ? "" : req.getParameter("fileType");
			if ((fileType == null) || (fileType.trim().length() == 0)) {
				throw new IllegalArgumentException("Unknow file type specified !");
			}

			// File content
			Part filePart = req.getPart("fileContent");
			if ((filePart == null) || (filePart.getInputStream() == null)) {
				throw new IllegalArgumentException("Unknow file content specified !");
			}

			// Write a temporary file with uploaded file
			tmpFile = File.createTempFile("uploaded-", null);
			tmpPath = tmpFile.toPath();
			long copiedBytesCount = Files.copy(filePart.getInputStream(), tmpPath, StandardCopyOption.REPLACE_EXISTING);
			if (copiedBytesCount != filePart.getSize()) {
				throw new IOException(String.format("Error during stream copy to temporary disk (copied: %s / expected: %s !", copiedBytesCount, filePart.getSize()));
			}

			/* Step 2: Initialize a detector/sanitizer for the target file type and perform validation */
			// Init the safety state flag
			boolean isSafe = false;

			// Instanciate the dedicated detector/sanitizer implementation and apply detection/sanitizing
			DocumentDetector documentDetector = null;
			DocumentSanitizer documentSanitizer = null;
			switch (fileType) {
			case "PDF":
				documentDetector = new PdfDocumentDetectorImpl();
				isSafe = documentDetector.isSafe(tmpFile);
				break;
			case "WORD":
				documentDetector = new WordDocumentDetectorImpl();
				isSafe = documentDetector.isSafe(tmpFile);
				break;
			case "EXCEL":
				documentDetector = new ExcelDocumentDetectorImpl();
				isSafe = documentDetector.isSafe(tmpFile);
				break;
			case "IMAGE":
				documentSanitizer = new ImageDocumentSanitizerImpl();
				isSafe = documentSanitizer.madeSafe(tmpFile);
				break;
			default:
				throw new IllegalArgumentException("Unknow file type specified !");
			}

			/* Step 3 : Take decision based on sfa status detected */
			// Take action is the file is not safe
			if (!isSafe) {
				LOG.warn("Detection of a unsafe file upload or cannot sanitize uploaded document !");
				// Remove temporary file
				safelyRemoveFile(tmpPath);
				// Return error
				resp.sendError(HttpServletResponse.SC_FORBIDDEN);
			} else {
				// Here print file infos...
				resp.setStatus(HttpServletResponse.SC_OK);
				resp.setContentType("text/plain");
				// For submitted file name, if you are on a container using Servlet API 3.1 then you can use method
				// filePart.getSubmittedFileName() instead of my workaround method....
				resp.getWriter().printf("Submitted file name       : %s\n", extractSubmittedFileName(filePart));
				resp.getWriter().printf("Submitted file size       : %s\n", filePart.getSize());
				resp.getWriter().printf("Received temp file name   : %s\n", tmpFile.getName());
				resp.getWriter().printf("Received temp file path   : %s\n", tmpFile.getAbsolutePath());
				// Create a HASH of the file to check the integrity of the uploaded content
				byte[] content = Files.readAllBytes(tmpPath);
				MessageDigest digester = MessageDigest.getInstance("sha-256");
				byte[] hash = digester.digest(content);
				String hashHex = DatatypeConverter.printHexBinary(hash);
				resp.getWriter().printf("Received temp file SHA256 : %s\n", hashHex);
			}

		}
		catch (Exception e) {
			// Remove temporary file
			safelyRemoveFile(tmpPath);
			// Log error
			LOG.error("Error during detection of file upload safe status !", e);
			// Return an access denied to stay consistent from a client point of
			// view (not discrepancy on response)
			resp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	/**
	 * Utility methods to safely remove a file
	 * 
	 * @param p file to remove
	 */
	private static void safelyRemoveFile(Path p) {
		try {
			if (p != null) {
				// Remove temporary file
				if (!Files.deleteIfExists(p)) {
					// If remove fail then overwrite content to sanitize it
					Files.write(p, "-".getBytes("utf8"), StandardOpenOption.CREATE);
				}
			}
		}
		catch (Exception e) {
			LOG.warn("Cannot safely remove file !", e);
		}
	}

	/**
	 * Utility method (taken from Oracle site) to retrieve the original name of the submitted file.<br>
	 * Only useful when your container run version of Servlet API inferior to 3.1 <br>
	 * It's the case here because I use the Tomcat 7 Maven plugin and it use Servlet API version 3.0 <br>
	 * Unfortunately I haven't found any Maven plugin for Tomcat 8...
	 * 
	 * @see "https://tomcat.apache.org/whichversion.html"
	 * @see "https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html"
	 * @param part Multipart file part
	 * @return The file name or null if not found
	 * 
	 */
	private static String extractSubmittedFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
