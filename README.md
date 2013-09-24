AccountReader
=============

Basic Text file reader. See attached document 
Lab 1

Write a program that reads bank account information contained in a text file, determines the 
monthly fee for each account and calculates the total amount of money deposited in all the accounts.  
Your program must process the "AcctInfo.txt" file, but be written in such a way that it can handle a 
similarly formatted file containing any number of accounts. The text file contains the following bank 
account information in this order: Account Number, Account Type, Customer Name, Customer Type, and 
Account Balance.  Each account is separated by a blank line. There are two types of accounts; Savings 
and Checking.  There are three types of customers; Value, Advantage and Premier.  The monthly fee is based 
on the type of account, the type of customer and the account balance.  Use the following rules to calculate 
the monthly fee	C:

Account Type   Customer Type     Balance           Monthly Fee
Savings          All types     All balances           $0
Checking         Value         under $1,500           $5
Checking         Value        $1,500 or more          $0
Checking        Advantage      under $1,000           $10
Checking        Advantage     $1,000 or more          $0
Checking         Premier       under $25,000          $30
Checking         Premier      $25,000 or more         $0

Your program must display the complete bank account information for each individual account (found in "AcctInfo.txt") as
well as the monthly fee for that account.  It should also display the sum of all the account balances. You cannot 
edit "AcctInfo.txt" in any way.  Your program should be able to process the text file as is.

NOTE:  If your Java program tries to read a file that is not accessible, an error occurs.  Java calls errors like 
these Exceptions.  If a method might potentially cause an error to occur, Java requires that you handle that error 
in case it should happen.  We will learn how to do that in Chapter 12.  For now, it is sufficient for you to just 
state that the error might occur.  You will need to do this for your main method and any other method that uses code 
that reads from a file. To prevent a syntax error, add "throws FileNotFoundException" to the method header.  For the 
main method, your code should be:  public static void main (String [] args) throws FileNotFoundException
