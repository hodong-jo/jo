
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatCount {

	public static void main(String[] args) {
		String path = "C:\\megawareFSC\\DATA\\sucsess\\KMC\\20201028";
		File fileDir = new File(path);
		File[] fileList = fileDir.listFiles();
		
		List fileName = new ArrayList();
		List length = new ArrayList();
		System.out.println("FileCount : " + fileList.length);
		for (File file : fileList) {
			Path filePath = Paths.get(file.getAbsolutePath());
			Charset cs = StandardCharsets.ISO_8859_1;
			List<String> list = new ArrayList<String>();
			try {
				list = Files.readAllLines(filePath,cs);
				if(file.length() == 0) {
					fileName.add(file.getName());
					length.add("0");
					
					System.out.println(list.size());
				}else {
					String lastLine = list.get(list.size()-1);
//					String lastLine = list.get(0);
//					System.out.println(lastLine.length());
					fileName.add(file.getName());
//					System.out.println(lastLine.length());
//					System.out.println("index : " + lastLine.lastIndexOf("^||"));
					int index = lastLine.lastIndexOf("^||");
					String len = lastLine.substring(0,index);
					index = len.lastIndexOf("^||");
//					System.out.println(lastLine.substring(index+3,index+39));
//					System.out.println(lastLine.substring(0,index));
//					System.out.println(index);
//					System.out.println(lastLine.substring(index,index+2));
//					
//					length.add(lastLine.substring(0,lastLine.indexOf(":||")));
					length.add(lastLine.substring(index+3,index+39));
				
	
					System.out.println(list.size());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		for(int i = 0; i < fileName.size(); i++) {
//			  System.out.println("-----------------------");
	    	  System.out.println(fileName.get(i));
	    	 
	      }
		for(int i = 0; i < length.size(); i++) {
//	    	  
			try {
				System.out.println(Integer.parseInt((String) length.get(i)));
			} 
			catch (Exception e) {
				System.out.println(length.get(i));
				System.out.println("-----------------------");
			}
	    	  
	    	 
	      }
		

	}

}
