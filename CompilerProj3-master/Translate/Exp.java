package Translate;

import Types.Type;

public abstract class Exp {
    public Type type;   // semantic type of the expression
    
    public Exp(Type t) {
        this.type = t;
    }
}
