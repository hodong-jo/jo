package com.thread;

public class Bank {
	private int money = 10000;
	
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMoney() {
		return this.money;
	}
	public /*synchronized */void saveMoney(int save) {
		int m = this.getMoney();
		try {
			Thread.sleep(3000);
		}catch (InterruptedException e) {e.printStackTrace();}
		this.setMoney(m + save);
	}
	public void minusMoney(int minus) {
//		synchronized (this) {
			int m = this.money;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {e.printStackTrace();}
			this.setMoney(m-minus);
		}
			
//	}

}





