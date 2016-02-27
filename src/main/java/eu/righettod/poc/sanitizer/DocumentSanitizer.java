package eu.righettod.poc.sanitizer;

import java.io.File;

/**
 * Interface to define sanitize methods.
 *
 */
public interface DocumentSanitizer {

	/**
	 * Method to try to (sanitize) disable any code contained into the specified file by using re-writing approach.
	 * 
	 * @param f File to made safe
	 * 
	 * @return TRUE only if the specified file has been successfully made safe.
	 */
	boolean madeSafe(File f);

}
