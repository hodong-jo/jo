package sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import vo.Dvd;

public class DvdDataHandler extends DefaultHandler{
	
	private Dvd dvd = null;
	private List dvdList = null;
	private List starList = null;

	public List getDvd() {
		return dvdList;
	}
	
	String dvdName = "dvd";
	
	boolean id;
	boolean type;
	boolean title;
	boolean director;
	boolean star;
	boolean price;
	
	boolean state = false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (localName.equalsIgnoreCase("itemList")) {
			if(dvdList == null)
				dvdList = new ArrayList();
		} else if (localName.equalsIgnoreCase(dvdName)) {
			starList = new ArrayList();
			dvd = new Dvd();
			state = true;
		} else if (localName.equalsIgnoreCase("id") && state) {
			id = true;
		} else if (localName.equalsIgnoreCase("type") && state) {
			type = true;
		} else if (localName.equalsIgnoreCase("title") && state) {
			title = true;
		} else if (localName.equalsIgnoreCase("director") && state) {
			director = true;
		} else if (localName.equalsIgnoreCase("star") && state) {
			star = true;
		} else if (localName.equalsIgnoreCase("price") && state) {
			price = true;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(localName.equalsIgnoreCase(dvdName)) {
			dvdList.add(dvd);
			state = false;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(id) {
			dvd.setId(new String(ch, start, length));
			id = false;
		} else if (type) {
			dvd.setType(new String(ch, start, length));
			type = false;
		} else if (title) {
			dvd.setTitle(new String(ch, start, length));
			title = false;
		} else if (director) {
			dvd.setDirector(new String(ch, start, length));
			director = false;
		} else if (star) {
			starList.add(new String(ch, start, length));
			dvd.setStar(starList);
			star = false;
		} else if (price) {
			dvd.setPrice(new String(ch, start, length));
			price = false;
		}
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {//error«ÿ¡‡æﬂ Ω∫≈∞∏∂ ¿˚øÎµ 
		throw e;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		throw e;
	}
}
