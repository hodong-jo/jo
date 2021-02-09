/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2013. 4. 22.   tghan    1.0    최초 작성
 *
 */

package vo;


/**
 * @author tghan
 * @since 2013. 4. 22.
 * @version 1.0
 * @see 
 */
public class Book {

	private String id;
	private String type;
	private String title;
	private String author;
	private String format;
	private String price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String toString() {
		return "Book@[id=" + this.id + ",type=" + this.type + ",title=" + this.title 
				+ ",author=" + this.author + ",format=" + this.format + ",price=" + this.price + "]";
	}
	
	
	
}
