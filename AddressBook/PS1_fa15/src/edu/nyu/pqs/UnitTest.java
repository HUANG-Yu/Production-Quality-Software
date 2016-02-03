package edu.nyu.pqs;

public class UnitTest {
  public static void main(String[] args) {
    AddressBook book = AddressBook.createAddressBook();
    AddressBookEntry entry1 = new AddressBookEntry.Builder().personName("Amy").build();
    AddressBookEntry entry2 = new AddressBookEntry.Builder().personName("Ashley").
        email("33333@diewir").phoneNumber("9293219929").build();
    AddressBookEntry entry3 = new AddressBookEntry.Builder().personName("Tim").build();
    AddressBookEntry entry4 = new AddressBookEntry.Builder().personName("Pig Zhi").build();
    AddressBookEntry entry5 = new AddressBookEntry.Builder().personName("Jeff").build();
    AddressBookEntry entry6 = new AddressBookEntry.Builder().personName("Jose").email("22@gmail.com").build();
    AddressBookEntry entry7 = new AddressBookEntry.Builder().personName("Jose").build();
    book.addEntry(entry1);
    book.addEntry(entry2);
    book.addEntry(entry3);
    book.addEntry(entry4);
    book.addEntry(entry5);
    book.addEntry(entry6);
    book.addEntry(entry7);
    book.removeEntry(entry6);
    book.searchByPersonName("Jose");
    book.searchByEmail("gmail");
    book.printAddressBook();
    book.saveAddressBook("/Users/yuhuang/Desktop/new.text");
    /*
    book.loadAddressBook("/Users/yuhuang/Desktop/new.text");
    book.printAddressBook();
    */
  }
}
