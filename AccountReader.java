import java.io.*;
import java.util.*;
import java.text.*;

class AccountReader {
	
	/**
	 *	AccountReader will asses the montly fees of Bank Customers
	 *	<p>
	 * 	Four functions are called.
	 *	Function 1: ReadFile(ArrayList<Accounts>, String filePath)
	 * 	Function 2: PopulateAccounts(ArrayList<Accounts>)
	 *  Function 3: CalculateFees(ArrayList<Accounts>)
	 * 	Function 4: DisplayCompleteAccountsInfo(ArrayList<Accounts>) 
	 *	@param  String[] args | path file name of the accounts file. Each param can contain a different file path.
	 *  @return void
	 */
	public static void main(String[] args) {
		
		ArrayList<String> files = new ArrayList<String>();
		String defaultFilePath = "AcctInfo.txt";
		System.out.println("Account Reader: Initializing");
		
		for (int ctr = 0; ctr < args.length; ctr++) {
        	files.add(args[ctr]); 
		}
				
		if( files.size() == 0){
			files.add(defaultFilePath);
		}

		for (int ctr = 0; ctr < files.size(); ctr++) {
        //This program consists of 4 main functions: ReadFile, PopulateAccounts, CalculateFees, and DisplayCompleteAccountsInfo
        ArrayList<String> tempAccounts = AccountReader.ReadFile(files.get(ctr));
        ArrayList<Account> accounts = AccountReader.PopulateAccounts(tempAccounts);
        accounts  = AccountReader.CalculateFees(accounts);
        AccountReader.DisplayAccountsInfo(accounts);	 
		}		     
    	System.out.println("Account Reader: Closing"); 
    }
    
	/**
	 *	Reads a file and populates the Account class array list from a properly formated file 
	 *	<p>
	 * 	Account File Format:
	 *	Line: 1 Account Number
     *	Line: 2 Account Type | {Savings, Checking}
     *	Line: 3 Customer Name 
	 *	Line: 4 Customer Type | {Value, Advantage, Premier}
	 *	Line: 5 Account Balance
	 *  Line: 6 <Blank Line>	 
	 *  @param  The name and file path of the account information.
	 *  @return Returns an ArrayList of class Account.
	 *  @see    ArrayList<T>
	 */      
    protected static ArrayList<String> ReadFile(String filePath){
 
    	System.out.println("Account Reader: Reading File " + filePath);
        ArrayList<String> fileLines = new ArrayList<String>();    	
    	try {
    		BufferedReader input = new BufferedReader(new FileReader(filePath));
        	try {        		
        		String line = null;     
        		// Parse the data from the file into an ArrayList of Strings
				while (( line = input.readLine()) != null){
					fileLines.add(line);
        		}
        	}
        	finally {
        		input.close();
        	}
        }
        catch (FileNotFoundException ex){
        	System.out.println("File Missing from Executable Path");
        }
        catch (IOException ex){
        	ex.printStackTrace();
        }
        System.out.println("Number of Accounts on file: " +  fileLines.size());
    	return fileLines;
    }
    
    /**
	 *	Creates an ArrayList<Account> from an ArrayList<String>  
	 *	<p>
	 * 	ArrayList account Format:
	 *	Line: 1 Account Number
     *	Line: 2 Account Type | {Savings, Checking}
     *	Line: 3 Customer Name 
	 *	Line: 4 Customer Type | {Value, Advantage, Premier}
	 *	Line: 5 Account Balance
	 *  Line: 6 <Blank Line>	 
	 *  @param  ArrayList<String> tempAccount. An Account information stored in a String ArrayList
	 *  @return Returns an ArraList<Account>.
	 */ 
    protected static ArrayList<Account> PopulateAccounts(ArrayList<String> tempAccounts){
    	/** You could probably do some crazy recursion here with PopulateAccounts() and PopulateAccount(), but I didn't want to think that hard
    	and, recursion is actually hard to read while troubleshooting. I would rather be more
    	verbose and have an extra method. */
		
		System.out.println("Account Reader: Populating Account Data");
    	
		ArrayList<Account> accounts = new ArrayList<Account>();
    	
		int numberOfAccounts = (tempAccounts.size()-1) / 6;
    	ArrayList<String> tempAccount = new ArrayList<String>();
		
    	//First go through every line in the file
    	for(int ctr = 0; ctr<tempAccounts.size()-1; ctr++){
			tempAccount.add(tempAccounts.get(ctr));
			//When the file cursor comes upon an empty line, this signifies a new account
			if(tempAccounts.get(ctr).length() == 0){
				Account acct = AccountReader.PopulateAccount(tempAccount);
				accounts.add(acct);
				tempAccount = new ArrayList<String>();
			}
		}
    	return accounts;
    }
  
    
    /**
	 *	Populates an Account object from an ArrayList<String>  
	 *	<p>
	 * 	ArrayList account Format:
	 *	Line: 1 Account Number
     *	Line: 2 Account Type | {Savings, Checking}
     *	Line: 3 Customer Name 
	 *	Line: 4 Customer Type | {Value, Advantage, Premier}
	 *	Line: 5 Account Balance
	 *  Line: 6 <Blank Line>	 
	 *  @param  ArrayList<String> tempAccount. An Account information stored in a String ArrayList
	 *  @return Returns an an object of class Account.
	 */ 
    protected static Account PopulateAccount(ArrayList<String> tempAccount){
    	Account account = new Account();
    	for(int ctr = 0; ctr<tempAccount.size(); ctr++){
    		switch (ctr) {
            case 0:
            	account.setAccountNumber(tempAccount.get(ctr));
            	break;
            case 1:
            	account.setAccountType(tempAccount.get(ctr));
                break;                      
            case 2: 
            	account.setCustomerName(tempAccount.get(ctr));
            	break;
            case 3:
            	account.setCustomerType(tempAccount.get(ctr));
                break;
            case 4:
            	account.setAccountBalance(tempAccount.get(ctr));
                break;          	
            default:
                break;
            }
        }
    	
    	return account;
    }
    
