/**
 *  내용        : 
 *  수정이력    
 * 
 *    수정일         수정자   버전     수정내용
 *    ---------     -------- ------  -----------------------------
 *    2005-09-22   tghan    1.0    최초 작성
 *
 */

package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


/**
 * <pre>
 * IO 관련 유틸 클래스
 * </pre>
 * @author tghan
 * @since 2005-09-22
 * @version 1.0
 * @see 
 */

public class IOUtil {
	
	public static final int BUFF_SIZE									= 1024;
	
	public static StringBuilder token(InputStream in, char token) throws IOException{
	    StringBuilder sbuf = new StringBuilder();
        while(true){
            int b = in.read();
            if(b == token || b == -1){
                break;
            }
            sbuf.append((char)b);
        }
        return sbuf;
	}
	
	public static StringBuilder token(InputStream in, char token, int index) throws IOException{
	    StringBuilder sbuf = new StringBuilder();
        int no = 0;
        while(true){
            int b = in.read();
            
            if(b == -1){
                return sbuf;
            }
            if(no == index){
                if( b == token) return sbuf;
                sbuf.append((char)b);
            }else if(no < index){
                if(b == token) ++no;
            }else{
                return sbuf;
            }
        }
 	}
	
    public static int writeByteArrayOutputStream(InputStream in, ByteArrayOutputStream baos, 
    		int lengthOffset, int lengthSize, int buff_size, boolean execludeLengthSize) throws IOException{
    	try{
    		byte[] readBytes = null;
    		
    		if(lengthOffset > 0){
		    	readBytes = readBytes(in, lengthOffset, buff_size);
		    	baos.write(readBytes);
    		}
	        
	        readBytes = readBytes(in, lengthSize, buff_size);
	        baos.write(readBytes);
	        
	        int sizeLength = 0;
	//        sizeLength = Integer.parseInt(new String(readBytes).trim());
	        try{
	        	sizeLength = Integer.parseInt(new String(readBytes).trim());
	        }catch(NumberFormatException nfe){
	        	throw new IOException("Invalid data length - must be number type[" + new String(readBytes).trim() + "]");
	        }
	        
	        if(sizeLength <= 0){
	        	throw new IOException("Invalid data length - must be more than 0");
	        }
	        
	        
	        if(execludeLengthSize){
	        	sizeLength += lengthSize;
	        }
	        
	        readBytes = readBytes(in, (sizeLength - (lengthOffset + lengthSize)), buff_size);
	        
	        baos.write(readBytes);
	        return sizeLength;
    	}catch(IOException ioe){
    		throw ioe;
    	}catch(Exception ex){
    		throw new IOException(ex.getLocalizedMessage());
    	}
    }

    public static void writeBytes(OutputStream out, int lengthOffset, int lengthSize, byte[] data) throws IOException{
    	for(int i=0; i<lengthOffset; i++){
			out.write(0);
		}
    	
    	String strDataLen = String.valueOf(lengthSize + data.length);
    	if(strDataLen.length() > lengthSize){
    		throw new IllegalArgumentException("Data length is lager than specified length size");
    	}
    	out.write(PadUtil.fillByte(strDataLen.getBytes(), lengthSize, '0', true));
//		out.write(Util.fillZero(strDataLen, lengthSize).getBytes());
		out.write(data);
		out.flush();
    }
    
//    public static byte[] readBytes(InputStream in, 
//    		int lengthOffset, int lengthSize, int buff_size, boolean execludeLengthSize) throws IOException{
//    	if(buff_size <= 0) buff_size = ChannelConstants.DefaultValue.BUFF_SIZE;
//        ByteBuffer byteBuf = ByteBuffer.allocate(buff_size);
//        byteBuf.setAutoExpand(true);
//        
//        int sizeLength = writeByteBuffer(in, byteBuf, lengthOffset, lengthSize, buff_size, execludeLengthSize);
//        
//        byte[] bytes = new byte[sizeLength];
//        byteBuf.get(bytes);
//        byteBuf.clear();
//        
//        return bytes;
//    }
    
    public static byte[] readBytes(InputStream in, 
    		int lengthOffset, int lengthSize, int buff_size, boolean execludeLengthSize) throws IOException{
    	if(buff_size <= 0) buff_size = BUFF_SIZE;
    	ByteArrayOutputStream baos = new ByteArrayOutputStream(buff_size);
        
//        int sizeLength = 
    	writeByteArrayOutputStream(in, baos, lengthOffset, lengthSize, buff_size, execludeLengthSize);
        
        return baos.toByteArray();
    }
    
	public static byte[] readBytes(InputStream in, int len) throws IOException{
	    return readBytes(in, len, BUFF_SIZE);
	}
	
