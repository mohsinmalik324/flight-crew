package org.flightcrew.beans;
 
enum AccountType {
	Manager,
	Representative,
	Customer
}

public class UserAccount {    
   private String userName;
   private String password;
   private AccountType type;
    
 
   public UserAccount() {
        
   }
    
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 
   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }

   public AccountType getType() {
	   return type;
   }

   public void setType(AccountType type) {
	   this.type = type;
   }
 
}