package dom;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DongHandler extends DefaultHandler{

	   boolean seq = false;
	   boolean zipcode = false;
	   boolean sido = false;
	   boolean gugun = false;
	   boolean dong = false;
	   boolean bunji = false;
	   
	   StringBuffer buffer = new StringBuffer();
	   String value;

		   @Override
		public void startDocument() throws SAXException {
			   System.out.println("Start Document");
		}
		   @Override
		public void endDocument() throws SAXException {
			   System.out.println("End Document");
		}
		   
	   @Override
	   public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		   
		   buffer.setLength(0); //buffer √ ±‚»≠
	      if (qName.equalsIgnoreCase("seq")) {
	    	  seq = true;
	      } else if (qName.equalsIgnoreCase("zipcode")) {
	    	  zipcode = true;
	      } else if (qName.equalsIgnoreCase("sido")) {
	    	  sido = true;
	      } else if (qName.equalsIgnoreCase("gugun")) {
	    	  gugun = true;
	      } else if (qName.equalsIgnoreCase("dong")) {
	    	  dong = true;
	      } else if (qName.equalsIgnoreCase("bunji")) {
	    	  bunji = true;
	      }
	   }

	   @Override
	   public void endElement(String uri, String localName, String qName) throws SAXException {
	      if (qName.equalsIgnoreCase("root")) {
	         System.out.println("End Element: " + qName);
	      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {
	      
	      if (seq) {
	    	  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("seq: " + this.value);
	    	  seq = false;
	      } else if (zipcode) {
	    	  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("zipcode: " + this.value);
	          zipcode = false;
	      } else if (sido) {
	    	  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("sido: " + this.value);
	          sido = false;
	      } else if (gugun) {
	    	  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("gugun: " + this.value);
	          gugun = false;
	      } else if (dong) {
	    	  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("dong: " + this.value);
		      dong = false;
		  } else if (bunji) {
			  buffer.append(new String(ch, start, length));
	    	  this.value = buffer.toString().trim();
	    	  System.out.println("bunji: " + this.value);
			  bunji = false;
		  }
	   }
}
