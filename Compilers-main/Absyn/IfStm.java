package Absyn;

public class IfStm extends Stm {
    public Exp test;
    public Stm thenStm;
    public Stm elseStm;

    public IfStm(int pos, Exp test, Stm thenStm, Stm elseStm) {
        super(pos);
        this.test = test;
        this.thenStm = thenStm;
        this.elseStm = elseStm;
    }
}
