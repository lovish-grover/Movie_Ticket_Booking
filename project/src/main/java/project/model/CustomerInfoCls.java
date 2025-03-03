package project.model;

public class CustomerInfoCls {
	int cusId;
	String cusNm;
	String cusEml;
	String cusPass;
	
	public CustomerInfoCls() {
		super();
	}
	public CustomerInfoCls(int cusId,String cusNm) {
		super();
		this.cusId = cusId;
		this.cusNm = cusNm;
		
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusNm() {
		return cusNm;
	}
	public void setCusNm(String cusNm) {
		this.cusNm = cusNm;
	}
}
