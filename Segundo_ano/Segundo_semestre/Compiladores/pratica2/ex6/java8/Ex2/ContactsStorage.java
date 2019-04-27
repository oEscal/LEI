package aula06.Ex2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactsStorage implements ContactsStorageInterface {

    String file_name;
    FileType file_type;

    public ContactsStorage(String file_name, FileType file_type) {

        this.file_name = file_name;
        this.file_type = file_type;
    }

    @Override
    public List<Contact> loadContacts() {

        List<Contact> contacts_list = new ArrayList<>();

        if (this.file_type == FileType.txt)
            readTxtFile(contacts_list);
        else
            if (this.file_type == FileType.bin)
                readBinFile(contacts_list);

        return contacts_list;
    }

    @Override
    // returns false if there was an error reading the file
    public boolean saveContacts(List<Contact> list) {

        if (this.file_type == FileType.txt)
            writeTxtFile(list);
        else
            if (this.file_type == FileType.bin)

                writeBinFile(list);

        return true;
    }

    // write in text file
    private boolean writeTxtFile(List<Contact> list){

        String all_contacts = "";
        for (Contact contact : list){
            all_contacts = all_contacts + contact.getNome() + "\t" + contact.getNumero() + "\n";
        }

        Path path = Paths.get(this.file_name);
        try {
            Files.write(
                    path,
                    all_contacts.getBytes()
            );
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private boolean writeBinFile(List<Contact> list){

        FileOutputStream f_stream;
        ObjectOutputStream o_stream;

        try {
            f_stream = new FileOutputStream(this.file_name);
            o_stream = new ObjectOutputStream(f_stream);

            for (Contact l : list)
                o_stream.writeObject(l);

            o_stream.close();
            f_stream.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    // read text file
    private boolean readTxtFile(List<Contact> contacts_list){

        // read files that are not binary
        Path path = Paths.get(this.file_name);
        try {
            Files.lines(path).forEach(line -> {
                String[] line_contact = line.split("\t");

                // verify if line_contact[1] is a number
                assert isNumber(line_contact[1]);

                String name = line_contact[0];
                int number = Integer.parseInt(line_contact[1]);

                // add new contact
                contacts_list.add(new Contact(name, number));
            });
        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    // read binary file
    private boolean readBinFile(List<Contact> contacts_list) {

        FileInputStream f_stream;
        ObjectInputStream o_stream;

        try {
            f_stream = new FileInputStream(this.file_name);
            o_stream = new ObjectInputStream(f_stream);

            while (true)
                try {
                    contacts_list.add((Contact) o_stream.readObject());
                } catch (EOFException e) {
                    o_stream.close();
                    f_stream.close();

                    return true;
                } catch (ClassNotFoundException e) {
                    return false;
                }
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
