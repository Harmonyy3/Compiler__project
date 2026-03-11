package Absyn;

public class AssignStm extends Stm {
    public Var var;
    public Exp exp;

    public AssignStm(int pos, Var var, Exp exp) {
        super(pos); 
        this.var = var; 
        this.exp = exp;
    }    
}
