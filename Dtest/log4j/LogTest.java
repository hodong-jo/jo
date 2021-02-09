import java.io.IOException;
import java.util.logging.Logger;


public class LogTest {
	private final static Logger logger = Logger.getLogger(LogTest.class.getName());

	public static void main(String[] args) throws SecurityException, IOException {
		
		logger.warning("세월아 네월아~~~~~~~");
		logger.info("대출이 어렵나요? 4천만 땡겨줘요");//사전체크하기
		logger.info("Current date timestamp is " + System.currentTimeMillis()); 
	}

}
