package postcard.parser;

import postcard.model.OldCard;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SaxCardParser {

    
 
    public List<OldCard> parse(String xmlFilePath) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        
        CardHandler handler = new CardHandler();
        saxParser.parse(new File(xmlFilePath), handler);
        
        return handler.getCards();
    }

 
    private static class CardHandler extends DefaultHandler {
        private List<OldCard> cards = new ArrayList<>();
        private OldCard currentCard;
        private StringBuilder elementValue; 

        public List<OldCard> getCards() {
            return cards;
        }

        @Override
        public void startDocument() {
            elementValue = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            elementValue.setLength(0); 
            
            if (qName.equalsIgnoreCase("OldCard")) {
                currentCard = new OldCard();
                currentCard.setId(attributes.getValue("id"));
                currentCard.setSent(Boolean.parseBoolean(attributes.getValue("sent")));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            elementValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            String value = elementValue.toString().trim();
            if (currentCard != null) {
                switch (qName.toLowerCase()) {
                    case "thema": currentCard.setThema(value); break;
                    case "type": currentCard.setType(value); break;
                    case "country": currentCard.setCountry(value); break;
                    case "year": currentCard.setYear(Integer.parseInt(value)); break;
                    case "author": currentCard.setAuthor(value); break;
                    case "valuable": currentCard.setValuable(value); break;
                    case "oldcard":
                        cards.add(currentCard);
                        break;
                }
            }
        }
    }
}