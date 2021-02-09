import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException{//사용이유? Testpj에서사용하니 받아지긴함
		FileInputStream fis = new FileInputStream("./io/serial.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		SampleData sd1 = (SampleData)ois.readObject();
		SampleData sd2 = (SampleData)ois.readObject();
		SampleData sd3 = (SampleData)ois.readObject();
		SampleData sd4 = (SampleData)ois.readObject();
		
		ois.close();
		
		System.out.println(sd1 + ":" + sd1.getSampleDate());
		System.out.println(sd2 + ":" + sd2.getSampleDate());
		System.out.println(sd3 + ":" + sd3.getSampleDate());
		System.out.println(sd4 + ":" + sd4.getSampleDate());
		
	}
}
