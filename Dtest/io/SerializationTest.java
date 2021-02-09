import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationTest {

	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream("./io/serial.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SampleData s1 = new SampleData("»ç½¿",1004);
		SampleData s2 = new SampleData("°õ",1005);
		SampleData s3 = new SampleData("¸»",1001);
		SampleData s4 = new SampleData("¶Ë",1007);
		oos.writeObject(s1);
		oos.writeObject(s2);
		oos.writeObject(s3);
		oos.writeObject(s4);
		
		oos.close();
		
		System.out.println(s1 + ":" + s1.getSampleDate());
		System.out.println(s2 + ":" + s2.getSampleDate());
		System.out.println(s3 + ":" + s3.getSampleDate());
		System.out.println(s4 + ":" + s4.getSampleDate());
	}

}
