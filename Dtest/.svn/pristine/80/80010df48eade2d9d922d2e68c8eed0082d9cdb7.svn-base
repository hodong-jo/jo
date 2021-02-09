
public class VirtualEnvThread extends Thread{

	StringBuffer buf = new StringBuffer();
	
	public void run() {
		System.out.println("VirtualEnvThread Start");
		while(true) {
			if(buf.length() > 100000) {
				System.out.println("buffer clear");
				buf.delete(0, buf.length());
			}
			try {
				for(int i =0; i < 100; i++) {
					buf.append("aaaaaaaaaaaaaaa");
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
