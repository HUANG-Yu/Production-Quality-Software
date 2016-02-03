/**
 * Created by Yu Huang on 14/9/15.
 * Copyright (c) 2015, Yu HUANG. All rights reserved.
 */
package edu.nyu.pqs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * AddressBook class
 * The class is used as a cluster to store entries information, including
 * addEntry, removeEntry, search by entry properties, save  print 
 * and load methods
 * @author yu Huang
 * @see AddressBookEntry
 */
public class AddressBook {
  // declare addressBook to store each entry in sorted order
  TreeSet<AddressBookEntry> addressBook;
  
  /**
   * private AddressBook constructor
   */
  private AddressBook () {
    addressBook = new TreeSet<AddressBookEntry>();
  }
  
  /**
   * AddressBook constructor using static factory mode
   * @return a empty address book
   */
  public static AddressBook createAddressBook () {
    return new AddressBook();
  }
  
  /**
   * Get all the entries in the address book
   * @return the entries stores in the current address book
   */
  public TreeSet<AddressBookEntry> getAddressBookEntry () {
    return addressBook;
  }
  
  /**
   * Add an entry to the address book
   * @param toBeAdded the element to be inserted to the address book
   * @return true indicates the entry is added successfully, otherwise return false
   */
  public boolean addEntry (AddressBookEntry toBeAdded) {
    if (toBeAdded == null) {
      return false;
    }
    return addressBook.add(toBeAdded);
  }
  
  /**
   * Remove an entry from the address book
   * @param toBeRemoved the element to be removed from the address book
   * @return true indicates the entry is removed successfully, otherwise return false
   */
  public boolean removeEntry (AddressBookEntry toBeRemoved) {
    return addressBook.remove(toBeRemoved);
  }
  
  /**
   * Search every property of all entries for a given String
   * @param inputEntry the given entry for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchEntry (String inputEntry) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getPersonName().matches(".*" + inputEntry + ".*") 
          || each.getPhoneNumber().matches(".*" + inputEntry + ".*") 
          || each.getPostalAddress().matches(".*" + inputEntry + ".*") 
          || each.getEmail().matches(".*" + inputEntry + ".*") 
          || each.getNote().matches(".*" + inputEntry + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Search all entries by name for a given String
   * @param inputPersonName the given person name for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchByPersonName (String inputPersonName) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getPersonName().matches(".*" + inputPersonName + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Search all entries by phone number for a given String
   * @param inputPhoneNumber the given phone number for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchByPhoneNumber (String inputPhoneNumber) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getPhoneNumber().matches(".*" + inputPhoneNumber + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Search all entries by postal address for a given String
   * @param inputPostalAddress the given postal address for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchByPostalAddress (String inputPostalAddress) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getPostalAddress().matches(".*" + inputPostalAddress + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Search entries by email for a given String
   * @param inputEmail the given email address for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchByEmail (String inputEmail) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getEmail().matches(".*" + inputEmail + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Search entries by note for a given String
   * @param inputNote the given note for search
   * @return a list of entry satisfied the given String
   */
  public List<AddressBookEntry> searchByNote (String inputNote) {
    List<AddressBookEntry> result = new ArrayList<AddressBookEntry>();
    for (AddressBookEntry each: addressBook) {
      if (each.getNote().matches(".*" + inputNote + ".*")) {
        result.add(each);
      }
    }
    return result;
  }
  
  /**
   * Save the current address book to file given a String representing path
   * @param fileSavePath specify the path where the address book will be stored
   */
  @SuppressWarnings("unchecked")
  public void saveAddressBook (String fileSavePath) {
    FileWriter write2File = null;
    try {
      write2File = new FileWriter(fileSavePath);
      String addressRecord = "";
      if (this.addressBook != null) {
        JSONArray addressList = new JSONArray();
        for (AddressBookEntry entry: addressBook) {
          JSONObject curEntry = new JSONObject();
          curEntry.put("Person Name", entry.getPersonName());
          curEntry.put("Postal Address", entry.getPostalAddress());
          curEntry.put("Phone Number", entry.getPhoneNumber());
          curEntry.put("Email", entry.getEmail());
          curEntry.put("Note", entry.getNote());
          addressList.add(curEntry);
        }
        addressRecord = addressList.toJSONString();
      }
      write2File.write(addressRecord);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (write2File != null) {
          write2File.close();
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
   * Load an address book from file given a String representing path
   * @param fileLoadPath the path where the file is loaded
   * @return true if the book loaded successfully, otherwise false
   */
  public boolean loadAddressBook (String fileLoadPath) {
    addressBook = new TreeSet<AddressBookEntry>();
    JSONParser parser = new JSONParser();
    try {
      Object parseResult = parser.parse(new FileReader(fileLoadPath));
      JSONArray inputAddress = (JSONArray) parseResult;
      for (Object currentObj: inputAddress) {
        JSONObject curObj = (JSONObject) currentObj;
        String personName = (String) curObj.get("Person Name");
        String postalAddress = (String) curObj.get("Postal Address");
        String phoneNumber = (String) curObj.get("Phone Number");
        String email = (String) curObj.get("Email");
        String note = (String) curObj.get("Note");
        AddressBookEntry cur = new AddressBookEntry.Builder().personName(personName).
            postalAddress(postalAddress).phoneNumber(phoneNumber).
            email(email).note(note).build();
        addressBook.add(cur);
      }
      return true;
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (ParseException e) {
      
    }
    return false;
  }
  
  /**
   * Print all entries of the current address book
   */
  public void printAddressBook () {
    for (AddressBookEntry curAddress: addressBook) {
      System.out.println(curAddress);
    }
  }
}
