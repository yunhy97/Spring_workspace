package kr.co.jhta.form;

public class OrderForm {

	//주문정보 관련
	private String username;
	private String usertel;
	private long productNo;
	private String productName;
	private long productPrice;
	private long productDiscountPrice;
	private int amount;
	private long price;
	
	//결재관련
	private String payType;
	private String payAccount;
	
	//배송관련
	private String receiverUsername;
	private String receiverUserTel;
	private String recieverAddress;
	
	@Override
	public String toString() {
		return "OrderForm [username=" + username + ", usertel=" + usertel + ", productNo=" + productNo
				+ ", productName=" + productName + ", productPrice=" + productPrice + ", productDiscountPrice="
				+ productDiscountPrice + ", amount=" + amount + ", price=" + price + ", payType=" + payType
				+ ", payAccount=" + payAccount + ", receiverUsername=" + receiverUsername + ", receiverUserTel="
				+ receiverUserTel + ", recieverAddress=" + recieverAddress + "]";
	}

	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public long getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}


	public long getProductDiscountPrice() {
		return productDiscountPrice;
	}


	public void setProductDiscountPrice(long productDiscountPrice) {
		this.productDiscountPrice = productDiscountPrice;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertel() {
		return usertel;
	}
	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}
	public long getProductNo() {
		return productNo;
	}
	public void setProductNo(long productNo) {
		this.productNo = productNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}
	public String getReceiverUserTel() {
		return receiverUserTel;
	}
	public void setReceiverUserTel(String receiverUserTel) {
		this.receiverUserTel = receiverUserTel;
	}
	public String getRecieverAddress() {
		return recieverAddress;
	}
	public void setRecieverAddress(String recieverAddress) {
		this.recieverAddress = recieverAddress;
	}
	
	
}
