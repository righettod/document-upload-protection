package eu.righettod.poc.detector;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aspose.words.Document;
import com.aspose.words.FileFormatInfo;
import com.aspose.words.FileFormatUtil;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Shape;

/**
 * Implementation of the detector for Microsoft Word document.
 * 
 *
 */
public class WordDocumentDetectorImpl implements DocumentDetector {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(WordDocumentDetectorImpl.class);

	/**
	 * List of allowed Word format (WML = Word ML (Word 2003 XML)).<br>
	 * Allow also DOCM because it can exists without macro inside.<br>
	 * Allow also DOT/DOTM because both can exists without macro inside.<br>
	 * We reject MHTML file because:<br>
	 * <ul>
	 * <li>API cannot detect macro into this format</li>
	 * <li>Is not normal to use this format to represent a Word file (there plenty of others supported format)</li>
	 * </ul>
	 */
	private static final List<String> ALLOWED_FORMAT = Arrays.asList(new String[] { "doc", "docx", "docm", "wml", "dot", "dotm" });

	/**
	 * {@inheritDoc}
	 *
	 * @see eu.righettod.poc.detector.DocumentDetector#isSafe(java.io.File)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isSafe(File f) {
		boolean safeState = false;
		try {
			if ((f != null) && f.exists() && f.canRead()) {
				// Perform a first check on Word document format
				FileFormatInfo formatInfo = FileFormatUtil.detectFileFormat(f.getAbsolutePath());
				String formatExtension = FileFormatUtil.loadFormatToExtension(formatInfo.getLoadFormat());
				if ((formatExtension != null) && ALLOWED_FORMAT.contains(formatExtension.toLowerCase(Locale.US).replaceAll("\\.", ""))) {
					// Load the file into the Word document parser
					Document document = new Document(f.getAbsolutePath());
					// Get safe state from Macro presence
					safeState = !document.hasMacros();
					// If document is safe then we pass to OLE objects analysis
					if (safeState) {
						// Get all shapes of the document
						NodeCollection shapes = document.getChildNodes(NodeType.SHAPE, true);
						Shape shape = null;
						// Search OLE objects in all shapes
						int totalOLEObjectCount = 0;
						for (int i = 0; i < shapes.getCount(); i++) {
							shape = (Shape) shapes.get(i);
							// Check if the current shape has OLE object
							if (shape.getOleFormat() != null) {
								totalOLEObjectCount++;
							}
						}
						// Update safe status flag according to number of OLE object found
						if (totalOLEObjectCount != 0) {
							safeState = false;
						}

					}
				}
			}
		}
		catch (Exception e) {
			safeState = false;
			LOG.warn("Error during Word file analysis !", e);
		}
		return safeState;
	}

}
