import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DGMTest {
	 public static void main(String[] args) {
		  String dirPath = "D:\\금융위\\Design\\HUB\\KSD";
	      File dir = new File(dirPath);
	      File []fileList = dir.listFiles();
	      List<String> list1 = new ArrayList<String>();
          List<String> list2 = new ArrayList<String>();
          List<String> list3 = new ArrayList<String>();
          List<String> list4 = new ArrayList<String>();
          
	      for(File file : fileList) {
	    		  Path filePath = Paths.get(file.getAbsolutePath());
		           // 캐릭터셋 지정
		           Charset cs = StandardCharsets.UTF_8;
		           //파일 내용담을 리스트
		           List<String> list = new ArrayList<String>();
		           
		           try{
			            list = Files.readAllLines(filePath,cs);
			        }catch(IOException e){
			            e.printStackTrace();
			        }
			        for(String readLine : list){
			        	if(readLine.contains("<m:diagram xmlns:m=\"http://megaengine.megatus.com/diagram\"")) {
			        		
			        		String a = readLine.substring(readLine.indexOf("name")+8,readLine.indexOf("path")-2);
//			        		System.out.print(readLine.substring(readLine.indexOf("name")+6,readLine.indexOf("path")-2)+" : " );
			        		list1.add(a);
			        	}
			        	if(readLine.contains("<m:description>")) {
			        		
			        		String a = readLine.substring(readLine.indexOf("<m:description>")+15,readLine.indexOf("</m:description>"));
//			        		System.out.print(readLine.substring(readLine.indexOf("name")+6,readLine.indexOf("path")-2)+" : " );
			        		list4.add(a);
			        	}
			           if(readLine.contains("<jdbc:query jdbcType=\"Datasource\">"))
			              if(!readLine.contains("_LOG")) {
//			                 System.out.println(readLine.substring(readLine.indexOf("\">")+2,readLine.lastIndexOf("<")));
			                 list2.add(readLine.substring(readLine.indexOf("\">")+2,readLine.lastIndexOf("<")));
			                 
			                 String a = readLine.substring(readLine.indexOf("INSERT INTO")+12,readLine.indexOf(" (ESB_TRNSN_ID"));
			                 list3.add(a);
			              }
			        }
	      }
		      for(int i = 0; i < list1.size(); i++) {
		    	  System.out.println(list1.get(i));
		    	 
		      }
		      System.out.println("-------------------------------");
		      for(int i = 0; i < list2.size(); i++) {
		    	  System.out.println(list2.get(i));
		    	  
		      }
		      System.out.println("-------------------------------");
		      for(int i = 0; i < list3.size(); i++) {
		    	  System.out.println(list3.get(i));
		      }
		      System.out.println("-------------------------------");
		      for(int i = 0; i < list3.size(); i++) {
		    	  System.out.println(list4.get(i));
		      }
	    	  
	         
	 }
	      

}
