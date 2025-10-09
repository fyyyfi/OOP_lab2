package postcard.parser;

import postcard.model.OldCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomCardParser {


    public List<OldCard> parse(String xmlFilePath) throws Exception {
        List<OldCard> cardList = new ArrayList<>();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(xmlFilePath));
        
        document.getDocumentElement().normalize();
        
        NodeList nodeList = document.getElementsByTagName("OldCard");
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                OldCard card = new OldCard();
                card.setId(element.getAttribute("id"));
                card.setSent(Boolean.parseBoolean(element.getAttribute("sent")));
                card.setThema(getElementTextContent(element, "Thema"));
                card.setType(getElementTextContent(element, "Type"));
                card.setCountry(getElementTextContent(element, "Country"));
                card.setYear(Integer.parseInt(getElementTextContent(element, "Year")));
                card.setAuthor(getElementTextContent(element, "Author"));
                card.setValuable(getElementTextContent(element, "Valuable"));
                
                cardList.add(card);
            }
        }
        return cardList;
    }

    private String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return null; 
        }
    }
}