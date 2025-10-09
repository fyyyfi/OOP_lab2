package postcard;

import org.junit.jupiter.api.Test;
import postcard.model.OldCard;
import postcard.parser.DomCardParser;
import postcard.parser.SaxCardParser;
import postcard.parser.StaxCardParser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserTest {

    private static final String XML_FILE_PATH = "src/main/resources/old_cards.xml";
    private static final int EXPECTED_CARDS_COUNT = 4;

    @Test
    void testDomParser() throws Exception {
  
        DomCardParser parser = new DomCardParser();
        
        List<OldCard> cards = parser.parse(XML_FILE_PATH);
          
        assertNotNull(cards, "Список не повинен бути null");
        
        assertEquals(EXPECTED_CARDS_COUNT, cards.size(), "Кількість розпарсених листівок не вірна");
 
        assertEquals("card001", cards.get(0).getId(), "ID першої листівки невірний");
        assertEquals(1985, cards.get(0).getYear(), "Рік першої листівки невірний");
    }

    @Test
    void testSaxParser() throws Exception {
        SaxCardParser parser = new SaxCardParser();
        List<OldCard> cards = parser.parse(XML_FILE_PATH);
        
        assertNotNull(cards);
        assertEquals(EXPECTED_CARDS_COUNT, cards.size());
    }

    @Test
    void testStaxParser() throws Exception {
        StaxCardParser parser = new StaxCardParser();
        List<OldCard> cards = parser.parse(XML_FILE_PATH);
        
        assertNotNull(cards);
        assertEquals(EXPECTED_CARDS_COUNT, cards.size());
    }
}