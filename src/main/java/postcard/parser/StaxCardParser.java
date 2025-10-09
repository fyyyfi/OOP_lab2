package postcard.parser;

import postcard.model.OldCard;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class StaxCardParser {

    public List<OldCard> parse(String xmlFilePath) throws Exception {
        List<OldCard> cardList = new ArrayList<>();
        OldCard currentCard = null;
        String currentTagContent = null;
        
        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(xmlFilePath));


        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.START_ELEMENT:
                    String tagName = reader.getLocalName();
                    if ("OldCard".equalsIgnoreCase(tagName)) {
                        currentCard = new OldCard();
                        currentCard.setId(reader.getAttributeValue(null, "id"));
                        currentCard.setSent(Boolean.parseBoolean(reader.getAttributeValue(null, "sent")));
                    }
                    break;
                
                case XMLStreamConstants.CHARACTERS:
                    currentTagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    tagName = reader.getLocalName();
                    if (currentCard != null) {
                        if ("OldCard".equalsIgnoreCase(tagName)) {
                            cardList.add(currentCard);
                        } else if ("Thema".equalsIgnoreCase(tagName)) {
                            currentCard.setThema(currentTagContent);
                        } else if ("Type".equalsIgnoreCase(tagName)) {
                            currentCard.setType(currentTagContent);
                        } else if ("Country".equalsIgnoreCase(tagName)) {
                            currentCard.setCountry(currentTagContent);
                        } else if ("Year".equalsIgnoreCase(tagName)) {
                            currentCard.setYear(Integer.parseInt(currentTagContent));
                        } else if ("Author".equalsIgnoreCase(tagName)) {
                            currentCard.setAuthor(currentTagContent);
                        } else if ("Valuable".equalsIgnoreCase(tagName)) {
                            currentCard.setValuable(currentTagContent);
                        }
                    }
                    break;
            }
        }
        return cardList;
    }
}