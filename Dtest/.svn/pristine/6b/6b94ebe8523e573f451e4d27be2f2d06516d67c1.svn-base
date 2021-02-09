import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter{
	
	@Override
	public String format(LogRecord record) {
		
		StringBuffer buf = new StringBuffer(1000);
		 
	    buf.append(calcDate(record.getMillis()));
	        
	    buf.append(" [");
	    buf.append(record);
	    buf.append("] ");
	        
	    buf.append("[");
	    buf.append(record.getSourceMethodName());
	    buf.append("] ");
	        
	    buf.append(record.getMessage());
	    buf.append("\n");
	        
	    return buf.toString();
	}
    
    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }

}
