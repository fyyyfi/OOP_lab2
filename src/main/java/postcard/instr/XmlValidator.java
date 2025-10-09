package postcard.instr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlValidator {
     
    private static final Logger logger = LogManager.getLogger(XmlValidator.class);
 
    public static boolean validate(String xmlPath, String xsdPath) {
        try {
   
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(new File(xsdPath));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlPath)));

 logger.debug("XML-файл {} успішно пройшов валідацію за схемою {}.", xmlPath, xsdPath);
            return true;
        } catch (Exception e) {

            logger.error("Помилка валідації XML: {}", e.getMessage());
            return false;
        }
    }
}
