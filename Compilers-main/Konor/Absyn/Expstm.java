package Absyn;

public class Expstm extends Stm {
    public Exp expression; //null or expression

    public Expstm(int pos, Exp expression) {
        super(expression != null ? expression.pos : 0);
        this.expression = expression;
    }
}