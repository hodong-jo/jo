
public class User {
	private String name;
	private int age;
	private boolean isNew;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public User(String name, int age,boolean isNew) {
		this.name = name;
		this.age = age;
		this.isNew = isNew;
	}

	
}
