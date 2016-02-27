package eu.righettod.poc.detector;

import java.io.File;

/**
 * Interface to define detection methods.
 *
 */
public interface DocumentDetector {

	/**
	 * Method to verify if the specified file contains a document that:<br>
	 * <ul>
	 * <li>Do not contains potential malicious content</li>
	 * <li>Is part of the supported accepted format</li>
	 * </ul>
	 * 
	 * @param f File to validate
	 * 
	 * @return TRUE only if the file fill the 2 rules above
	 */
	boolean isSafe(File f);

}
