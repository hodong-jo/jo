import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class GsonObject {

	public static void main(String[] args) {
		FileWriter fw = null;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		JsonArray array = new JsonArray();
		List<User> users = new ArrayList<User>();
		
		users.add(new User("hodong",26,true));
		users.add(new User("BBB",24,true));
		
//		JsonObject json = new JsonObject();
//		json.addProperty("User", "hodong");
//		json.addProperty("age", 26);
//		json.addProperty("isNew", true);
//		array.add(json);
//		
//		JsonObject json2 = new JsonObject();
//		json2.addProperty("User", "BBB");
//		json2.addProperty("age", 24);
//		json2.addProperty("isNew", true);
//		array.add(json2);
		
		try {
			fw = new FileWriter("./json/megatus.txt");
			gson.toJson(users, fw);
			Logger logger = Logger.getLogger("syncLogTestLogger");
			logger.warn("megatus.txt ����Ϸ�");
		
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw != null) {try {fw.close();} catch (Exception e2) {}}
		}
	}

}
