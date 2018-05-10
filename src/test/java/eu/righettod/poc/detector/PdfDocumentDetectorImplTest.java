package eu.righettod.poc.detector;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Test cases for Pdf detector implementation.
 */
public class PdfDocumentDetectorImplTest {

    /**
     * Home directory of the Pdf samples
     */
    private static final File SAMPLES_DIRECTORY = new File("src/test/resources/pdf");

    /**
     * Tested class instance
     */
    private DocumentDetector victim = new PdfDocumentDetectorImpl();

    /**
     * Test case for PDF format without JS and without attached files.
     */
    @Test
    public void testSafePDFDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-no-files-no-js.pdf");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PDF format with JS but without attached files.
     */
    @Test
    public void testUnSafeJSOnlyPDFDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-no-files-with-js.pdf");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PDF format without JS but with attached files.
     */
    @Test
    public void testUnSafeAttachedFilesOnlyPDFDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-files-no-js.pdf");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PDF format with JS and with attached files.
     */
    @Test
    public void testUnSafeAttachedFilesAndJSPDFDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-files-with-js.pdf");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

}
