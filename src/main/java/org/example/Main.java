package org.example;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) {
        try {
            // ====== 1️⃣ Validation XML/XSD ======
            File xmlFile = new File("src/main/resources/Cinema.xml");
            File xsdFile = new File("src/main/resources/Cinema.xsd");

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            System.out.println("✅ XML valide selon le XSD !");

            // ====== 2️⃣ Interrogation XPath ======
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            // Exemple : tous les titres de films
            XPathExpression expr = xpath.compile("/Cinema/Film/Title");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

            System.out.println("🎬 Titres des films :");
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println("- " + nodes.item(i).getTextContent());
            }

        } catch (Exception e) {
            System.out.println("❌ Une erreur s'est produite :");
            e.printStackTrace();
        }
    }
}
