package dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import vo.Dvd;

public class DomTest {

	public static void main(String[] args) {
		try {
			File file = new File(ClassLoader.getSystemResource("itemList.xml").getFile());
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("itemList.xsd"));
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); //네임스페이스사용
			factory.setIgnoringElementContentWhitespace(true); //각태그와 다음태그사이의 공백 및 개행문자 제거(공백문자제거)
			factory.setSchema(schema);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setErrorHandler(new ErrorHandler(){

				@Override
				public void warning(SAXParseException e)
						throws SAXException {
					throw e;
				}

				@Override
				public void error(SAXParseException e)
						throws SAXException {
					throw e;
				}

				@Override
				public void fatalError(SAXParseException e)
						throws SAXException {
					throw e;
				}
				
			});
			Document document = builder.parse(file);
			document.getDocumentElement().normalize();//xml정규화 xml문서의 구조로 만들기위해?
			
			Element root = document.getDocumentElement();
			String nsURI = root.getNamespaceURI();
			
			NodeList nodeList = document.getElementsByTagNameNS(nsURI, "book");
			
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
				    System.out.println("id : "  + eElement.getElementsByTagNameNS(nsURI,"id").item(0).getTextContent());
				    System.out.println("type : "   + eElement.getElementsByTagNameNS(nsURI,"type").item(0).getTextContent());
				    System.out.println("title : "    + eElement.getElementsByTagNameNS(nsURI, "title").item(0).getTextContent());
				    System.out.println("author : "    + eElement.getElementsByTagNameNS(nsURI, "author").item(0).getTextContent());
				    System.out.println("format : "    + eElement.getElementsByTagNameNS(nsURI, "format").item(0).getTextContent());
				    System.out.println("price : "    + eElement.getElementsByTagNameNS(nsURI, "price").item(0).getTextContent());
				}
				System.out.println("");
			}
			
			XPathFactory xpthFactory = XPathFactory.newInstance();
			XPath xpath = xpthFactory.newXPath();
			xpath.setNamespaceContext(new NamespaceContext() {
				@Override
				public Iterator getPrefixes(String namespaceURI) {
					List list = new ArrayList();
					if("http://ws.sosnoski.com/library".equals(namespaceURI)) {
						list.add("ns1");
					}
					return list.iterator();
				}
				@Override
				public String getPrefix(String namespaceURI) {
					return "http://ws.sosnoski.com/library".equals(namespaceURI) ? "ns1" : null;
				}
				@Override
				public String getNamespaceURI(String prefix) {
					return "ns1".equals(prefix) ? "http://ws.sosnoski.com/library" : null;
				}
			});
			
			NodeList list = (NodeList)xpath.evaluate("//ns1:dvd", document, XPathConstants.NODESET);// //*[local-name()='dvd']
			
			System.out.println("## Node len :" + list.getLength());
			
			Dvd[] dvds = new Dvd[list.getLength()];
			for(int i = 0; i < list.getLength(); i++) {
				List star = new ArrayList();
				Node dvdNode = list.item(i);
				
				String starStr = xpath.evaluate("count(ns1:star)", dvdNode);
				int starCount = Integer.parseInt(starStr);
				
				dvds[i] = new Dvd();
				dvds[i].setId(xpath.evaluate("ns1:id", dvdNode));
				dvds[i].setType(xpath.evaluate("ns1:type", dvdNode));
				dvds[i].setTitle(xpath.evaluate("ns1:title", dvdNode));
				dvds[i].setDirector(xpath.evaluate("ns1:director", dvdNode));
				for(int j = 1; j <= starCount; j++) {
					star.add(xpath.evaluate("ns1:star["+ j +"]", dvdNode));
				}
				dvds[i].setStar(star);
				dvds[i].setPrice(xpath.evaluate("ns1:price", dvdNode));
				
				System.out.println("Dvd[" + i + "]:" + dvds[i]);
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
	}
}
