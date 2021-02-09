import java.io.Serializable;

public class SampleData implements Serializable{
	public String str;
	public int id;
	public SampleData(String str, int id) {
		this.str = str;
		this.id = id;
	}
	public String getSampleDate() {
		return id + "=" + str;
	}
}
