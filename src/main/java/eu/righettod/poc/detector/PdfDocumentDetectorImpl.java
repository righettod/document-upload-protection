package eu.righettod.poc.detector;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;

/**
 * Implementation of the detector for Adobe PDF document.
 * 
 *
 */
public class PdfDocumentDetectorImpl implements DocumentDetector {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(PdfDocumentDetectorImpl.class);

	/**
	 * {@inheritDoc}
	 *
	 * @see eu.righettod.poc.detector.DocumentDetector#isSafe(java.io.File)
	 */
	@Override
	public boolean isSafe(File f) {
		boolean safeState = false;
		try {
			if ((f != null) && f.exists()) {
				// Load stream in PDF parser
				// If the stream is not a PDF then exception will be throwed
				// here and safe state will be set to FALSE
				PdfReader reader = new PdfReader(f.getAbsolutePath());
				// Check 1:
				// Detect if the document contains any JavaScript code
				String jsCode = reader.getJavaScript();
				if (jsCode == null) {
					// OK no JS code then when pass to check 2:
					// Detect if the document has any embedded files
					PdfDictionary root = reader.getCatalog();
					PdfDictionary names = root.getAsDict(PdfName.NAMES);
					PdfArray namesArray = null;
					if (names != null) {
						PdfDictionary embeddedFiles = names.getAsDict(PdfName.EMBEDDEDFILES);
						namesArray = embeddedFiles.getAsArray(PdfName.NAMES);
					}
					// Get safe state from number of embedded files
					safeState = ((namesArray == null) || namesArray.isEmpty());
				}
			}
		} catch (Exception e) {
			safeState = false;
			LOG.warn("Error during Pdf file analysis !", e);
		}
		return safeState;
	}

}
