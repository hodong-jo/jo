/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2011. 11. 10.   tghan    1.0    최초 작성
 *
 */

package utils;

/**
 * @author tghan
 * @since 2011. 11. 10.
 * @version 1.0
 * @see 
 */
public class PadUtil {
	
	public static byte[] fillByte(byte[] buf, int len, char ch, boolean leading)
	{
		byte[] value = new byte[len]; 
		if(buf.length == 0){
			for(int i = 0; i < len; i++)
			{
				value[i] = (byte)ch;
			}	
			return value;
		}
		
		int offset = len - buf.length;
		
		if(offset == 0) return buf;
		
		if(offset < 0)
		{
			System.arraycopy(buf, 0, value, 0, len);
			return value;	 
		}
		else
		{
		    if(leading)
			{
			    
				for(int i = 0; i < offset; i++)
				{
					value[i] = (byte)ch;
				}	
				System.arraycopy(buf, 0, value, offset, buf.length);
			}
			else
			{
				System.arraycopy(buf, 0, value, 0, buf.length);
				for(int i = buf.length; i < len; i++)
				{
					value[i] = (byte)ch;
				}	
			}
			return value;
		}
	}
	
	public static byte[] fillByteWithLenghExceedCheck(byte[] buf, int len, char ch, boolean leading) throws ExceedLengthException
	{
		byte[] value = new byte[len]; 
		if(buf.length == 0){
			for(int i = 0; i < len; i++)
			{
				value[i] = (byte)ch;
			}	
			return value;
		}
		
		int offset = len - buf.length;
		
		if(offset < 0){
			throw new ExceedLengthException(buf.length, len);
		}
		
		if(offset == 0) return buf;
		
		if(leading)
		{
		    
			for(int i = 0; i < offset; i++)
			{
				value[i] = (byte)ch;
			}	
			System.arraycopy(buf, 0, value, offset, buf.length);
		}
		else
		{
			System.arraycopy(buf, 0, value, 0, buf.length);
			for(int i = buf.length; i < len; i++)
			{
				value[i] = (byte)ch;
			}	
		}
		return value;
	}
	
	public static class ExceedLengthException  extends RuntimeException{
		private static final long serialVersionUID = -3514353592206828093L;

		private int dataLength;
		private int specifiedLength;
		
		public ExceedLengthException(int dataLength, int specifiedLength) {
			super("Value length(" + dataLength + ") exceeds specified length(" + specifiedLength + ")");
			this.setDataLength(dataLength);
			this.setSpecifiedLength(specifiedLength);
		}

		public int getDataLength() {
			return dataLength;
		}

		public void setDataLength(int dataLength) {
			this.dataLength = dataLength;
		}

		public int getSpecifiedLength() {
			return specifiedLength;
		}

		public void setSpecifiedLength(int specifiedLength) {
			this.specifiedLength = specifiedLength;
		}
	}
}
