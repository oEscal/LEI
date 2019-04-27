package aula06.Ex2;

import java.util.ArrayList;
import java.util.TreeSet;

public class Contacts implements ContactsInterface {

    private TreeSet<Contact> contacts_list;
    private ContactsStorageInterface store;

    public Contacts(){

        contacts_list = new TreeSet<>();
    }

    @Override
    //loadContacts return list of contacts and just print them
    public void openAndLoad(ContactsStorageInterface store) {

        this.store = store;

        contacts_list = new TreeSet<>(this.store.loadContacts());
        for (Contact myContact : contacts_list)
            System.out.println(myContact.toString());
    }

    @Override
    // save all contacts on this.store
    public void saveAndClose() {

        this.store.saveContacts(new ArrayList<>(contacts_list));
    }

    @Override
    // save all contacts on a given store
    public void saveAndClose(ContactsStorageInterface store) {

        this.store = store;
        saveAndClose();
    }

    @Override
    //check if contact belong on list of contacts
    public boolean exist(Contact contact) {

        return contacts_list.contains(contact);
    }

    @Override
    // returns the contact with the given name; if it doesn't exists in the contacts list, returns null
    public Contact getByName(String name) {

        for (Contact contact : contacts_list)
            if (contact.getNome().equals(name))
                return contact;
        return null;
    }

    @Override
    //if contact exist in list , the function return false. Moreover, return true if contact isn't known.
    public boolean add(Contact contact) {

        return contacts_list.add(contact);
    }

    @Override
    //if contact don't exist in list of contacts , the function return false. Else, remove and return true
    public boolean remove(Contact contact) {

        return contacts_list.remove(contact);
    }
}
