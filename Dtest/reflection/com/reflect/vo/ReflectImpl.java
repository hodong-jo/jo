package com.reflect.vo;

public class ReflectImpl implements ReflectTest{
	private String str;
	private int number;
	
	public ReflectImpl() {
		this.str = System.getProperty("user.name");
	}
	public ReflectImpl(String str) {
		this.str = str;
	}
	public ReflectImpl(int a) {
		this.number = a;
	}

	@Override
	public String getString() {
		return this.str + "~~";
	}

	@Override
	public String getString(String str) {
		return this.str + ", parameter" + str;
	}

	@Override
	public int getInteger(int a) {
		return this.number + a;
	}
	//int first,second;
	//int result = 0;
	//String a;
	/*public ReflectImpl(){
		this.result = System.getProperties();
	}
	public ReflectImpl(first,second){
		
	}*/
	/*public int setOperands(int first, int second) {
		this.first = first;
		this.second = second;
		return 5;
	}*/
	

	

}
