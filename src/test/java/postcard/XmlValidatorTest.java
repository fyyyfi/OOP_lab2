package postcard;

import org.junit.jupiter.api.Test;
import postcard.instr.XmlValidator;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XmlValidatorTest {

    private static final String VALID_XML = "src/main/resources/old_cards.xml";
    private static final String INVALID_XML = "src/test/resources/invalid_card.xml";
    private static final String XSD = "src/main/resources/old_cards.xsd";

    @Test
    void testValidatorWithValidFile() {
        boolean result = XmlValidator.validate(VALID_XML, XSD);
        assertTrue(result, "Валідний XML має проходити перевірку");
    }

    @Test
    void testValidatorWithInvalidFile() {
        boolean result = XmlValidator.validate(INVALID_XML, XSD);
        assertFalse(result, "Невалідний XML не має проходити перевірку");
    }
}