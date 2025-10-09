package postcard.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import postcard.model.OldCard;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DomCardParser {

    public List<OldCard> parse(String xmlFilePath) throws Exception {
        List<OldCard> cardList = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new File(xmlFilePath));
       
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("OldCard");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element cardElement = (Element) node;
                OldCard card = new OldCard();


                card.setId(cardElement.getAttribute("id"));
                card.setSent(Boolean.parseBoolean(cardElement.getAttribute("sent")));
                card.setThema(getTagValue("Thema", cardElement));
                card.setType(getTagValue("Type", cardElement));
                card.setCountry(getTagValue("Country", cardElement));
                card.setYear(Integer.parseInt(getTagValue("Year", cardElement)));
                card.setAuthor(getTagValue("Author", cardElement));
                card.setValuable(getTagValue("Valuable", cardElement));
                
                cardList.add(card);
            }
        }
        return cardList;
    }


    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);

        if (nodeList != null && nodeList.getLength() > 0) {
            Node singleNode = nodeList.item(0);

            if (singleNode != null && singleNode.getFirstChild() != null) {
                return singleNode.getFirstChild().getNodeValue();
            }
        }
        return null;
    }
}