	public static byte[] readBytes(InputStream in, int len, int buff_size) throws IOException{
		if(len < 0){
			throw new IllegalArgumentException("Length for reading is invalid(must be lager than 0) : " + len);
		}
		
	    if(len < buff_size){
	        buff_size = len;
	    }
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(buff_size);
	    
		int value = 0;
		int read_bytes = 0;
		while (read_bytes < len) {
			int ramained = len - read_bytes;
			if(buff_size > ramained){
		        buff_size = ramained;
		    }
		    byte[] buffer = new byte[buff_size];
		    
		    value = in.read(buffer, 0, buffer.length);
		    if(value == -1){
		    	throw new EOFException("End of stream, Read bytes are insufficient - required : " + len + ", read : " + read_bytes);
//		    	return null;
		    }
		    baos.write(buffer, 0, value);
		    read_bytes += value;
		}
	    return baos.toByteArray();
		
//		DataInputStream dis = new DataInputStream(in);
//        byte[] buffer = new byte[len];
//        dis.readFully(buffer);
//        return buffer;
	}

	/**
	 * <pre>
	 * 입력값인 InputStream 객체에서 Read한 데이터를 입력값인 OutputStream 객체에 Write
	 * </pre>
	 * @param in_stream InputStream 객체
	 * @param out_stream OutputStream 객체
	 * @throws IOException
	 */
	public static void writeData(InputStream in_stream, OutputStream out_stream) throws IOException{
		writeData(in_stream, out_stream,  BUFF_SIZE);
	}
	
	/**
	 * <pre>
	 * 입력값인 InputStream 객체에서 Read한 데이터를 입력값인 사이즈의 버퍼를 사용하여 입력값인 OutputStream 객체에 Write.
	 * </pre>
	 * @param in_stream InputStream 객체
	 * @param out_stream OutputStream 객체
	 * @param buff_size 버퍼 사이즈
	 * @throws IOException
	 */
	public static void writeData(InputStream in_stream, OutputStream out_stream, int buff_size) throws IOException{
		if(buff_size <= 0){
			byte[] buffer = new byte[in_stream.available()];
			in_stream.read(buffer);
			out_stream.write(buffer);
		    out_stream.flush();
		}else{
			byte[] buffer = new byte[buff_size];
			int value = 0;
			
			while ((value = in_stream.read(buffer, 0, buffer.length)) != -1) {
			    out_stream.write(buffer, 0, value);
			    out_stream.flush();
			}
		}
	}
	
	public static void writeData(byte[] input, OutputStream out_stream, int buff_size) throws IOException{
		if(buff_size <= 0){
			out_stream.write(input);
		    out_stream.flush();
		}else{
			ByteArrayInputStream bais = new ByteArrayInputStream(input);
			byte[] buffer = new byte[buff_size];
			int value = 0;
			
			while ((value = bais.read(buffer, 0, buffer.length)) != -1) {
			    out_stream.write(buffer, 0, value);
			    out_stream.flush();
			}
		}
	}
	
	public static void writeData(String input, OutputStream out_stream) throws IOException{
	    writeData(input.getBytes(), out_stream, BUFF_SIZE);
	}
	
	public static void writeData(byte[] input, OutputStream out_stream) throws IOException{
		writeData(input, out_stream, BUFF_SIZE);
	}
	
	public static void writeData(String input, OutputStream out_stream, int buff_size) throws IOException{
	    writeData(input.getBytes(), out_stream, buff_size);
	}
	
//	public static void writeData(byte[] input, OutputStream out_stream, int buff_size) throws IOException{
//		writeData(new ByteArrayInputStream(input), out_stream, buff_size);
////		out_stream.write(input);
//	}
	
	/**
	 * <pre>
	 * 입력값인 디렉토리의 입력값인 파일명에 해당하는 파일 데이터를 읽어 byte 배열로 리턴
	 * </pre>
	 * @param dir 디렉토리
	 * @param fileName 파일명
	 * @return byte 배열의 파일 데이터
	 * @throws IOException
	 */
	public static byte[] getBytes(String dir, String fileName) throws IOException{
		File file = new File(dir, fileName);
		return getBytes(file);
	}
	
