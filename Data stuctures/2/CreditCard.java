package BankAccount;
public class CreditCard {
    private long id;
    private double balance;
    private double limit;
    
    public CreditCard(long creditCardID, double initLimit){
        id=creditCardID;
        limit=initLimit;
        balance=0;
    }
    
    public CreditCard(long creditCardID, double initLimit, double initBalance){
        id=creditCardID;
        limit=initLimit;
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
    public boolean canCharge(double amount){
        return balance >= amount;
    }
    public boolean chargeAmount(double amount){
        if (canCharge(amount)){
            modifyBalanceByAmount(-amount);
            return true;
        }
        else
            return false;
        
    }
    public void payAmount(double amount){
        modifyBalanceByAmount(amount);
    }
    public String toString(){
        String result=""+id+" "+balance+" "+limit;
        return result;
    }

}
