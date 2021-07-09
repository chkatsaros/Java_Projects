
package BankAccount;


    public class BankOperations2 {
      public static void main(String[]args) {
        CreditCustomer mrSmith = new CreditCustomer( 
          new CustomerInfo("Peter Smith", "23 Highway", "+301234567890"),
          new BankAccount( 1410708210L, 1523.5),
          new CreditCard( 1541288964L, 1000, 10000)
        );
        CreditCustomer mrsJones = new CreditCustomer( 
          new CustomerInfo("Katherine Jones", "25 Highway", "+301234567898"),
          new BankAccount( 4506011789L, 120548.5),
          new CreditCard( 8546753701L, 100, 1000)
        );
     
        System.out.println(mrSmith);
        System.out.println(mrsJones);
     
        mrSmith.getBankAccount().modifyBalanceByAmount(10000);
        mrsJones.getBankAccount().modifyBalanceByAmount(-100000);
     
        if( mrSmith.getCreditCard().chargeAmount(1000) )
          System.out.println("Mr Smith charge OK!");
     
        if (mrsJones.getCreditCard().chargeAmount(1000))
          System.out.println("Mrs Jones charge OK!");
     
        System.out.println(mrSmith);
        System.out.println(mrsJones);
      }
    }


