package eu.righettod.poc.detector;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Test cases for Excel detector implementation.
 */
public class ExcelDocumentDetectorImplTest {

    /**
     * Home directory of the Excel samples
     */
    private static final File SAMPLES_DIRECTORY = new File("src/test/resources/excel");

    /**
     * Tested class instance
     */
    private DocumentDetector victim = new ExcelDocumentDetectorImpl();

    /**
     * Test case for XLS format without macro.
     */
    @Test
    public void testSafeXLSDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xls");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for XLSB format without macro.
     */
    @Test
    public void testSafeXLSBDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlsb");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for XLSM format without macro.
     */
    @Test
    public void testSafeXLSMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for XLT format without macro.
     */
    @Test
    public void testSafeXLTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xlt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for XLTM format without macro.
     */
    @Test
    public void testSafeXLTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-without-macro.xltm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertTrue(safeState);
    }

    /**
     * Test case for XLSM format with macro renamed to XLS.
     */
    @Test
    public void testUnSafeXLSMDocumentRenamedToXLS() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro-xlsm-renamed-to-xls.xls");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLS format with macro.
     */
    @Test
    public void testUnSafeXLSDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xls");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLSB format with macro.
     */
    @Test
    public void testUnSafeXLSBDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlsb");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLSM format with macro.
     */
    @Test
    public void testUnSafeXLSMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLT format with macro.
     */
    @Test
    public void testUnSafeXLTDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xlt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLTM format with macro.
     */
    @Test
    public void testUnSafeXLTMDocument() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-macro.xltm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLS format with OLE object.
     */
    @Test
    public void testUnSafeXLSDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xls");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLSB format with OLE object.
     */
    @Test
    public void testUnSafeXLSBDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xlsb");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLSM format with OLE object.
     */
    @Test
    public void testUnSafeXLSMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xlsm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLSX format with OLE object.
     */
    @Test
    public void testUnSafeXLSXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xlsx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLT format with OLE object.
     */
    @Test
    public void testUnSafeXLTDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xlt");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLTM format with OLE object.
     */
    @Test
    public void testUnSafeXLTMDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xltm");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

    /**
     * Test case for XLTX format with OLE object.
     */
    @Test
    public void testUnSafeXLTXDocumentWithOLE() {
        // Prepare test
        File sample = new File(SAMPLES_DIRECTORY, "test-with-ole-object.xltx");
        // Run test
        boolean safeState = this.victim.isSafe(sample);
        // Validate test
        Assert.assertFalse(safeState);
    }

}
