package postcard.instr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XsltTransformer {
    private static final Logger logger = LogManager.getLogger(XsltTransformer.class);

    public static void transform(String xmlPath, String xsltPath, String outputPath) {

        try {         
            logger.info("Початок XSLT-трансформації...");
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xsltSource = new StreamSource(new File(xsltPath));
            Transformer transformer = factory.newTransformer(xsltSource);
            StreamSource xmlSource = new StreamSource(new File(xmlPath));
            StreamResult result = new StreamResult(new File(outputPath));
            transformer.transform(xmlSource, result);
            logger.info("Трансформація успішно завершена.");
        } catch (Exception e) {
            logger.error("Під час XSLT-трансформації сталася помилка!", e);
        }
    }
}