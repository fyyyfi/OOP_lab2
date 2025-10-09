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

    public static void main(String[] args) {
        logger.info("--- Запуск програми 'Старі листівки' ---");

        String xmlFile = "src/main/resources/old_cards.xml";
        String xsdFile = "src/main/resources/old_cards.xsd";
        String xsltFile = "src/main/resources/old_cards.xsl";
        String outputXmlFile = "grouped_by_country.xml";

  
        logger.info("Крок 1: Валідація файлу {}", xmlFile);
        if (XmlValidator.validate(xmlFile, xsdFile)) {
            logger.info("Результат: XML є валідним.");
        } else {
            logger.error("Результат: XML не є валідним. Роботу програми зупинено.");
            return;
        }


        logger.info("Крок 2: Парсинг та сортування");


        try {
            logger.info(" Використання DOM парсера");
            List<OldCard> cards = new DomCardParser().parse(xmlFile);
            logger.info("Знайдено {} листівок", cards.size());
             cards.sort(CardComparators.byYear());
            logger.info("Листівки, відсортовані за роком:");
            cards.forEach(card -> logger.info(card.toString()));
        } catch (Exception e) {
            logger.error("Помилка DOM-парсера", e);
        }

        try {
            logger.info("Використання SAX парсера");
            List<OldCard> cards = new SaxCardParser().parse(xmlFile);
            logger.info("Знайдено {} листівок", cards.size());
            cards.sort(CardComparators.byCountry());
            logger.info("Листівки, відсортовані за країною:");
            cards.forEach(card -> logger.info(card.toString()));
        } catch (Exception e) {
            logger.error("Помилка SAX парсера", e);
        }

        try {
            logger.info("Використання STAX парсера");
            List<OldCard> cards = new StaxCardParser().parse(xmlFile);
            logger.info("Знайдено {} листівок.", cards.size());
            cards.sort(CardComparators.byId());
            logger.info("Листівки, відсортовані за ID:");
            cards.forEach(card -> logger.info(card.toString()));
        } catch (Exception e) {
            logger.error("Помилка STAX парсера", e);
        }

        logger.info("Крок 3: XSLT трансформація");
        XsltTransformer.transform(xmlFile, xsltFile, outputXmlFile);
        logger.info("Трансформацію завершено. Результат у файлі: {}", outputXmlFile);
    }
}