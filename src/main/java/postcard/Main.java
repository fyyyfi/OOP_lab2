package postcard;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import postcard.model.OldCard;
import postcard.parser.DomCardParser;
import postcard.parser.SaxCardParser;
import postcard.parser.StaxCardParser;
import postcard.instr.CardComparators;
import postcard.instr.XmlValidator;
import postcard.instr.XsltTransformer;

import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String XML_FILE_PATH = "src/main/resources/old_cards.xml";
    private static final String XSD_FILE_PATH = "src/main/resources/old_cards.xsd";
    private static final String XSLT_FILE_PATH = "src/main/resources/old_cards.xsl";
    private static final String OUTPUT_XML_FILE_PATH = "grouped_by_country.xml";

    public static void main(String[] args) {
        logger.info("Запуск програми 'Старі листівки'");

        logger.info("Валідація файлу {}",XML_FILE_PATH);
        if (XmlValidator.validate(XML_FILE_PATH, XSD_FILE_PATH)) {
            logger.info("Результат: XML є валідним.");
        } else {
            logger.error("Результат: XML не є валідним. Роботу програми зупинено.");
            return;
        }


        System.out.println("Парсинг та сортування");


        try {
            System.out.println("\n1. Використання DOM парсера");
            List<OldCard> cards = new DomCardParser().parse(XML_FILE_PATH);
            System.out.println("Знайдено " + cards.size() + " листівки.");
            cards.sort(CardComparators.byYear());
            System.out.println("Листівки, відсортовані за роком:");

            cards.forEach(System.out::println);
        } catch (Exception e) {
            logger.error("Помилка DOM-парсера", e);
        }

        try {
            System.out.println("\n2. Використання SAX парсера:");
            List<OldCard> cards = new SaxCardParser().parse(XML_FILE_PATH);
            System.out.println("Знайдено " + cards.size() + " листівки.");
            cards.sort(CardComparators.byCountry());
            System.out.println("Листівки, відсортовані за країною:");
            cards.forEach(System.out::println);
        } catch (Exception e) {
            logger.error("Помилка SAX парсера", e);
        }

        try {
            System.out.println("\n3. Використання StAX парсера:");
            List<OldCard> cards = new StaxCardParser().parse(XML_FILE_PATH);
            System.out.println("Знайдено " + cards.size() + " листівки.");
            cards.sort(CardComparators.byId());
            System.out.println("Листівки, відсортовані за ID:");
            cards.forEach(System.out::println);
        } catch (Exception e) {
            logger.error("Помилка STAX парсера", e);
        }

        logger.info("XSLT трансформація");
        XsltTransformer.transform(XML_FILE_PATH, XSLT_FILE_PATH, OUTPUT_XML_FILE_PATH);
        logger.info("Трансформацію завершено. Результат у файлі: {}", OUTPUT_XML_FILE_PATH);
    }
}