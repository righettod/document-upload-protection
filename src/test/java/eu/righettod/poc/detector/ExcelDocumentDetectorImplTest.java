package eu.righettod.poc.detector;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for Excel detector implementation.
 *
 */
public class ExcelDocumentDetectorImplTest {

	/** Home directory of the Excel samples */
	private static final File SAMPLES_DIRECTORY = new File("src/test/resources/excel");

	/** Tested class instance */
	private DocumentDetector victim = new ExcelDocumentDetectorImpl();

	/**
	 * Test case for XLS format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeXLSDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xls");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for XLSB format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeXLSBDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlsb");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for XLSM format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeXLSMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlsm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for XLT format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeXLTDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlt");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for XLTM format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeXLTMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xltm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for XLS format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeXLSDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xls");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for XLSB format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeXLSBDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlsb");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for XLSM format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeXLSMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlsm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for XLT format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeXLTDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlt");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for XLTM format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeXLTMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xltm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

}
