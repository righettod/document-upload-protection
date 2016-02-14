package eu.righettod.poc.detector;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.aspose.cells.FileFormatInfo;
import com.aspose.cells.FileFormatUtil;
import com.aspose.cells.Workbook;

/**
 * Implementation of the detector for Microsoft Excel workbook.
 * 
 *
 */
public class ExcelDetectorImpl implements Detector {

	/**
	 * List of allowed Excel format<br>
	 * Allow also XLSM/XSLB because both can exists without macro inside.<br>
	 * Allow also XLT/XLTM because both can exists without macro inside.<br>
	 */
	private static final List<String> ALLOWED_FORMAT = Arrays.asList(new String[] { "xls", "xlsx", "xlsm", "xlsb", "xlt", "xltm" });

	/**
	 * {@inheritDoc}
	 *
	 * @see eu.righettod.poc.detector.Detector#isSafe(java.io.File)
	 */
	@Override
	public boolean isSafe(File f) {
		boolean safeState = false;
		try {
			if ((f != null) && f.exists() && f.canRead()) {
				// Perform a first check on Excel document format
				FileFormatInfo formatInfo = FileFormatUtil.detectFileFormat(f.getAbsolutePath());
				String formatExtension = FileFormatUtil.loadFormatToExtension(formatInfo.getLoadFormat());
				if ((formatExtension != null) && ALLOWED_FORMAT.contains(formatExtension.toLowerCase(Locale.US).replaceAll("\\.", ""))) {
					// Load the file into the Excel document parser
					Workbook book = new Workbook(f.getAbsolutePath());
					// Get safe state from Macro presence
					safeState = !book.hasMacro();
				}
			}
		}
		catch (Exception e) {
			safeState = false;
			// Not clean way of logging but it's a POC :)
			e.printStackTrace();
		}
		return safeState;
	}

}
