import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChkFilegenerate {

	public static void main(String[] args) {
		try {
			File file = new File("D:\\금융위\\ESB연계데이터\\KDB");
			String files[] = file.list();
			for(String fn : files) {
				if(fn.endsWith(".DAT")) {
					FileWriter fw = new FileWriter(file.getPath()+"\\"+fn.substring(0,fn.lastIndexOf("."))+".chk");
//					fw.write("");
					fw.close();
					System.out.println("�Ϸ�");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
