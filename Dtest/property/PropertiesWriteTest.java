import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

public class PropertiesWriteTest {
	
	static final String CONFIG_PATH = "test.properties";

	public static void main(String[] args) {
		Writer writer = null;
		Properties props = new Properties();
		System.out.println(ClassLoader.getSystemClassLoader().getResource(CONFIG_PATH));
		try {
			
			props.setProperty("T1", "a");
			props.setProperty("T2", "b");
			props.setProperty("T3", "c");
			props.setProperty("T4", "d");
			props.setProperty("T5", "e");
			props.setProperty("T6", "f");
			
			File propFile = new File(ClassLoader.getSystemClassLoader().getResource(CONFIG_PATH).getFile());
			
			writer = new OutputStreamWriter(new FileOutputStream(propFile));
			
//			props.load(propFile);
			
			props.store(writer, "Properties File");
			
			System.out.println("Stored config file:" + propFile);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
