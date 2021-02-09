import java.io.FileWriter;
import java.io.IOException;

public class FileWriteTest {

	public static void main(String[] args) throws IOException {
		
		char[] content = new char[] {1,2,3,4};
		FileWriter fos = new FileWriter("./io/writer.dat");//("./io/writer.dat",true) = µ¡ºÙÈ÷±â
		fos.write(72);
		fos.write(73);
		fos.write(172);
		fos.write(173);
		fos.write("hi");
		fos.write(content);
		fos.close();
		System.out.println("write");
	}

}
