package aula06.Ex2;


import java.io.Serializable;

public class Contact implements Comparable, Serializable {

    private String nome;
    private int numero;

    public Contact(String nome, int numero){
        this.nome = nome;
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    // order alphabetically and numerically
    public int compareTo(Object o) {

        Contact contact_to_compare = (Contact) o;

        int compare_alphabetic = this.nome.compareTo(contact_to_compare.getNome());
        if (compare_alphabetic == 0)
            return this.numero - contact_to_compare.getNumero();
        return  compare_alphabetic;

    }

    @Override
    public String toString() {
        return "nome: '" + nome + '\'' +
                ", numero: " + numero;
    }
}
