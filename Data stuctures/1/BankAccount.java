package BankAccount;
        
public class BankAccount {
    private long id;
    private double balance;
    
    
    public BankAccount(long accountID) {
        id=accountID;
        balance=0;
    }
    
    public BankAccount(long accountID, double initBalance){
        id=accountID;
        balance=initBalance;
    }
    
    
    public double getBalance(){
        return balance;
    }
    public long getID(){
        return id;
    }
    public void modifyBalanceByAmount(double amount){
        balance=balance+amount;
    }
    public String toString(){
       String result=""+id+ " "+balance;
       return result;
    }
}
