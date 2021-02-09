package mwtest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.megatus.megaengine.util.log.LoggerFactory;

public class JavaInvoke {
	private static Logger LOG = LoggerFactory.getLogger("com.megatus");
	
	public static void main(String[] args) throws Exception {
		execute("<item><msg>�ȳ�</msg><test>����</test><t>aaa</t></item>");
	}
	
	public static String execute(String param) throws Exception{
		LOG.info(param);
		String clientId = "p9pnwHfuvtRdMFIZ3GaM";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
        String clientSecret = "rNbnDyJ38g";//���ø����̼� Ŭ���̾�Ʈ ��ũ����";
        
        String text = null;
        String paramMsg = getParam(param);
        try {
        	text = URLEncoder.encode(paramMsg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
			throw new RuntimeException("�˻��� ���ڵ� ����",e);
		}
        
        Map<String, String> requestHeaders = new HashMap();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        
        String responsBody = post(apiURL,requestHeaders,text);
        
        LOG.info(responsBody);
        
        return msgToXml(responsBody);
	}
	
	private static String msgToXml(String responsBody) {
		JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(responsBody);
        JsonElement jsonElement = (JsonElement)jsonObject.get("message");
        String msg = jsonElement.getAsJsonObject().get("result").getAsJsonObject().get("translatedText").toString();
        msg = msg.replace("\"", "");
        LOG.info("msg : "+ msg);
        
        StringBuffer result = new StringBuffer();
        result.append("<result>")
        	.append(	"<msg>").append(msg).append("</msg>")
        	.append("</result>");
        
        LOG.info(result.toString());
        
        return result.toString();
	}
	
	private static String post(String apiUrl,Map<String, String> requestHeaders,String text) {
		HttpURLConnection con = connection(apiUrl);
		String postParams = "source=ko&target=en&text=" + text;
        try {
			con.setRequestMethod("POST");
	        for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
	        	con.setRequestProperty(header.getKey(),header.getValue());
	        }
	        // post request
	        con.setDoOutput(true);
	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(postParams);
	        wr.flush();
	        
	        int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // ���� ȣ��
                return readBody(con.getInputStream());
            } else {  // ���� �߻�
            	return readBody(con.getInputStream());
            }
		} catch (IOException e) {
			throw new RuntimeException("API ��û�� ���� ����", e);
		} finally {
			con.disconnect();
		}
	}
	
	private static HttpURLConnection connection(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL�� �߸��Ǿ����ϴ�." + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("������ �����߽��ϴ�." + apiUrl, e);
		}
	}
	
	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		BufferedReader lineReader = new BufferedReader(streamReader);
		StringBuilder responseBody = new StringBuilder();
		
		String line;
		try {
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody.toString();
	}
	
	private static String getParam(String param) throws Exception{
		String result = null;
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(param)));
		NodeList nodeList = (NodeList) xPath.evaluate("//item/*",document, XPathConstants.NODESET);
		System.out.println(nodeList.getLength());
		
		for(int i = 0; i < nodeList.getLength() ; i++) {
			Node node = nodeList.item(i);
			System.out.println(node.getNodeName());
			if(node.getNodeName().equals("msg")) {
				System.out.println(node.getTextContent());
				return nodeList.item(0).getTextContent();
			}
		}
		return result;
	}

}
