package utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	
	public static String getMandatoryProperty(String key, Properties props){
		if(key == null || key.length() == 0) 
			throw new IllegalArgumentException("Properties key not assigned");
		
		String value = props.getProperty(key);
		
		if(value == null || value.length() == 0){
			throw new IllegalArgumentException("'" + key + "' not assigned");
		}
		return value;
	}
	
	public static Properties parseProperties(String text) throws Exception{
		Properties p = new Properties();
		if (text != null) {
			p.load(new ByteArrayInputStream(text.replace(';', '\n')
					.getBytes()));
		}
		return p;
	}
	
	public static void parseProperties(String text, Map map) throws Exception{
		map.putAll(parseProperties(text));
	}

	public static Properties loadProperties(File propFile) throws Exception{
		FileInputStream fis = null;
		Properties props = new Properties();
		try{
			fis = new FileInputStream(propFile);
			props.load(fis);
		}finally{
			try{
				fis.close();
			}catch(IOException ioe){
				
			}
		}
		return props;
	}

}
