package Absyn;

public class TypeName {

    public String name;

    public TypeName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}