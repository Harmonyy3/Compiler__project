package Translate;

import Types.Type;

public class Nx extends Exp {
    public Object stmt;  // could be IR node, AST, etc.

    public Nx(Object s) {
        super(Type.VOID);  // variable declarations are VOID
        this.stmt = s;
    }
}
