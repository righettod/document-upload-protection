package eu.righettod.poc.sanitizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

/**
 * Test cases for image sanitizer implementation.<br>
 * Use ".tmp" extension for working sample in order to ensure that the image API do not use the file extension to detect image format.
 *
 */
@SuppressWarnings("static-method")
public class ImageDocumentSanitizerImplTest {

	/** Home directory of the Images samples */
	private static final File SAMPLES_DIRECTORY = new File("src/test/resources/image");

	/** Working directory */
	private static final File WORKING_DIRECTORY = new File("target/work");

	/** Tested class instance */
	private DocumentSanitizer victim = new ImageDocumentSanitizerImpl();

	/**
	 * Initialize workspace before a test case execution
	 * 
	 * @throws IOException
	 */
	@Before
	public void initTest() throws IOException {
		if (WORKING_DIRECTORY.exists()) {
			WORKING_DIRECTORY.delete();
		}
		WORKING_DIRECTORY.mkdirs();
	}

	/**
	 * Test case for executable renamed as image<br>
	 * Here program cannot make it safe.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testExeRenamedAsImg() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-exe-renamed-as-img.png");
		File workingSample = new File(WORKING_DIRECTORY, "test-exe-renamed-as-img.tmp");
		Files.copy(sample.toPath(), workingSample.toPath(), StandardCopyOption.REPLACE_EXISTING);
		// Run test
		boolean safeState = this.victim.madeSafe(workingSample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for JPEG image containing code into one of his EXIF tags.<br>
	 * Here program must successfully make it safe.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testJpegWithCodeInEXIFTag() throws Exception {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-php-inserted-into-exif.jpg");
		File workingSample = new File(WORKING_DIRECTORY, "test-php-inserted-into-exif.tmp");
		Files.copy(sample.toPath(), workingSample.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Assert.assertTrue(this.containsStringInEXIFTags(workingSample, "phpinfo()"));
		// Run test
		boolean safeState = this.victim.madeSafe(workingSample);
		// Validate test
		Assert.assertFalse(this.containsStringInEXIFTags(workingSample, "phpinfo()"));
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for TIFF image containing code into one of his EXIF tags.<br>
	 * Here program must successfully make it safe.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testTiffWithCodeInEXIFTag() throws Exception {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-php-inserted-into-exif.tif");
		File workingSample = new File(WORKING_DIRECTORY, "test-php-inserted-into-exif.tmp");
		Files.copy(sample.toPath(), workingSample.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Assert.assertTrue(this.containsStringInEXIFTags(workingSample, "phpinfo()"));
		// Run test
		boolean safeState = this.victim.madeSafe(workingSample);
		// Validate test
		Assert.assertFalse(this.containsStringInEXIFTags(workingSample, "phpinfo()"));
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for GIF image containing code into one his content.<br>
	 * Here program must successfully make it safe.
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testGifWithCodeInContent() throws Exception {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-php-inserted-into-img.gif");
		File workingSample = new File(WORKING_DIRECTORY, "test-php-inserted-into-img.tmp");
		Files.copy(sample.toPath(), workingSample.toPath(), StandardCopyOption.REPLACE_EXISTING);
		String sampleContent = new String(Files.readAllBytes(workingSample.toPath())).toLowerCase(Locale.US);
		Assert.assertTrue(sampleContent.contains("phpinfo()"));
		// Run test
		boolean safeState = this.victim.madeSafe(workingSample);
		// Validate test
		sampleContent = new String(Files.readAllBytes(workingSample.toPath())).toLowerCase(Locale.US);
		Assert.assertFalse(sampleContent.contains("phpinfo()"));
		Assert.assertTrue(safeState);
	}

	/**
	 * Utility method to search a string into all the EXIF tags of an image file.
	 * 
	 * @param image Source image file
	 * @param searched String searched
	 * @return TRUE only the string is present
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	private boolean containsStringInEXIFTags(File image, String searched) throws Exception {
		boolean isFound = false;
		Metadata metadata = ImageMetadataReader.readMetadata(image);
		String tagDesc = null;
		for (Directory dir : metadata.getDirectories()) {
			for (Tag tag : dir.getTags()) {
				tagDesc = (tag.getDescription() == null) ? "" : tag.getDescription().toLowerCase(Locale.US);
				if (tagDesc.contains(searched.toLowerCase(Locale.US))) {
					isFound = true;
					break;
				}
			}
		}
		return isFound;
	}
}
