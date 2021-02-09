package jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import jaxb.foodList.Food;

public class FoodTest {

	public static void main(String[] args) {
		try {
			marshall();
			unmarshall();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void marshall() {
		try {
			Food food= createFood();
			JAXBContext jaxbContext = JAXBContext.newInstance(Food.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(food, new FileOutputStream(new File("./xml/xmlfile/food.xml")));
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private static void unmarshall() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Food.class);
			Unmarshaller unmarshal = jaxbContext.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("food.xsd"));
			unmarshal.setSchema(schema);
			unmarshal.setEventHandler(new DefaultValidationEventHandler());
			
			Food food = (Food) unmarshal.unmarshal(new FileInputStream(new File(ClassLoader.getSystemResource("food.xml").getFile())));
			System.out.println(food);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static Food createFood() {
		Food food = new Food();
		food.setName("스시");
		food.setCost("100000");
		food.setType("일식");
		return food;
	}
}
