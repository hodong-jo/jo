package sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SaxTest {

	public static void main(String[] args) {
		SaxTest saxTest = new SaxTest();
		saxTest.sax();
	}

	private void sax() {
		try {
			File file = new File(ClassLoader.getSystemResource("XmlTest.xml").getFile());
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			DongHandler dongHandler = new DongHandler();
			parser.parse(file, dongHandler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
