
package BankAccount;


public class BankOperations {
    public static void main(String args[]){
        CustomerInfo mrSmithInfo=new CustomerInfo("Peter Smith","pagaswn 9","6955197599");
        BankAccount mrSmithBank=new BankAccount(1111,100.10);
        Customer mrSmith=new Customer(mrSmithInfo,mrSmithBank);

        CustomerInfo mrsJonesInfo;
        mrsJonesInfo = new CustomerInfo("Cathrine zeta jones","proskopwn 13","696942069");
        BankAccount mrsJonesBank=new BankAccount(2222,100000.10);
        Customer mrsJones=new Customer(mrsJonesInfo,mrsJonesBank);
    
        System.out.println(mrSmith.toString());
        System.out.println(mrsJones.toString());

    
    }
    
}
