package eu.righettod.poc.sanitizer;

import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.process.ImageProcessor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the sanitizer for Image file.
 * 
 * @see "http://stackoverflow.com/a/24747085"
 *
 */
public class ImageDocumentSanitizerImpl implements DocumentSanitizer {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(ImageDocumentSanitizerImpl.class);

	/**
	 * {@inheritDoc}
	 *
	 * @see eu.righettod.poc.sanitizer.DocumentSanitizer#madeSafe(java.io.File)
	 */
	@Override
	public boolean madeSafe(File f) {
		boolean safeState = false;
		try {
			if ((f != null) && f.exists() && f.canRead() && f.canWrite()) {
				// Load image
				ImagePlus image = new Opener().openImage(f.getAbsolutePath());

				// Check that image has been successfully loaded
				if (image == null) {
					throw new Exception("Cannot load the original image !");
				}

				// Get current Width and Height of the image
				int originalWidth = image.getWidth();
				int originalHeight = image.getHeight();

				// Obtain an Image processor on this image
				ImageProcessor originalImageProcessor = image.getProcessor();
				if (originalImageProcessor == null) {
					throw new Exception("Cannot obtains an image processor for the original image !");
				}

				// Resize the image by removing 1px on Width and Height
				ImageProcessor resizedImageProcessor = originalImageProcessor.resize(originalWidth - 1, originalHeight - 1);
				if (resizedImageProcessor == null) {
					throw new Exception("Cannot resize the original image !");
				}

				// Resize the resized image by adding 1px on Width and Height
				// In fact set image to is initial size
				ImageProcessor initialSizedImageProcessor = resizedImageProcessor.resize(originalWidth, originalHeight);
				if (initialSizedImageProcessor == null) {
					throw new Exception("Cannot restore the initial size of the original image !");
				}

				// Save image and detect the image format for provided file
				String imageFormat = Opener.getFileFormat(f.getAbsolutePath());
				ImagePlus finalImg = new ImagePlus("", initialSizedImageProcessor);
				IJ.saveAs(finalImg, imageFormat, f.getAbsolutePath());

				// IJ will save the file with the extension associated to the image format (ex: jpg or png)
				// but, as the provided input file can have any extension (we do not use it to detect image format),
				// then we must manage the case in which 2 files exists at this point:
				// 1) The input file provided (ex: myfile.tmp)
				// 2) The new file saved by IJ (ex: myfile.png)
				String tmp = f.getName();
				String newSavedFileName = tmp.substring(0, tmp.lastIndexOf(".") + 1) + imageFormat;
				File newSavedFile = new File(f.getParentFile(), newSavedFileName);
				if (newSavedFile.exists() && !f.getAbsolutePath().equalsIgnoreCase(newSavedFile.getAbsolutePath())) {
					// Overwrite content of the input file with the content of the new saved file
					Files.copy(newSavedFile.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
					// Remove file saved by IJ
					newSavedFile.delete();
				}

				// Set state flag
				safeState = true;
			}

		}
		catch (Exception e) {
			safeState = false;
			LOG.warn("Error during Image file processing !", e);
		}

		return safeState;
	}

}
