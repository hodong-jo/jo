package com.thread;

class Park extends Thread{
	public void run() {
//		synchronized (NotSyncMain.myBack) {
			NotSyncMain.myBack.saveMoney(3000);
			System.out.println("saveMoney(3000):" + NotSyncMain.myBack.getMoney());
//		}
		
	}
}
class ParkWife extends Thread{
	public void run() {
//		synchronized (NotSyncMain.myBack) {
		NotSyncMain.myBack.minusMoney(1000);
		System.out.println("minusMoney(1000):" + NotSyncMain.myBack.getMoney());
//		}
	}
}
public class NotSyncMain {
	public static Bank myBack= new Bank();
	public static void main(String[] args) {
		System.out.println("¿ø±Ý: "+myBack.getMoney());
		Park p = new Park();
		ParkWife w = new ParkWife();
		p.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {e.printStackTrace();}
		w.start();

	}

}
