package jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class JaxbTest {
	static Logger logger = Logger.getLogger("CONSOLE");
	public static void main(String[] args) {
		JaxbTest jaxbTest = new JaxbTest();
//		jaxbTest.marshall();
		jaxbTest.unmarshall();
	}

	private void marshall() {
		try {
			JaxbTest jaxbTest = new JaxbTest();
			Phonebook phonebook = new Phonebook();
			phonebook.setPerson(jaxbTest.createPerson());
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Phonebook.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(phonebook, new FileOutputStream(new File("./xml/xmlfile/jaxb.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {
			logger.warn("Marshall Finished!");
		}
	}
	
	private void unmarshall() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Phonebook.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory factory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("./xml/xsd/phonebook.xsd"));
			unmarshaller.setSchema(schema);
			
			Phonebook phonebook = (Phonebook)unmarshaller.unmarshal(new FileInputStream("./xml/xmlfile/jaxb.xml"));
			for(int i = 0; i < phonebook.getPerson().size(); i++) {
				logger.warn(phonebook.getPerson().get(i).toString());
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {
			logger.warn("Unmarshall Finished!");
		}
	}
	
	private List<Person> createPerson(){
		List<Person> personList = new ArrayList();
		
		Person person1 = new Person();
		person1.setName("Joe Wang");
		person1.setEmail("joe@yourserver.com");
		person1.setTelephone("202-999-9999");
		person1.setWeb("www.java2s.com");
		personList.add(person1);
		
		Person person2 = new Person();
		person2.setName("Karol");
		person2.setEmail("karol@yourserver.com");
		person2.setTelephone("306-999-9999");
		person2.setWeb("www.java2s.com");
		personList.add(person2);
		
		Person person3 = new Person();
		person3.setName("Green");
		person3.setEmail("green@yourserver.com");
		person3.setTelephone("202-414-9999");
		person3.setWeb("www.java2s.com");
		personList.add(person3);
		
		return personList;
	}
}
