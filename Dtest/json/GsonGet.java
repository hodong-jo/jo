import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonGet{

	public static void main(String[] args)  {
		FileWriter fw = null;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<User> users = new ArrayList<User>();
		
		JsonParser parser = new JsonParser();
//		JsonObject jsonObject = new JsonObject();
//		JsonArray array = new JsonArray();
		
		Type type = new TypeToken<List<User>>() {}.getType();
		
		try {
//			Object obj = parser.parse(new FileReader("./json/megatus.txt"));
//			JsonElement jsonElement = (JsonElement) obj;
			
			Object obj = parser.parse(new FileReader("./json/megatus.txt"));
			JsonElement jsonElement = (JsonElement) obj;
			users = gson.fromJson(jsonElement, type);
			for(User u : users) {
				if(u.getName().equals("hodong"))
					System.out.println("hodong");
			}
			users.get(1).setName("AAA");
			users.add(new User("chanyoung,",26,true));
			
//			array = jsonElement.getAsJsonArray();
//			jsonObject = (JsonObject) array.get(0);
//			System.out.println(jsonObject.get("name"));
//			
//			jsonObject = (JsonObject) array.get(1);
//			jsonObject.addProperty("User", "AAA");
//			
//			JsonObject jsonObject2 = new JsonObject();
//			jsonObject2.addProperty("User", "chanyoung");
//			jsonObject2.addProperty("age", 26);
//			jsonObject2.addProperty("isNew", true);
//			array.add(jsonObject2);
			
			fw = new FileWriter("./json/newmegatus.txt");
			gson.toJson(users, fw);
			Logger logger = Logger.getLogger("syncLogTestLogger");
			logger.warn("newmegatus.txt 저장완료");
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( fw != null) {try {fw.close();} catch (Exception e2) {}}
		}
	}

}
