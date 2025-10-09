package postcard.parser;

import postcard.model.OldCard;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StaxCardParser {

    public List<OldCard> parse(String xmlFilePath) throws Exception {
        List<OldCard> cardList = new ArrayList<>();
        OldCard currentCard = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFilePath));

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                if (elementName.equalsIgnoreCase("OldCard")) {
                    currentCard = new OldCard();
                    Iterator<Attribute> attributes = startElement.getAttributes();
                    while (attributes.hasNext()) {
                        Attribute attr = attributes.next();
                        if (attr.getName().toString().equalsIgnoreCase("id")) {
                            currentCard.setId(attr.getValue());
                        } else if (attr.getName().toString().equalsIgnoreCase("sent")) {
                            currentCard.setSent(Boolean.parseBoolean(attr.getValue()));
                        }
                    }
                } else if (currentCard != null) {
                    String value = eventReader.nextEvent().asCharacters().getData();
                    switch (elementName.toLowerCase()) {
                        case "thema": currentCard.setThema(value); break;
                        case "type": currentCard.setType(value); break;
                        case "country": currentCard.setCountry(value); break;
                        case "year": currentCard.setYear(Integer.parseInt(value)); break;
                        case "author": currentCard.setAuthor(value); break;
                        case "valuable": currentCard.setValuable(value); break;
                    }
                }
            }
            
            if (event.isEndElement()) {
                String elementName = event.asEndElement().getName().getLocalPart();
                if (elementName.equalsIgnoreCase("OldCard") && currentCard != null) {
                    cardList.add(currentCard);
                }
            }
        }
        return cardList;
    }
}