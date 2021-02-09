import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MyClassTest {
	    private static Logger LOGGER;

	    static {
	        InputStream stream = MyClassTest.class.getClassLoader().
	                getResourceAsStream("logging.properties");
	        try {
	            LogManager.getLogManager().readConfiguration(stream);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        LOGGER = Logger.getLogger(MyClassTest.class.getName());
	    }

	    public static void main(String[] args) {
	        System.out.println("-- main method starts --");
	        LOGGER.info("an info msg");
	        LOGGER.warning("a warning msg");
	        LOGGER.severe("a severe msg");

	}

}
