package aula06.Ex2;

public enum FileType {

    txt(0), bin(1);

    private int value;

    FileType(int value) {
        this.value = value;
    }
}
