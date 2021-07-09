
package BankAccount;

public class CreditCustomer extends Customer {
    public CreditCard card;
    
    public CreditCustomer(CustomerInfo info, BankAccount account, CreditCard initCard){
        super(info,account);
        card=initCard;
    }
    
    
    public CreditCard getCreditCard(){
        return card;
    }
    public void setCreditCard(CreditCard setCard){
        card=setCard;
    }
    public String toString(){
        String result=info.toString()+"\n"+account.toString()+"\n"+card.toString();
        return result;
    }

    
}
