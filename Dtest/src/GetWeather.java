import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetWeather {

	public static void main(String[] args) {
		String areaTop = "서울";
		String areaMdl = "구로구";
		String areaLeaf = "구로3동";
		
		String[] location = {areaTop,areaMdl,areaLeaf};
		String[] loc = {"top","mdl.","leaf."};
		
		String code = "";

		for(int i = 0; i < location.length; i++) {
			code = getCode(code, location[i], loc[i]);
		}
		
		System.out.println("좌표 : " + code);
		
		// api호출
		HashMap<String,String> baseDateTime = getBaseDateTime();
		String baseDate = baseDateTime.get("baseDate");
		String baseTime = baseDateTime.get("baseTime");
		String fcstDate = baseDateTime.get("fcstDate");
		String fcstTime = baseDateTime.get("fcstTime");
		
		apiCall(code, baseDate, baseTime, fcstDate, fcstTime);
		
	}
	
	private static String apiCall(String coordinates, String baseDate, String baseTime, String fcstDate, String fcstTime) {
		URL url;
		BufferedReader br;
		InputSource is;
		
		String serviceKey = "F1LuemYs%2B52ju3Pyjwz7brAXGrZifXV3cBrmbmez%2Be2NjDSf9idvtTx3s%2FrXY9eHLNlV05PzazM%2Bjb2kOV56iA%3D%3D";
		String dataType = "XML";
		
		int div = coordinates.indexOf(",");
		String nx = coordinates.substring(0,div);
		String ny = coordinates.substring(div+1);
		
		String str = "";
		
		JsonParser parser;
		JsonArray jsonArray;
		JsonObject jsonObject;
		
		try {
			url = new URL("http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey="+serviceKey+"&pageNo=1&numOfRows=3000&dataType="+dataType+"&"
					+ "base_date="+baseDate+"&base_time="+baseTime+"&nx="+nx+"&ny="+ny+"");
			
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line;
			while ((line = br.readLine()) != null) {
				str += line;
			}
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(str);
			ByteArrayInputStream input = new ByteArrayInputStream(stringBuilder.toString().getBytes("UTF-8"));
			Document doc = builder.parse(input);

			XPath xPath = XPathFactory.newInstance().newXPath();
			
			NodeList nodeList = (NodeList) xPath.evaluate("//item", doc, XPathConstants.NODESET);
			
			String T3H = "";
			String SKY = "";
			for(int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				String eCategory = element.getElementsByTagName("category").item(0).getTextContent();
				String eFcstTime = element.getElementsByTagName("fcstTime").item(0).getTextContent();
				String eFcstDate = element.getElementsByTagName("fcstDate").item(0).getTextContent();
				
				if(eCategory.equals("T3H") && eFcstTime.equals(fcstTime) && eFcstDate.equals(fcstDate)) {
					T3H = element.getElementsByTagName("fcstValue").item(0).getTextContent();
				}
				if(eCategory.equals("SKY") && eFcstTime.equals(fcstTime) && eFcstDate.equals(fcstDate)) {
					SKY = element.getElementsByTagName("fcstValue").item(0).getTextContent();
					if (SKY.equals("1")) {
						SKY = "맑음";
					} else if (SKY.equals("3")) {
						SKY = "구름많음";
					} else if (SKY.equals("4")) {
						SKY = "흐림";
					}
				}
//				if(eCategory.equals("TMN") && eFcstDate.equals(fcstDate)) {
//					System.out.println("최저기온 : " + element.getElementsByTagName("fcstValue").item(0).getTextContent() + "℃");
//				}
//				if(eCategory.equals("TMX") && eFcstDate.equals(fcstDate)) {
//					System.out.println("최고기온 : " + element.getElementsByTagName("fcstValue").item(0).getTextContent()+ "℃");
//				}
//				System.out.println("category : " + element.getElementsByTagName("category").item(0).getTextContent()
//						+ ", fcsValue : " + element.getElementsByTagName("fcstValue").item(0).getTextContent()
//						+ ", fcstTime : " + element.getElementsByTagName("fcstTime").item(0).getTextContent());
			}
			return "현재날씨는 " + SKY + " 기온은 "+ T3H + "℃ 입니다.";
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private static HashMap getBaseDateTime() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String baseTime = "";
		String baseDate = "";
		String fcstTime = "";
		String fcstDate = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.DATE, 8);
//		calendar.set(Calendar.HOUR_OF_DAY,23);
		int t = calendar.get(Calendar.HOUR_OF_DAY);
		
		calendar.set(Calendar.HOUR_OF_DAY, (t - (t % 3) + 3));
		fcstTime = String.format("%02d00", calendar.get(Calendar.HOUR_OF_DAY));
		fcstDate = dateFormat.format(calendar.getTime());
		
		if(t == 23){
			calendar.set(calendar.DATE, calendar.get(Calendar.DATE)-1);
		}
		
		calendar.set(Calendar.HOUR_OF_DAY, (t - (t + 1) % 3) -3);
		baseTime = String.format("%02d00", calendar.get(Calendar.HOUR_OF_DAY));
		baseDate = dateFormat.format(calendar.getTime());
		
		hashMap.put("baseTime", baseTime);
		hashMap.put("baseDate", baseDate);
		hashMap.put("fcstTime", fcstTime);
		hashMap.put("fcstDate", fcstDate);
		
		System.out.println("BaseTime : " + baseTime + "\nBaseDate : " + baseDate + "\nfcstTime : " + fcstTime + "\nfcstDate : " + fcstDate);
		
		return hashMap;
	}
	
	private static String getCode(String code, String location, String loc) {
		URL url;
		BufferedReader br;
		
		String json;
		String x;
		String y;

		JsonParser parser;
		JsonArray jsonArray;
		JsonObject jsonObject;
		
		try {
			url = new URL("https://www.kma.go.kr/DFSROOT/POINT/DATA/" + loc + code + ".json.txt");
			
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			json = br.readLine().toString();
			
			parser = new JsonParser();
			jsonArray = (JsonArray) parser.parse(json);
			jsonObject = new JsonObject();
			
			for(int i = 0; i < jsonArray.size(); i++) {
				jsonObject = (JsonObject)jsonArray.get(i);
				
				if(jsonObject.get("value").getAsString().contains(location)) {
					code = jsonObject.get("code").getAsString();
					
					if(jsonObject.has("x") && jsonObject.has("y")) {
						x = jsonObject.get("x").getAsString();
						y = jsonObject.get("y").getAsString();
						String coordinates = x + "," + y;
						
						return coordinates;
					}
					return code;
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
