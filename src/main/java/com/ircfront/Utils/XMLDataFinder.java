package com.ircfront.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;

public class XMLDataFinder {

  private static String pathPersonalData = "personal-data.xml";
  private static String pathSetting = "setting.xml";

  private static NodeList XPathFinder(String url, String expression) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(url);
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile(expression);
    return (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
  }

  private static String getPersonnalData(String expr, String casError){
    try {
      NodeList res1 = XPathFinder(pathPersonalData, expr);
      return res1.item(0).getTextContent();
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      createXML("", "white","En");
      return casError;
    }
  }

  private static String getSetting(String expr){
    try {
      return XPathFinder(pathSetting, expr).item(0).getTextContent();
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      return "";
    }
  }


    private static void createXML(String PSEUDO, String THEME, String LANGUAGE){
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("user");
      doc.appendChild(rootElement);

      // pseudo elements
      Element pseudo = doc.createElement("pseudo");
      pseudo.appendChild(doc.createTextNode(PSEUDO));
      rootElement.appendChild(pseudo);

      // theme elements
      Element theme = doc.createElement("theme");
      theme.appendChild(doc.createTextNode(THEME));
      rootElement.appendChild(theme);

      // language elements
      Element language = doc.createElement("language");
      language.appendChild(doc.createTextNode(LANGUAGE));
      rootElement.appendChild(language);

      new File(pathPersonalData).delete();

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(pathPersonalData));

      // Output to console for testing
      // StreamResult result = new StreamResult(System.out);

      transformer.transform(source, result);
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
  }

  public static String getPseudo() {
    return getPersonnalData("//pseudo", "");
  }

  public static String getTheme() {
    return getPersonnalData("//theme", "white");
  }

  public static String getLangage() {
    return getPersonnalData("//language", "En");
  }

  public static String getBuildNum() { return getSetting("//numero"); }
  public static String getVersion() { return getSetting("//version"); }

  public static int[] getBuildDate() {
    String[] str = getSetting("//date").split("-");
    return new int[]{Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2])};
  }

  public static void setPseudo(String pseudo) {
    createXML(pseudo, getTheme(), getLangage());
  }

  public static void setTheme(String theme) {
    createXML(getPseudo(), theme, getLangage());
  }

  public static void setLangage(String language) {
    createXML(getPseudo(), getTheme(), language);
  }

}
