package vo;

import java.util.List;

public class Dvd {

	private String id;
	private String type;
	private String title;
	private String director;
	private List star;
	private String price;
	
	public List getStar() {
		return star;
	}
	public void setStar(List star) {
		this.star = star;
	}
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
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Dvd [id=" + id + ", type=" + type + ", title=" + title + ", director=" + director + ", star=" + star
				+ ", price=" + price + "]";
	}
	
}
