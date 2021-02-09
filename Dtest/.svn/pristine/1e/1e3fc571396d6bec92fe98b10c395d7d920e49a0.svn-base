package jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import jaxb.userlist.USERLIST;
import jaxb.userlist.USERLIST.USER;

public class UserListTest {

	public static void main(String[] args) {
		unmarshall();
	}
	private static void marshall() {
		try {
//			USERLIST 
			JAXBContext jaxbContext = JAXBContext.newInstance(USERLIST.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
//			marshaller.marshal(jaxbElement, handler);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void unmarshall() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(USERLIST.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("userlist.xsd"));
			
			unmarshaller.setSchema(schema);
			
			USERLIST userlist = (USERLIST) unmarshaller.unmarshal(new FileInputStream("./xml/xmlfile/userlist.xml"));
			for(int i = 0; i < userlist.getUSEROrUSER1().size(); i++) {
				USER user = (USER) userlist.getUSEROrUSER1().get(i);
				System.out.println(user);
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createUser() {
		USER user = new USER();
		user.setNAME("ho");
//		user.set
	}

}
