package BankAccount;

public class CustomerInfo {
    private String name;
    private String address;
    private String phoneNumber;
    
    public CustomerInfo(String names, String addresss, String phoneNum) {
        name=names;
        address=addresss;
        phoneNumber=phoneNum;
    }
    
    
    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setName(String newName){
        name=newName;
    }
    public void setAddress(String newAddress){
        address=newAddress;
    }
    public void setPhoneNumber(String phoneNum){
        phoneNumber=phoneNum;
    }
    public String toString(){
        String result=name+" " + address+" "+phoneNumber;
        return result;
    }

    
    
}
