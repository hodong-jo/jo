import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesReadTest {
	
	static final String CONFIG_PATH = "test.properties";
	
	public static void main(String[] args) {
		Reader reader = null;
		Properties props = new Properties();
		
		try {
			File propFile = new File(ClassLoader.getSystemClassLoader().getResource(CONFIG_PATH).getFile());
			reader = new InputStreamReader(new FileInputStream(propFile));
			props.load(reader);
			
			Iterator iter = props.entrySet().iterator();
			while(iter.hasNext()) {
				Entry entry = (Entry) iter.next();
				
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
