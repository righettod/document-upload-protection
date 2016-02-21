package eu.righettod.poc.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Simple servlet to display infos about uploaded document.
 *
 */
@SuppressWarnings("serial")
@WebServlet("/upload")
public class DocumentInfos extends HttpServlet {

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");

		// Display received infos from FILTER...
		resp.getWriter().printf("SAFE STATUS        : %s\n", req.getAttribute("FILE_SAFE_STATUS"));
		resp.getWriter().printf("FILE REFERENCE     : %s\n", req.getAttribute("FILE_REF"));
		resp.getWriter().printf("FILE TYPE          : %s\n", req.getAttribute("FILE_TYPE"));
		resp.getWriter().printf("FILE ORIGINAL NAME : %s\n", req.getAttribute("FILE_ORIGINAL_NAME"));

		// Display SHA256 of temporary file in order to validate the consistency
		try {
			MessageDigest d = MessageDigest.getInstance("SHA256");
			Path p = (Path) req.getAttribute("FILE_REF");
			String hash = DatatypeConverter.printHexBinary(d.digest(Files.readAllBytes(p)));
			resp.getWriter().printf("FILE SHA256    : %s\n", hash);
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException(e);
		}

	}

}
