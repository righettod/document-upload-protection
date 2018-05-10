package eu.righettod.poc.detector;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Test cases for Word detector implementation.
 */
public class WordDocumentDetectorImplTest {

    /**
     * Home directory of the Word samples
     */
    private static final File SAMPLES_DIRECTORY = new File("src/test/resources/word");

    /**
     * Tested class instance
     */
    private DocumentDetector victim = new WordDocumentDetectorImpl();

    /**
     * Test case for DOC format without macro.
     */
    @Test
    public void testSafeDOCDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.doc");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for DOCM format without macro.
     */
    @Test
    public void testSafeDOCMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.docm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for Word ML (XML) format without macro.
     */
    @Test
    public void testSafeWordMLDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro-word2003xml.xml");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for MHTML format that we always reject.
     */
    @Test
    public void testSafeMHTMLDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.mhtml");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOT format without macro.
     */
    @Test
    public void testSafeDOTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.dot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for DOTM format without macro.
     */
    @Test
    public void testSafeDOTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.dotm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for MHTML format that we always reject.
     */
    @Test
    public void testUnSafeMHTMLDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.mhtml");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOC format with macro.
     */
    @Test
    public void testUnSafeDOCDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.doc");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOCM format with macro.
     */
    @Test
    public void testUnSafeDOCMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.docm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for Word ML (XML) format with macro.
     */
    @Test
    public void testUnSafeWordMLDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro-word2003xml.xml");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOCM format with macro renamed to DOC.
     */
    @Test
    public void testUnSafeDOCMDocumentRenamedToDOC() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro-docm-renamed-to-doc.doc");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOT format with macro.
     */
    @Test
    public void testUnSafeDOTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.dot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOTM format with macro.
     */
    @Test
    public void testUnSafeDOTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.dotm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOC format with OLE object.
     */
    @Test
    public void testUnSafeDOCDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.doc");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOCM format with OLE object.
     */
    @Test
    public void testUnSafeDOCMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.docm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOCX format with OLE object.
     */
    @Test
    public void testUnSafeDOCXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.docx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOT format with OLE object.
     */
    @Test
    public void testUnSafeDOTDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOTM format with OLE object.
     */
    @Test
    public void testUnSafeDOTMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dotm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for DOTX format with OLE object.
     */
    @Test
    public void testUnSafeDOTXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.dotx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for MTHML format with OLE object.
     */
    @Test
    public void testUnSafeMTHMLDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.doctest-with-ole-object.mht");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for WordML format with OLE object.
     */
    @Test
    public void testUnSafeWordMLDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object-word2003xml.xml");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }
}
