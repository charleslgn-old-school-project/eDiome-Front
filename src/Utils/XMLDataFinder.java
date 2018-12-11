package Utils;

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

  private static NodeList XPathFinder(String url, String expression) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(url);
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile(expression);
    return (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
  }

  private static String getSomething(String expr, String casError){
    try {
      NodeList res1 = XPathFinder("./src/resource/data.xml", expr);
      return res1.item(0).getTextContent();
    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      createXML("", "white","En");
      return casError;
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

      new File("./src/resource/data.xml").delete();

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("./src/resource/data.xml"));

      // Output to console for testing
      // StreamResult result = new StreamResult(System.out);

      transformer.transform(source, result);

      System.out.println("File saved!");

    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
  }

  public static String getPseudo() {
    return getSomething("//pseudo", "");
  }

  public static String getTheme() {
    return getSomething("//theme", "white");
  }

  public static String getLangage() {
    return getSomething("//language", "En");
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