     /**
	 *	Calculates the monthly fees for each account within the ArrayList<Account>  
	 *	<p>
	 * 	Fee logic:
	 *	AccountType	CustomerType  Balance     MonthlyFee
	 *  Savings       Alltypes   Allbalances     $0
	 *	Checking        Value    under $1,500    $5
	 *	Checking 		Value 	 $1,500 or more  $0
	 *	Checking 	  Advantage  under $1,000    $10
	 *	Checking 	  Advantage  $1,000 or more  $0
	 *	Checking       Premier   under $25,000   $30
	 *	Checking  	   Premier   $25,000 or more $0 
	 *  @param  ArrayList<Account> all Accounts 
	 *  @return Returns an an object of class Account with the added Fees
	 */ 
    protected static ArrayList<Account> CalculateFees(ArrayList<Account> accounts){
    	System.out.println("Account Reader: Calculating Fees");
    	System.out.println("--------------------------------");   
    	int fee = 0;
    	for(int ctr = 0; ctr<accounts.size(); ctr++){
    		fee = 0;

    		switch (accounts.get(ctr).getAccountType()) {
            case "checking":
            	    	
            	//Value Customer Type
            	if(accounts.get(ctr).getCustomerType().equals("value") && accounts.get(ctr).getAccountBalance() < 1500){
             		fee = 5;
            	}
            	//Advantage Customer Type
            	if(accounts.get(ctr).getCustomerType().equals("advantage") && accounts.get(ctr).getAccountBalance() < 1000){
            		fee = 10;
            	}
            	//Premier Customer Type
            	if(accounts.get(ctr).getCustomerType().equals("premier") && accounts.get(ctr).getAccountBalance() < 25000){
            		fee = 30;
            	}
            	accounts.get(ctr).setAccountFee(Integer.toString(fee));
            	break;         
            case "savings":
            	fee = 0;
            	accounts.get(ctr).setAccountFee(Integer.toString(fee));
            	break;
            }
            
            
     	}   	
    	return accounts;
    } 
    
    /**
	 *	Displays all account information, fees and total account balances
	 *  @param  ArrayList<Account> All the accounts to display
	 *  @return void
	 */     
    protected static void DisplayAccountsInfo(ArrayList<Account> accounts){
    	System.out.println("Account Reader: Displaying All Accounts Info");
    	System.out.println("--------------------------------------------");

    	for(int ctr = 0; ctr<accounts.size(); ctr++){

    		System.out.println("Account Number  : " + accounts.get(ctr).getAccountNumber());
    		System.out.println("Account Type    : " + accounts.get(ctr).getAccountType());
    		System.out.println("Customer Name   : " + accounts.get(ctr).getCustomerName());
    		System.out.println("Customer Type   : " + accounts.get(ctr).getCustomerType());
    		System.out.println("Account Balance : " + accounts.get(ctr).getAccountBalance()); 
    		System.out.println("Monthly Fee     : " + accounts.get(ctr).getAccountFee());
    		System.out.println("");
    	}
    	double totalBalances = TotalAccountBalance(accounts);
    	DecimalFormat df = new DecimalFormat("#.##");
    	System.out.println("Total Balances  : " + df.format(totalBalances));	
    }
  
    /**
	 *	Calculates the total account balances
	 *  @param  ArrayList<Account> All the accounts to display
	 *  @return double
	 */     
    protected static double TotalAccountBalance(ArrayList<Account> accounts){
    	double total = 0;
    	for(int ctr = 0; ctr<accounts.size();ctr++){
    		total += accounts.get(ctr).getAccountBalance();
    	}
    	return total;
    }
}

