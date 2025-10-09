package postcard.instr;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XsltTransformer {


    public static void transform(String xmlPath, String xsltPath, String outputPath) {
        try {

            TransformerFactory factory = TransformerFactory.newInstance();

            StreamSource xsltSource = new StreamSource(new File(xsltPath));

            Transformer transformer = factory.newTransformer(xsltSource);

            StreamSource xmlSource = new StreamSource(new File(xmlPath));
            StreamResult result = new StreamResult(new File(outputPath));

            transformer.transform(xmlSource, result);

        } catch (Exception e) {

            System.err.println("Помилка трансформації: " + e.getMessage());
            e.printStackTrace();
        }
    }
}