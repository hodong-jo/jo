package socket;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class Tes {

	public static void main(String[] args) {
		Gson gson = new Gson();
		try {
//			FileReader fr = new FileReader("dong.txt");
//			System.out.println(gson.fromJson(fr, Dong.class));
			
			List<Dong> dong = new ArrayList<Dong>();
			JsonParser parser = new JsonParser();
			
			Type type = new TypeToken<List<Dong>>() {}.getType();
			
			Object obj = parser.parse(new FileReader("dong.txt"));
			JsonElement jsonElement = (JsonElement) obj;
			dong = gson.fromJson(jsonElement, type);
			for(Dong u : dong) {
				System.out.println(u);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		

	}

}
