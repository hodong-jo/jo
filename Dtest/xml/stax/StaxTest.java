package stax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import javanet.staxutils.IndentingXMLStreamWriter;
import utils.IOUtil;
import vo.Dong;
import vo.Dvd;

public class StaxTest {

	public static void main(String[] args) {
		
		File file = new File(ClassLoader.getSystemResource("XmlTest.xml").getFile());
		List<Dong> dongList = dongXML(file);
		for(int i = 0; i < dongList.size(); i++) {
			System.out.println(dongList.get(i));
		}
		
		File file2 = new File(ClassLoader.getSystemResource("itemList.xml").getFile());
		List<Dvd> dvdList = dvdXML(file2);
		for(int i = 0; i < dvdList.size(); i++) {
			System.out.println(dvdList.get(i));
		}
		
		staxWriteTest();
	}
	
	private static List<Dong> dongXML(File file){
		try {
			List<Dong> dongList = new ArrayList<>();
			Dong dong = null;
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader(file));
			
			while(streamReader.hasNext()) {
				streamReader.next();
				if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
					if(streamReader.getLocalName().equalsIgnoreCase("donglist")) {
						dong = new Dong();
					}
					if(streamReader.getLocalName().equalsIgnoreCase("seq")) {
						dong.setSeq(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("zipcode")) {
						dong.setZipcode(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("sido")) {
						dong.setSido(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("gugun")) {
						dong.setGugun(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("dong")) {
						dong.setDong(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("bunji")) {
						dong.setBunji(streamReader.getElementText());
					}
				}
				if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT) {
					if(streamReader.getLocalName().equalsIgnoreCase("donglist")) {
						dongList.add(dong);
					}
				}
			}
			return dongList;
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static List<Dvd> dvdXML(File file){
		try {
			List<Dvd> dvdList = new ArrayList<>();
			List star = null;
			Dvd dvd = null;
			boolean stat = false;
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader streamReader = factory.createXMLStreamReader(new FileReader(file));
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("itemList.xsd"));
			
			Validator validator = schema.newValidator();
			validator.validate(new StAXSource(factory.createXMLStreamReader(new FileReader(file))));
			
			while(streamReader.hasNext()) {
				streamReader.next();
				if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {
					if(streamReader.getLocalName().equalsIgnoreCase("dvd")) {
						star = new ArrayList();
						dvd = new Dvd();
						stat = true;
					}
					if(streamReader.getLocalName().equalsIgnoreCase("id") && stat) {
						dvd.setId(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("type") && stat) {
						dvd.setType(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("title") && stat) {
						dvd.setTitle(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("director") && stat) {
						dvd.setDirector(streamReader.getElementText());
					}
					if(streamReader.getLocalName().equalsIgnoreCase("star") && stat) {
						star.add(streamReader.getElementText());
						dvd.setStar(star);
					}
					if(streamReader.getLocalName().equalsIgnoreCase("price") && stat) {
						dvd.setPrice(streamReader.getElementText());
					}
				}
				if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT) {
					if(streamReader.getLocalName().equalsIgnoreCase("dvd")){
						dvdList.add(dvd);
						stat = false;
					}
				}
			}
			return dvdList;
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void staxWriteTest() {
		StringWriter stringWriter = null;
		FileOutputStream fos = null;
		try {
			stringWriter = new StringWriter();
			fos = new FileOutputStream("./xml/xmlfile/Test.xml");
			
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = new IndentingXMLStreamWriter(xMLOutputFactory
					.createXMLStreamWriter(fos));//&createXMLStreamWriter(stringWriter)
			
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("cars");

			xMLStreamWriter.writeStartElement("supercars");
			xMLStreamWriter.writeAttribute("company", "Ferrari");

			xMLStreamWriter.writeStartElement("carname");
			xMLStreamWriter.writeAttribute("type", "formula one");
			xMLStreamWriter.writeCharacters("Ferrari 101");
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("carname");
			xMLStreamWriter.writeAttribute("type", "sports");
			xMLStreamWriter.writeCharacters("Ferrari 202");
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();

			xMLStreamWriter.flush();
			
			String xmlString = stringWriter.toString();

			System.out.println(xmlString);
			System.out.println("sucess");

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtil.close(fos);
			IOUtil.close(stringWriter);
		}
	}

}
