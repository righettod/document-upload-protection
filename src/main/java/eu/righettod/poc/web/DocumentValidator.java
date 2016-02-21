package eu.righettod.poc.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.righettod.poc.detector.Detector;
import eu.righettod.poc.detector.ExcelDetectorImpl;
import eu.righettod.poc.detector.PdfDetectorImpl;
import eu.righettod.poc.detector.WordDetectorImpl;

/**
 * Filter to ensure that the uploaded file is safe.
 *
 */
@WebFilter("/upload")
public class DocumentValidator implements Filter {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentValidator.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// not used
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		// not used
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResp = (HttpServletResponse) resp;
		HttpServletRequest httpReq = (HttpServletRequest) req;
		File tmp = null;
		Path p = null;

		try {
			// Retrieve the file content and the file type from multipart
			// incoming request
			// TODO using helper to parse multipart form
			byte[] content = null;
			String fileType = null;

			// Initialize a detector for the target file type
			Detector detector = null;
			switch (fileType) {
			case "PDF":
				detector = new PdfDetectorImpl();
				break;
			case "WORD":
				detector = new WordDetectorImpl();
				break;
			case "EXCEL":
				detector = new ExcelDetectorImpl();
				break;
			default:
				throw new IllegalArgumentException("Unknow file type specified !");
			}

			// Write a temporary file with uploaded file
			tmp = File.createTempFile("detector", null);
			p = tmp.toPath();
			Files.write(p, content, StandardOpenOption.CREATE);

			// Apply detection
			boolean isSafe = detector.isSafe(tmp);

			// Take decision based on safe status
			if (!isSafe) {
				LOG.warn("Detection of a unsafe file upload !");
				// Remove temporary file
				this.safelyRemoveTempFile(p);
				// Return error
				httpResp.sendError(HttpServletResponse.SC_FORBIDDEN);
			} else {
				// Pass temporary file information to target
				// in order that it work with it
				httpReq.setAttribute("FILE_SAFE_STATUS", "OK");
				httpReq.setAttribute("FILE_REF", p);
				httpReq.setAttribute("FILE_TYPE", Files.probeContentType(p));
				httpReq.setAttribute("FILE_ORIGINAL_NAME", "");
				chain.doFilter(httpReq, httpResp);
			}

		} catch (Exception e) {
			// Remove temporary file
			this.safelyRemoveTempFile(p);
			// Log error
			LOG.error("Error during detection of file upload safe status !", e);
			// Return an access denied to stay consistent from a client point of
			// view (not discrepancy on response)
			httpResp.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	/**
	 * Utility methods to safely remove temp file
	 * 
	 * @param p
	 *            file to remove
	 */
	private void safelyRemoveTempFile(Path p) {
		try {
			if (p != null) {
				// Remove temporary file
				if (!Files.deleteIfExists(p)) {
					// If remove fail then overrite content to sanitize it
					Files.write(p, "-".getBytes("utf8"), StandardOpenOption.CREATE);
				}
			}
		} catch (Exception e) {
			LOG.warn("Cannot safely remove file !", e);
		}

	}
}
