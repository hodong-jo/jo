import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class fileNameAppend {
	public static void main(String[] args) {
		String path = "C:\\Users\\USER\\Downloads\\Design\\AGENT\\FST";
		String chpath = "C:\\Users\\USER\\Downloads\\Design\\AGENT\\FST_";
		File fileDir = new File(path);
		File[] fileList = fileDir.listFiles();
		
		List fileName = new ArrayList();
//		List length = new ArrayList();
		
		List fileStr = new ArrayList<String>();
		
		
		System.out.println("FileCount : " + fileList.length);
		int count = 0;
		for (File file : fileList) {
			Path filePath = Paths.get(file.getAbsolutePath());
			Charset cs = StandardCharsets.UTF_8;
			
			List<String> list = new ArrayList<String>();
			
			try {
				fileName.add(file.getName());
				list = Files.readAllLines(filePath,cs);
//				System.out.println(list.get(1).substring(0,list.get(1).indexOf("name=\"파일유무체크BindingPort\"><file:address")));
//				System.out.println(list.size());
				String str = list.get(4);
				int a = str.indexOf("name=\"파일유무체크BindingPort\"><file:address fileName=");
//				System.out.println(a);
//				System.out.println(str);
//				System.out.println(list.get(0).indexOf("contains(//fileName/text(),'"));
				int index = list.get(0).indexOf("contains(//fileName/text(),'");
//				System.out.println(list.get(0).substring(index+28,index+33));
				String itf = ".*"+list.get(0).substring(index+28,index+33)+".*";
//				System.out.println(str.substring(a+48,a+50));
				String rplStr;
				rplStr = list.get(4).replace("name=\"파일유무체크BindingPort\"><file:address fileName=\"\"", "name=\"파일유무체크BindingPort\"><file:address fileName=\""+itf+"\"");
//				System.out.println(rplStr);
				list.set(4, rplStr);
//				System.out.println(rplStr.substring(a+48,a+52));
				for(int i = 0; i < list.size(); i++) {
					fileStr.add(list.get(i));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(fileStr.size());
		}
//		chpath
//		File file
		BufferedWriter writer;
		for (int i = 0; i < fileList.length; i++) {
			try {
				writer = new BufferedWriter(new FileWriter(chpath+"/"+fileName.get(i)));
				for(int j = 0; j < 5; j++) {
					writer.append(fileStr.get(count).toString());
					writer.append("\n");
					count++;
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
