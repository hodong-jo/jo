/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2013. 4. 19.   tghan    1.0    최초 작성
 *
 */

package sax;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import vo.Book;

/**
 * @author tghan
 * @since 2013. 4. 19.
 * @version 1.0
 * @see 
 */
public class BookDataHandler extends DefaultHandler{
	
	private String startTagName;
    private String endTagName;

	private String value;
	
	private StringBuffer buffer = new StringBuffer();

	private Vector books = new Vector();
	
	private int state;
	
	public static final int STARTED			= 1;
	public static final int ENDED			= 2;
	
	public BookDataHandler() {
		super();
	}

	public Vector getBooks(){
		return this.books;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("#invoked startElement:" + localName);
		
		if("book".equals(localName)){
			this.books.add(new Book());
		}
		this.startTagName = localName;
		this.value = null;
		buffer.setLength(0);
		
		this.state = STARTED;
//		System.out.println("localName : " + localName + " / state : " + this.state);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
//		System.out.println("ene localName : " + localName + " / state : " + this.state);
		this.endTagName = localName;
		if(this.startTagName.equals(this.endTagName) && this.state == STARTED){
			this.value = buffer.toString().trim();
			Book book = (Book)this.books.lastElement();
			System.out.println(this.endTagName + ":[" + this.value + "]");
			
			if("id".equals(localName)){
				book.setId(this.value);
			}else if("type".equals(localName)){
				book.setType(this.value);
			}else if("title".equals(localName)){
				book.setTitle(this.value);
			}else if("author".equals(localName)){
				book.setAuthor(this.value);
			}else if("format".equals(localName)){
				book.setFormat(this.value);
			}else if("price".equals(localName)){
				book.setPrice(this.value);
			}
			
		}
		
		this.state = ENDED;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println("#invoked characters:" + this.startTagName);
		buffer.append(ch, start, length);
		this.value = new String(ch, start, length).trim();
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		throw e;
	}
	
	
}
