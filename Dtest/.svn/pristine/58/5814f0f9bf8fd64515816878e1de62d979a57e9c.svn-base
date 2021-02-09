package socket;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.google.gson.stream.JsonWriter;

import utils.IOUtil;

public class DongClient {
	static Logger logger = Logger.getLogger(TestClient.class);

	public static void main(String[] args) {
		InetSocketAddress endpoint = new InetSocketAddress("localhost", 9000);
		Socket client = new Socket();
		FileWriter fw = null;
		OutputStream out = null;
		InputStream in = null;
		int connectTimeout = 1000; //milliseconds
		try {
			client.connect(endpoint, connectTimeout);
			logger.info("Connected : [" + endpoint + "]");
			
			out = client.getOutputStream();
			in = client.getInputStream();
			
			String dongName = "구로";
			String check = "1";
			byte[] sendData = dongName.getBytes();
			int len = sendData.length;
			String lenStr = String.format("%04d", len);
			lenStr += check;
			out.write(lenStr.getBytes());
			out.write(sendData);
			out.flush();
			
			logger.info("Send : [" + "Send success" + "]");
			
			fw = new FileWriter("dong.json");
			byte[] header = new byte[5];
			
			JsonWriter writer = new JsonWriter(fw);
			writer.setIndent("	");//들여쓰기 설정해서 prettyprint
			writer.beginArray();
					
			/*JsonFactory jsonfactory = new JsonFactory();
			JsonGenerator generator = jsonfactory.createJsonGenerator(fw);
			generator.useDefaultPrettyPrinter();
			generator.writeStartArray();*/ //jackson
			
			while(true) {
				in.read(header);
				int length = Integer.parseInt(new String(header).substring(0,4));
				int recvCheck = Integer.parseInt(new String(header).substring(4,5));
				
				byte[] recvData = new byte[length];
				in.read(recvData);
				
				String data = new String(recvData);
				StringTokenizer st = new StringTokenizer(data,",");
				
				writer.beginObject();
				writer.name("seq").value(Integer.parseInt((String) st.nextElement()));
				writer.name("zipcode").value((String) st.nextElement());
				writer.name("sido").value((String) st.nextElement());
				writer.name("gugun").value((String) st.nextElement());
				writer.name("dong").value((String) st.nextElement());
				writer.name("bunji").value((String) st.nextElement());
				writer.endObject();
				
				/*generator.writeStartObject();
				generator.writeNumberField("seq", Integer.parseInt((String) st.nextElement()));
				generator.writeStringField("zipcode", (String) st.nextElement());
				generator.writeStringField("sido", (String) st.nextElement());
				generator.writeStringField("gugun", (String) st.nextElement());
				generator.writeStringField("dong", (String) st.nextElement());
				generator.writeStringField("bunji", (String) st.nextElement());
				generator.writeEndObject();*/ //jackson
				
				if(recvCheck == 1)
					break;
			}
			
			writer.endArray();
			/*generator.writeEndArray();
			generator.close();*/ //jackson
			
			logger.info("Recv : [" + "Recv success" + "]");
			
		} catch (FileNotFoundException e) {
			logger.error("Failed:", e);
		} catch (IOException e) {
			logger.error("Failed:", e);
		} finally {
			if(fw != null) { try { fw.close(); } catch (IOException e) { e.printStackTrace(); }}
			IOUtil.close(in);
			IOUtil.close(out);
			IOUtil.close(client);
			logger.info("Closed : [" + endpoint + "]");   
		}
	}
}
