    package BankAccount;


public class Customer {
    BankAccount account;
    CustomerInfo info ;
    
    public Customer(CustomerInfo infos, BankAccount accounts){
        info=infos;
        account=accounts;
    }
    
    
    public CustomerInfo getCustomerInfo(){
        return info;
    }
    public BankAccount getBankAccount(){
        return account;
    }
    public String toString(){
        String result=info.toString()+"\n"+account.toString();
        return result;
    }
}
