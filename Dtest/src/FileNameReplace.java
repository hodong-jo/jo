import java.io.File;

public class FileNameReplace {

	public static void main(String[] args) {
		
			String filePath = "D:/금융위/ESB연계데이터/FSS/20201016/";
			String filename = "20201110.chk";
			String newDate = "20201111.chk";
		
			File file = new File(filePath);
			String fileList[] = file.list();
			File[] files = file.listFiles();
			
			for(int i = 0; i < fileList.length; i++) {
				
				if(fileList[i].endsWith(filename)){
					String newFileName = fileList[i].replace(filename, newDate);
					if(files[i].renameTo(new File(filePath+newFileName))) {
						System.out.println(fileList[i]+" 변경완료");
					}
				}
				
			}

	}

}
