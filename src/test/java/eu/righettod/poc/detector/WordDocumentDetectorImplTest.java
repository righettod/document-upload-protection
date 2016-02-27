package eu.righettod.poc.detector;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for Word detector implementation.
 *
 */
public class WordDocumentDetectorImplTest {

	/** Home directory of the Word samples */
	private static final File SAMPLES_DIRECTORY = new File("src/test/resources/word");

	/** Tested class instance */
	private DocumentDetector victim = new WordDocumentDetectorImpl();

	/**
	 * Test case for DOC format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeDOCDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.doc");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for DOCM format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeDOCMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.docm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for Word ML (XML) format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeWordMLDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro-word2003xml.xml");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for MHTML format that we always reject.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeMHTMLDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.mhtml");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOT format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeDOTDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.dot");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for DOTM format without macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testSafeDOTMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.dotm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertTrue(safeState);
	}

	/**
	 * Test case for MHTML format that we always reject.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeMHTMLDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.mhtml");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOC format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.doc");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOCM format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.docm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for Word ML (XML) format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeWordMLDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro-word2003xml.xml");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOCM format with macro renamed to DOC.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCMDocumentRenamedToDOC() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro-docm-renamed-to-doc.doc");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOT format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOTDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.dot");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOTM format with macro.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOTMDocument() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.dotm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOC format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.doc");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOCM format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCMDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.docm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOCX format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOCXDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.docx");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOT format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOTDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dot");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOTM format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOTMDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dotm");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for DOTX format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeDOTXDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dotx");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for MTHML format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeMTHMLDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.mht");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}

	/**
	 * Test case for WordML format with OLE object.
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUnSafeWordMLDocumentWithOLE() throws IOException {
		// Prepare test
		File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object-word2003xml.xml");
		// Run test
		boolean safeState = this.victim.isSafe(sample);
		// Validate test
		Assert.assertFalse(safeState);
	}
}
