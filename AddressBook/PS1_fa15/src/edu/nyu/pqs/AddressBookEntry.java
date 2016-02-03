/**
 * Created by Yu Huang on 14/9/15.
 * Copyright (c) 2015, Yu HUANG. All rights reserved.
 */
package edu.nyu.pqs;

import java.util.Objects;

/**
* AddressBookEntry class 
* The class is used to store one single entry's information, including
* person name, postal address, phone number, email address 
* and note as well as their getter and setter methods corresponding to each property
* @author Yu HUANG
* @see AddressBook
*/

public class AddressBookEntry implements Comparable<AddressBookEntry>{
  private String personName;
  private String postalAddress;
  private String phoneNumber;
  private String email;
  private String note;
  
  /**
   * inner builder class for chain initialization
   */
  public static class Builder {
    
    // Optional parameters
    private String personName = "";
    private String postalAddress = "";
    private String phoneNumber = "";
    private String email = "";
    private String note = "";
    
    /**
     * Initialize the person name using a given String
     * @param initPersonName a String to initialize person name
     * @return the current Builder instance
     */
    public Builder personName (String initPersonName) {
      personName = initPersonName;
      return this;
    }
    
    /**
     * Initialize the postal address using a given String
     * @param initPostalAddress a String to initialize postal address
     * @return the current Builder instance
     */
    public Builder postalAddress (String initPostalAddress) {
      postalAddress = initPostalAddress;
      return this;
    }
    
    /**
     * Initialize the phone number using a given String
     * @param s a String to initialize phone number
     * @return the current Builder instance
     */
    public Builder phoneNumber (String s) {
      assert s.matches("[0-9]{7}[0-9]*") || s.equals(""): "Invalid Phone Number";
      if (s.matches("[0-9]{7}[0-9]*") || s.equals("")) {
        phoneNumber = s;
      }
      else {
        phoneNumber = "Warning" + s + " is not a valid phone number.";
      }
      return this;
    }
    
    /**
     * Initialize the email using a given String
     * @param s a String to initialize email
     * @return the current Builder instance
     */
    public Builder email (String s) {
      assert s.matches("(.+)@(.+)") || s.equals(""): "Invalid Email Address!";
      if (s.matches("(.+)@(.+)") || s.equals("")) {
        email = s;
      }
      else {
        email = "Warning: " + s + " is not a valid Email.";
      }
      return this;
    }
    
    /**
     * Initialize the note using a given String
     * @param s a String to initialize note
     * @return the current Builder instance
     */
    public Builder note (String s) {
      note = s;
      return this;
    }
    
    /**
     * Create the AddressBookEntry instance
     * @return an AddressBookEntry instance
     */
    public AddressBookEntry build () {
      return new AddressBookEntry(this);
    }
  }
  
  /**
   * private AddressBookEntry constructor
   * @param builder
   */
  private AddressBookEntry (Builder builder) {
    personName = builder.personName;
    postalAddress = builder.postalAddress;
    phoneNumber = builder.phoneNumber;
    email = builder.email;
    note = builder.note;
  }
  
  /**
   * Set the person name of an entry
   * @param inputPersonName the String which the name is to be set
   */
  public void setPersonName(String inputPersonName) {
    personName = inputPersonName;
  }
  
  /**
   * Set the postal address of an entry
   * @param inputPostalAddress a String representing the input postal address
   */
  public void setPostalAddress (String inputPostalAddress) {
    postalAddress = inputPostalAddress;
  }
  
  /**
   * Set the phone number of an entry
   * @param s a String representing the input phone number
   */
  public void setPhoneNumber (String s) {
    assert s.matches("[0-9]{7}[0-9]*") || s.equals(""): "Invalid Phone Number";
    if (s.matches("[0-9]{7}[0-9]*") || s.equals("")) {
      phoneNumber = s;
    }
    else {
      phoneNumber = "Warning" + s + " is not a valid phone number.";
    }
  }
  
  /**
   * Set the email address of an entry
   * @param s a String representing the email address
   */
  public void setEmail (String s) {
    assert !s.matches("(.+)@(.+)") || s.equals(""): "Email format is wrong";
    if (s.matches("(.+)@(.+)") || s.equals("")) {
      email = s;
    }
    else {
      email = "Warning: " + s + " is not a valid Email.";
    }
  }
  
  /**
   * Set the note of an entry
   * @param s a String representing the input note
   */
  public void setNote (String s) {
    note = s;
  }
  
  /**
   * Get the person name of an entry
   * @return person name
   */
  public String getPersonName () {
    return personName;
  }
  
  /**
   * Get the postal address of an entry
   * @return postal address
   */
  public String getPostalAddress () {
    return postalAddress;
  }
  
  /**
   * Get the phone number of an entry
   * @return phone number
   */
  public String getPhoneNumber () {
    return phoneNumber;
  }
  
  /**
   * Get the email address of an entry
   * @return email address
   */
  public String getEmail () {
    return email;
  }
  
  /**
   * Get the note of an entry
   * @return note
   */
  public String getNote () {
    return note;
  }
  
  /**
   * Present properties with corresponding values of an entry
   */
  @Override
  public String toString () {
    return "Person Name: " + personName + "\n" + "Address: " 
        + postalAddress + "\n" + "Phone: " + phoneNumber + "\n" 
        + "Email: " + email + "\n" + "Note: " + note;
  }
  
  /**
   * Decide if two entries are equal only if each property of entry are the same
   * @return true if objects are equal, else return false
   */
  @Override
  public boolean equals (Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AddressBookEntry)) {
      return false;
    }
    AddressBookEntry comp = (AddressBookEntry) o;
    return comp.personName.equals(personName) && comp.phoneNumber.equals(phoneNumber) 
        && comp.postalAddress.equals(postalAddress) && comp.email.equals(email) 
        && comp.note.equals(note);
  }
  
  /**
   * Compute the hash code of an entry by combining every property's hash code
   */
  @Override
  public int hashCode () {
    return Objects.hash(personName.hashCode(), phoneNumber.hashCode(), 
        postalAddress.hashCode(), email.hashCode(), note.hashCode());
  }

  /**
   * Compare entries by comparing each property of an entry
   * @return 0 means equal, 1 means this "bigger" that, -1 means this "smaller" that
   */
  @Override
  public int compareTo(AddressBookEntry that) {
    if (this.getPersonName().equals(that.getPersonName())) {
      if (this.getPostalAddress().equals(that.getPostalAddress())) {
        if (this.getPhoneNumber().equals(that.getPhoneNumber())) {
          if (this.getEmail().equals(that.getEmail())) {
            return this.getNote().compareTo(that.getNote());
          }
          return this.getEmail().compareTo(that.getEmail());
        }
        return this.getPhoneNumber().compareTo(that.getPhoneNumber());
      }
      return this.getPostalAddress().compareTo(that.getPostalAddress());
    }
    return this.getPersonName().compareTo(that.getPersonName()); 
  }
}
