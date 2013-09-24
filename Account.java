public class Account{
	
	private String _accountNumber = "";
	public String getAccountNumber(){ return _accountNumber; }
	public void setAccountNumber(String accountNumber){ _accountNumber = accountNumber;}

	private String _accountType = "";
	public String getAccountType(){ return _accountType; }
	public void setAccountType(String accountType){ _accountType = accountType.toLowerCase(); }	

	private String _customerName = "";
	public String getCustomerName(){ return _customerName; }
	public void setCustomerName(String customerName){ _customerName = customerName; }		

	private String _customerType = "";
	public String getCustomerType(){ return _customerType; }
	public void setCustomerType(String customerType){ _customerType = customerType.toLowerCase(); }	

	private double _accountBalance = 0;
	public double getAccountBalance(){ return _accountBalance; }
	public void setAccountBalance(String accountBalance){ _accountBalance = Double.parseDouble(accountBalance); }

	private double _accountFee = 0;
	public double getAccountFee(){ return _accountFee; }
	public void setAccountFee(String accountFee){ _accountFee = Double.parseDouble(accountFee); }
	
}
