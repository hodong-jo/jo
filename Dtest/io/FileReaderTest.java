import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("./io/writer.dat");
		int i;
		while((i = fr.read()) != -1) {
			System.out.print(i+" ");
		}
		fr.close();
	}

}
