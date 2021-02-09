import java.io.File;
import java.util.ArrayList;

public class FolderTest {

	public static void main(String[] args) {
		String path = "C:\\megawareFSC\\DATA\\sucsess\\KSD\\0data";
	      File dir = new File(path);
	      File []fileList = dir.listFiles();
	      
	      ArrayList nameList = new ArrayList<String>();
	      
	      for(File f : fileList) {
	         if(f.isFile()) {
	            String fileName = f.getName();
	            String subFileName = fileName.substring(0,fileName.lastIndexOf('_'));
	            
	            if(!nameList.contains(subFileName)) {
	            	if(fileName.contains("20201023")) {
	            		nameList.add(subFileName);
	            	}
//	               nameList.add(subFileName);
	            }
	         }
	      }
	      
	      for(int i = 0; i< nameList.size() ; i++) {
	    	  System.out.println(nameList.get(i));
	      }
//	      System.out.println(nameList+"");
	      System.out.println(nameList.size());

	}

}
