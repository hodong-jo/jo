package vo;

import java.sql.Date;

public class Test {
	private String ID;
	private String NAME;
	private Date TES;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public Date getTES() {
		return TES;
	}
	public void setTES(Date tES) {
		TES = tES;
	}
	@Override
	public String toString() {
		return "Test [ID=" + ID + ", NAME=" + NAME + ", TES=" + TES + "]";
	}
	
	
}
