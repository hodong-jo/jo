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

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
/**
 * @author tghan
 * @since 2013. 4. 19.
 * @version 1.0
 * @see 
 */
public class SaxParsetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			
			InputStream in = ClassLoader.getSystemResourceAsStream("itemList.xml");
			
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(ClassLoader.getSystemResource("itemList.xsd"));
			
//			Validator validator = schema.newValidator();
//			SAXSource source = new SAXSource(new InputSource(ClassLoader.getSystemResourceAsStream("itemList.xml")));
//			validator.validate(source);//스키마유효성검사
			
			saxFactory.setSchema(schema);//스키마 유효성검사 핸들러에서 errorException 설정해줘야함
			saxFactory.setNamespaceAware(true);
//			saxFactory.setValidating(true);
			
			SAXParser parser = saxFactory.newSAXParser();
//			System.out.println("Schema:" + parser.getSchema());
			
			BookDataHandler dataHandler = new BookDataHandler();
			DvdDataHandler dvdHandler = new DvdDataHandler();
			parser.parse(in, dvdHandler);
			
			Vector bookList = dataHandler.getBooks();
			List dvdList = dvdHandler.getDvd();
			
			for(int i=0; i<bookList.size(); i++){
				System.out.println("## Book[" + i + "]:" + bookList.get(i));
			}
			
			for(int i = 0; i < dvdList.size(); i++) {
				System.out.println(dvdList.get(i));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
}
