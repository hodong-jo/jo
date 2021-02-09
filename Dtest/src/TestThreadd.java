
public class TestThreadd {

	public static void main(String[] args) {
		System.out.println("Ω√¿€");
		Top t = new Top();
		Thread thd = new Thread(t);
//		thd.start();
		
		DerivedThread d = new DerivedThread();
		d.start();
		System.out.println("≥°");
	}

}

class Top implements Runnable{
	@Override
	public void run() {
		for(int i=0; i<50; i++)
			System.out.print(i+"\t");
	}
}

class DerivedThread extends Thread{
	public void run() {
		for(int i=0; i<50; i++)
			System.out.print(i+"\t");
	}
}