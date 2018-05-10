package eu.righettod.poc.detector;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Test cases for Powerpoint detector implementation.
 */
public class PowerpointDocumentDetectorImplTest {

    /**
     * Home directory of the Powerpoint samples
     */
    private static final File SAMPLES_DIRECTORY = new File("src/test/resources/powerpoint");

    /**
     * Tested class instance
     */
    private DocumentDetector victim = new PowerpointDocumentDetectorImpl();

    /**
     * Test case for POT format without macro.
     */
    @Test
    public void testSafePOTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.pot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for POTM format without macro.
     */
    @Test
    public void testSafePOTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.potm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for POTX format without macro.
     */
    @Test
    public void testSafePOTXDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.potx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPS format without macro.
     */
    @Test
    public void testSafePPSDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.pps");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPSX format without macro.
     */
    @Test
    public void testSafePPSXDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.ppsx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPSM format without macro.
     */
    @Test
    public void testSafePPSMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.ppsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPT format without macro.
     */
    @Test
    public void testSafePPTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.ppt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPTM format without macro.
     */
    @Test
    public void testSafePPTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.pptm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for PPTX format without macro.
     */
    @Test
    public void testSafePPTXDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.pptx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }


    /**
     * Test case for POT format with macro.
     */
    @Test
    public void testUnSafePOTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.pot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for POTM format with macro.
     */
    @Test
    public void testUnSafePOTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.potm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPS format with macro.
     */
    @Test
    public void testUnSafePPSDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.pps");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPSM format with macro.
     */
    @Test
    public void testUnSafePPSMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.ppsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPT format with macro.
     */
    @Test
    public void testUnSafePPTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.ppt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPTM format with macro.
     */
    @Test
    public void testUnSafePPTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.pptm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }


    /**
     * Test case for POT format with OLE object.
     */
    @Test
    public void testUnSafePOTDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.pot");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for POTM format with OLE object.
     */
    @Test
    public void testUnSafePOTMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.potm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for POTX format with OLE object.
     */
    @Test
    public void testUnSafePOTXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.potx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPS format with OLE object.
     */
    @Test
    public void testUnSafePPSDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.pps");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPSM format with OLE object.
     */
    @Test
    public void testUnSafePPSMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.ppsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPSX format with OLE object.
     */
    @Test
    public void testUnSafePPSXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.ppsx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPT format with OLE object.
     */
    @Test
    public void testUnSafePPTDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.ppt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPTM format with OLE object.
     */
    @Test
    public void testUnSafePPTMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.pptm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for PPTX format with OLE object.
     */
    @Test
    public void testUnSafePPTXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.pptx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }


}
