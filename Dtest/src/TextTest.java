import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextTest {

	public static void main(String[] args) {
		
		File file = new File("D:\\aa.txt");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
//				System.out.println(line);
				System.out.println("INSERT INTO PF_MSG_FILTER(MF_REFVALUE,MF_COLUMNNAME,MF_ATTRNAME,MF_ATTRVALUE,MF_APPLY_SEQ,MF_TYPE) VALUES ('"+line+"','*','minOccurs','1','1','ATTR');");
//				System.out.println("INSERT INTO PF_MSG_FILTER(MF_REFVALUE,MF_COLUMNNAME,MF_ATTRNAME,MF_ATTRVALUE,MF_APPLY_SEQ,MF_TYPE) VALUES ('"+line+"','*,LAST_REVS_DTM','mw:delimiter',':||,NONE','1','ATTR');");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
