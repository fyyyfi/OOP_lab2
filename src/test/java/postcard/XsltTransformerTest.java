package postcard;

import org.junit.jupiter.api.Test;
import postcard.instr.XsltTransformer;

import java.io.File;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class XsltTransformerTest {

    private static final String XML = "src/main/resources/old_cards.xml";
    private static final String XSLT = "src/main/resources/old_cards.xsl";
    private static final String TEST_OUTPUT_FILE = "test_transformed_cards.xml";

    @Test
    void testTransformationCreatesFile() throws Exception {
        File outputFile = new File(TEST_OUTPUT_FILE);

        if (outputFile.exists()) {
            outputFile.delete();
        }

        XsltTransformer.transform(XML, XSLT, TEST_OUTPUT_FILE);

        assertTrue(outputFile.exists(), "Трансформер має створити вихідний файл.");

        outputFile.delete();
    }
}