	public static byte[] getBytes(File file) throws IOException{
	    BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] result = getBytes(fis);
	    close(fis);
	    return result;
	}
	
	public static byte[] getBytes(InputStream is) throws IOException{
		return getBytes(is, BUFF_SIZE);
	}
	
	public static byte[] getBytes(InputStream is, int inputBuffSize) throws IOException{
		if(inputBuffSize <= 0){
			byte[] result = new byte[is.available()];
			is.read(result);
			return result;
		}else{
		    ByteArrayOutputStream baos = new ByteArrayOutputStream(inputBuffSize);
			try{
			    writeData(is, baos, inputBuffSize);
				byte[] result = baos.toByteArray();
				return result;
			}finally{
			    close(baos);
			}
		}
//	    BufferedReader in = new BufferedReader(new InputStreamReader(is) );
//	    StringBuilder sbuf = new StringBuilder();
//        while (true)
//        {
//            String line = in.readLine();
//            if(line == null || line.equals( "" )) break;
//            sbuf.append(line + System.getProperty("line.separator"));
//        }
//        return sbuf.toString().getBytes();
	}
	
	/**
	 * <pre>
	 * 입력값인 디렉토리의 입력값인 파일명에 해당하는 파일 데이터를 읽어 문자열로 리턴
	 * </pre>
	 * @param dir 디렉토리
	 * @param fileName 파일명
	 * @return 문자열의 파일 데이터
	 * @throws IOException
	 */
	public static String getText(String dir, String fileName) throws IOException{
		return new String(getBytes(dir, fileName));
	}
	
	public static String getText(File file) throws IOException{
		return new String(getBytes(file));
	}
	
	public static String getText(InputStream is) throws IOException{
		return new String(getBytes(is));
	}
	/**
	 * <pre>
	 * 입력값인 디렉토리의 입력값인 파일명에 해당하는 파일에 입력값인 byte 배열의 데이터를 Write.
	 * </pre>
	 * @param dir 디렉토리
	 * @param fileName 파일명
	 * @param data byte 배열의 데이터
	 * @throws IOException
	 */
	public static void writeFile(String dir, String fileName, byte[] data) throws IOException{
		File file = new File(dir, fileName);
		writeFile(file, data);
	}
	
	public static void writeFile(String filePath, byte[] data) throws IOException{
		File file = new File(filePath);
		writeFile(file, data);
	}
	
	public static void writeFile(File file, byte[] data) throws IOException{
		BufferedOutputStream fos = null;
		try{
			fos = new BufferedOutputStream(new FileOutputStream(file));
			writeData(data, fos);
		}finally{
			close(fos);
		}
	}
	
//	/**
//	 * <pre>
//	 * InputStream, OutputStream, Reader, Writer 객체에 대한 close 수행
//	 * </pre>
//	 * @param obj InputStream, OutputStream, Reader, Writer 객체
//	 */
//	public static void close(Object obj){
//		if(obj != null){
//			try{
//				if(obj instanceof InputStream){
//					((InputStream)obj).close();
//				}else if(obj instanceof OutputStream){
//					((OutputStream)obj).close();
//				}else if(obj instanceof Reader){
//					((Reader)obj).close();
//				}else if(obj instanceof Writer){
//					((Writer)obj).close();
//				}else if(obj instanceof Socket){
//					((Socket)obj).close();
//				}else if(obj instanceof ServerSocket){
//                    ((ServerSocket)obj).close();
//                }else{
//					throw new IllegalArgumentException("'" + obj.getClass().getName() + "' is invalid type");
//				}
//				obj = null;
//			}catch(Exception ex){
//                
//			}
//		}
//	}
	
	public static void close(InputStream is){
		if(is != null){
			try{
				is.close();
				is = null;
			}catch(Exception ex){
                
			}
		}
	}
	public static void close(ResultSet rs){
		if(rs != null){
			try{
				rs.close();
				rs = null;
			}catch(Exception ex){
                
			}
		}
	}
	public static void close(Statement stmt){
		if(stmt != null){
			try{
				stmt.close();
				stmt = null;
			}catch(Exception ex){
                
			}
		}
	}
	
	public static void close(OutputStream os){
		if(os != null){
			try{
				os.close();
				os = null;
			}catch(Exception ex){
                
			}
		}
	}
	
	public static void close(Reader reader){
		if(reader != null){
			try{
				reader.close();
				reader = null;
			}catch(Exception ex){
                
			}
		}
	}
	
	public static void close(Writer writer){
		if(writer != null){
			try{
				writer.close();
				writer = null;
			}catch(Exception ex){
                
			}
		}
	}

	public static void close(Socket socket){
		if(socket != null){
			try{
				socket.close();
				socket = null;
			}catch(Exception ex){
                
			}
		}
	}
	
	public static void close(Connection conn){
		if(conn != null){
			try{
				conn.close();
				conn = null;
			}catch(Exception ex){
                
			}
		}
	}
	
	public static void close(ServerSocket ssocket){
		if(ssocket != null){
			try{
				ssocket.close();
				ssocket = null;
			}catch(Exception ex){
                
			}
		}
	}
	
//	public static void dispose(Socket socket){
//        
//        if (socket != null && !socket.isClosed()) {
//        	try {
//        		socket.shutdownInput();
//        	} catch (IOException e) {
//            }
//        	try {
//        		socket.shutdownOutput();
//        	} catch (IOException e) {
//            }
//        	try {
//        		socket.close();
//        	} catch (IOException e) {
//            }
//        }
//        
//    }

	public static void main(String[] arg){
		try{
//			String text = getText("D:/eclipse/channel_workspace/stmr_channel/temp", "PLAA1010I0.txt");
//			System.out.println("[Result]" + text);
		    System.out.println(InetAddress.getLocalHost());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
