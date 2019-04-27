package aula06.Ex2;

public class Test {

    public static void main(String[] args){

        Contacts list_contacts = new Contacts();
        list_contacts.openAndLoad(new ContactsStorage("contacts", FileType.bin));

        list_contacts.add(new Contact("Escaleira", 911));
        list_contacts.add(new Contact("Escaleira", 910));
        list_contacts.add(new Contact("Baião", 933));
        list_contacts.saveAndClose(new ContactsStorage("contacts", FileType.bin));
    }

}
