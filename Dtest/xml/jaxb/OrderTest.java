package jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.print.attribute.standard.Finishings;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import jaxb.order.AddressType;
import jaxb.order.CustomerType;
import jaxb.order.OrderType;
import jaxb.order.Root;
import jaxb.order.ShipInfoType;
import jaxb.order.Root.Customers;
import jaxb.order.Root.Orders;

public class OrderTest {

	public static void main(String[] args) {
//		marshaller();
		unmarshaller();
	}
	
	private static void marshaller() {
		try {
			Root root = new Root();
			Customers customers = new Customers();
			Orders orders = new Orders();
			customers.addCustomer(createCustomerType());
			orders.addOrder(createOrderType());
			root.setCustomers(customers);
			root.setOrders(orders);
			
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(Root.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(root, new FileOutputStream(new File("./xml/xmlfile/order.xml")));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("marshaller finished");
		}
	}
	
	private static void unmarshaller() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("order.xsd"));
			unmarshaller.setSchema(schema);
			
			Root root = (Root)unmarshaller.unmarshal(new FileInputStream(ClassLoader.getSystemResource("order.xml").getFile()));
			System.out.println(root.getCustomers().getCustomer().get(0).getCompanyName());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("unmarshaller finished");
		}
	}

	private static CustomerType createCustomerType() {
		CustomerType customerType = new CustomerType();
		customerType.setCompanyName("companyname");
		customerType.setContactName("contactname");
		customerType.setContactTitle("contactTitle");
		customerType.setCustomerID("customerid");//key
		customerType.setFax("fax");
		customerType.setFullAddress(createAddressType());
		customerType.setPhone("phone");
		return customerType;
	}
	
	private static OrderType createOrderType() throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		OrderType orderType = new OrderType();
		orderType.setCustomerID("customerid");
		orderType.setEmployeeID("employeeid");
		orderType.setOrderDate(xmlGregorianCalendar);
		orderType.setRequiredDate(xmlGregorianCalendar);
		orderType.setShipInfo(createShipInfoType());
		return orderType;
	}
	
	private static AddressType createAddressType() {
		AddressType addressType = new AddressType();
		addressType.setAddress("address");
		addressType.setCity("city");
		addressType.setCountry("contry");
		addressType.setCustomerID("customerid");//key
		addressType.setPostalCode("postalcode");
		addressType.setRegion("region");
		return addressType;
	}
	
	private static ShipInfoType createShipInfoType() throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		ShipInfoType shipInfoType = new ShipInfoType();
		shipInfoType.setFreight(BigDecimal.ONE);
		shipInfoType.setShipAddress("shipaddress");
		shipInfoType.setShipCity("shipcity");
		shipInfoType.setShipCountry("shipcountry");
		shipInfoType.setShipName("shipname");
		shipInfoType.setShippedDate(xmlGregorianCalendar);
		shipInfoType.setShipPostalCode("postalcode");
		shipInfoType.setShipRegion("shipregion");
		shipInfoType.setShipVia(BigInteger.ONE);
		return shipInfoType;
	}
}
