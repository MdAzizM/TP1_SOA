package org.example;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;

public class Xpath {
    public static void main(String[] args) {
        try {
            // 1️⃣ Charger le document XML
            File xmlFile = new File("src/main/resources/Cinema.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // 2️⃣ Créer un objet XPath
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            // 3️⃣ Définir une expression XPath
            // Exemple : récupérer tous les titres de films
            String expression = "/Cinema/Film/Title";
            XPathExpression xPathExpression = xpath.compile(expression);

            // 4️⃣ Évaluer l'expression
            NodeList nodes = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);

            // 5️⃣ Afficher les résultats
            System.out.println("🎬 Titres des films :");
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println("- " + nodes.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
