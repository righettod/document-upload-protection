package eu.righettod.poc.detector;

import com.aspose.slides.IOleObjectFrame;
import com.aspose.slides.IShape;
import com.aspose.slides.ISlide;
import com.aspose.slides.Presentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Implementation of the detector for Microsoft Powerpoint document.
 *
 *
 */
public class PowerpointDocumentDetectorImpl implements DocumentDetector {

    /**
     * LOGGER
     */
    private static final Logger LOG = LoggerFactory.getLogger(PowerpointDocumentDetectorImpl.class);

    /**
     * {@inheritDoc}
     *
     * @see eu.righettod.poc.detector.DocumentDetector#isSafe(java.io.File)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean isSafe(File f) {
        boolean safeState = false;
        try {
            if ((f != null) && f.exists() && f.canRead()) {
                // Load the file into the Powerpoint document parser
                Presentation presentation = new Presentation(f.getAbsolutePath());
                // First check on Powerpoint format skipped because:
                // FileFormatInfo class is not provided for Aspose Slides API
                // PresentationFactory.getInstance().getPresentationInfo() can be used but the LoadFormat class miss format like POT or PPT XML
                //Aspose API do not support PPT XML format
                // Get safe state from presence of a VBA project in the presentation
                safeState = (presentation.getVbaProject() == null);
                // If presentation is safe then we pass to OLE objects analysis
                if (safeState) {
                    //Parse all slides of the presentation
                    int totalOLEObjectCount = 0;
                    for (ISlide slide : presentation.getSlides()) {
                        for (IShape shape : slide.getShapes()) {
                            //Check if the current shape is an OLE object
                            if (shape instanceof IOleObjectFrame) {
                                totalOLEObjectCount++;
                            }
                        }
                    }
                    // Update safe status flag according to number of OLE object found
                    if (totalOLEObjectCount != 0) {
                        safeState = false;
                    }
                }

            }
        } catch (Exception e) {
            safeState = false;
            LOG.warn("Error during Powerpoint file analysis !", e);
        }
        return safeState;
    }
}
