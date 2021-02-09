import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileCopyTest {

	public static void copyFile(File srcFile, File tgtFile){
		
		long start = System.currentTimeMillis();
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try{
			
			fis = new FileInputStream(srcFile);
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream(tgtFile, false);
			bos = new BufferedOutputStream(fos);
			
			byte[] data = new byte[1024];
//			byte[] data = new byte[fis.available()];//filesize랑 비슷한기능
			int readSize = bis.read(data);
			
			if(data.length != readSize) throw new IllegalStateException("Available size not equals read size:" + data.length + "!=" + readSize);
			
			while (bis.read(data, 0, data.length) != -1) {
				bos.write(data);
			}
			
//			int i = 0;
//			Date data = new Date();
			
//			while ((i=bis.read()) != -1) {//1바이트씩읽어서 버퍼에담는다
//				bos.write(i);
//			}
			
			bos.write(data);
			bos.flush();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(fos != null){
				try {
					fos.close();
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			long elapsed = System.currentTimeMillis() - start;
			
			System.out.println("Elapsed:" + elapsed + " ms.");
			
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			String tgtFileName = "output.txt";
			
			File srcFile = new File(FileCopyTest.class.getResource("/input.txt").getFile());
			
			if(!srcFile.exists()) throw new FileNotFoundException(srcFile.getPath());
			
			System.out.println("Source file:" + srcFile.getPath());
			
			File tgtDir = new File(FileCopyTest.class.getResource("/target").getFile());
			
			if(!tgtDir.exists()) throw new FileNotFoundException(tgtDir.getPath());
			if(!tgtDir.isDirectory()) throw new IllegalStateException("Not directory:" + tgtDir.getPath());
			
			System.out.println("Target directory:" + tgtDir.getPath());
			
			File tgtFile = new File(tgtDir, tgtFileName);
			if(tgtFile.exists()) tgtFile.delete();
			
			copyFile(srcFile, tgtFile);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

}
