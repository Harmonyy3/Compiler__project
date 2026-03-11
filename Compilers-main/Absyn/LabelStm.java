package Absyn;
import Symbol.Symbol;

public class LabelStm extends Stm {
    public Symbol label; 
    public Exp exp;      
    public Stm stm;

    public LabelStm(int pos, Symbol label, Exp exp, Stm stm) {
        super(pos);
        this.label = label;
        this.exp = exp;
        this.stm = stm;
    }
}
