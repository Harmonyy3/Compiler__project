package Absyn;

import java.io.PrintWriter;

public class Print {
    private PrintWriter out;

    public Print(PrintWriter w) {
        this.out = w;
    }

    private void indent(int d) {
        for (int i = 0; i < d; i++)
            out.print("  ");
    }

    public void prExp(Exp e, int d) {
        if (e == null) {
            indent(d);
            out.println("null");
            return;
        }

        if (e instanceof VarExp) prExp((VarExp)e, d);
        else if (e instanceof CharConstExp) prExp((CharConstExp)e, d);
        else if (e instanceof IntConstExp) prExp((IntConstExp)e, d);
        else if (e instanceof StringExp) prExp((StringExp)e, d);
        else if (e instanceof BinOpExp) prExp((BinOpExp)e, d);
        else if (e instanceof UnaryOpExp) prExp((UnaryOpExp)e, d);
        else if (e instanceof SizeofExp) prExp((SizeofExp)e, d);
        else if (e instanceof SizeofTypeExp) prExp((SizeofTypeExp)e, d);
        else if (e instanceof CastExp) prExp((CastExp)e, d);
        else if (e instanceof ArrayAccessExp) prExp((ArrayAccessExp)e, d);
        else if (e instanceof CallExp) prExp((CallExp)e, d);
        else if (e instanceof FieldAccessExp) prExp((FieldAccessExp)e, d);
        else if (e instanceof PointerAccessExp) prExp((PointerAccessExp)e, d);
        else if (e instanceof SeqExp) prExp((SeqExp)e, d);
        else if (e instanceof OpExpA) prExp((OpExpA)e, d);
        else if (e instanceof AndExpA) prExp((AndExpA)e, d);
        else if (e instanceof OrExpA) prExp((OrExpA)e, d);
        else if (e instanceof CondExpA) prExp((CondExpA)e, d);
        else if (e instanceof AssignExpA) prExp((AssignExpA)e, d);
        else {
            indent(d);
            out.println("Unknown Exp: " + e.getClass().getSimpleName());
        }
    }

    private void prExp(VarExp e, int d) {
        indent(d);
        out.println("VarExp:");
        prVar(e.var, d + 1);
    }

    private void prVar(Var v, int d) {
        if (v == null) {
            indent(d);
            out.println("null");
            return;
        }
        if (v instanceof SimpleVar) {
            indent(d);
            out.println("SimpleVar: " + ((SimpleVar)v).name);
        } else {
            indent(d);
            out.println("Unknown Var: " + v.getClass().getSimpleName());
        }
    }

    // private void prExp(ConstExp e, int d) {
    //     indent(d);
    //     if (e.value instanceof Integer)
    //         System.out.println("ConstExp: " + (Integer)e.value);
    //     else if (e.value instanceof Character)
    //         System.out.println("ConstExp: '" + (Character)e.value + "'");
    //     else
    //         System.out.println("ConstExp: " + e.value);
    // }

    private void prExp(IntConstExp e, int d) {
        indent(d);
        out.println("IntConstExp: " + e.value);
    }

    private void prExp(CharConstExp e, int d) {
        indent(d);
        out.println("CharConstExp: '" + e.value + "'");
    }

    private void prExp(StringExp e, int d) {
        indent(d);
        out.println("StringExp: \"" + e.value + "\"");
    }

    private void prExp(BinOpExp e, int d) {
        indent(d);
        out.println("BinOpExp: " + opToString(e.op));
        prExp(e.left, d + 1);
        prExp(e.right, d + 1);
    }

    private void prExp(UnaryOpExp e, int d) {
        indent(d);
        out.println("UnaryOpExp: " + unaryOpToString(e.op));
        prExp(e.exp, d + 1);
    }

    private void prExp(SizeofExp e, int d) {
        indent(d);
        out.println("SizeofExp: sizeof");
        prExp(e.expr, d + 1);  // fixed the field name
    }

    private void prExp(SizeofTypeExp e, int d) {
        indent(d);
        out.println("SizeofTypeExp: " + e.type.name);
    }

    private void prExp(CastExp e, int d) {
        indent(d);
        out.println("CastExp to " + e.type.name);
        prExp(e.exp, d + 1);
    }

    private void prExp(ArrayAccessExp e, int d) {
        indent(d);
        out.println("ArrayAccessExp:");
        prExp(e.array, d + 1);
        prExp(e.index, d + 1);
    }

    private void prExp(CallExp e, int d) {
    indent(d);
    out.println("CallExp:");
    indent(d + 1);
    out.println("Function:");
    prExp(e.func, d + 2);

    if (e.args != null) {
        indent(d + 1);
        out.println("Arguments:");
        prExpList(e.args, d + 2);
    } else {
        indent(d + 1);
        out.println("Arguments: none");
    }
}


    private void prExp(FieldAccessExp e, int d) {
        indent(d);
        out.println("FieldAccessExp: ." + e.field);
        prExp(e.record, d + 1);
    }

    private void prExp(PointerAccessExp e, int d) {
        indent(d);
        out.println("PointerAccessExp: ->" + e.field);
        prExp(e.pointer, d + 1);
    }

    private void prExp(SeqExp e, int d) {
        indent(d);
        out.println("SeqExp:");
        prExpList(e.list, d + 1);
    }

    private void prExp(OpExpA e, int d) {
        indent(d);
        String opStr = "?";

        switch (e.oper) {
            case OpExpA.PLUS:   opStr = "+"; break;
            case OpExpA.MINUS:  opStr = "-"; break;
            case OpExpA.MUL:    opStr = "*"; break;
            case OpExpA.DIV:    opStr = "/"; break;
            case OpExpA.EQ:     opStr = "=="; break;
            case OpExpA.NE:     opStr = "!="; break;
            case OpExpA.LT:     opStr = "<"; break;
            case OpExpA.LE:     opStr = "<="; break;
            case OpExpA.GT:     opStr = ">"; break;
            case OpExpA.GE:     opStr = ">="; break;
            case OpExpA.BITAND: opStr = "&"; break;
            case OpExpA.BITXOR: opStr = "^"; break;
            case OpExpA.BITOR:  opStr = "|"; break;
            case OpExpA.PREINC: opStr = "++"; break;
            case OpExpA.PREDEC: opStr = "--"; break;
        }

        out.println("OpExp: " + opStr);

        if (e.left != null) {
            prExp(e.left, d + 1);
        }
        if (e.right != null) {
            prExp(e.right, d + 1);
        }
    }

    private void prExp(AndExpA e, int d) {
        indent(d);
        out.println("AndExp: &&");  // print the operator

        if (e.left != null) {
            prExp(e.left, d + 1);   // recursively print left expression
        }
        if (e.right != null) {
            prExp(e.right, d + 1);  // recursively print right expression
        }
    }

    private void prExp(OrExpA e, int d) {
        indent(d);
        out.println("AndExp: ||");  // print the operator

        if (e.left != null) {
            prExp(e.left, d + 1);   // recursively print left expression
        }
        if (e.right != null) {
            prExp(e.right, d + 1);  // recursively print right expression
        }
    }


    private void prExp(CondExpA e, int d) {
        indent(d);
        out.println("CondExp: ? :");

        indent(d + 1);
        out.println("Condition:");
        prExp(e.condition, d + 2);

        indent(d + 1);
        out.println("Then:");
        prExp(e.thenExp, d + 2);

        indent(d + 1);
        out.println("Else:");
        prExp(e.elsExp, d + 2);
    }

    private void prExp(AssignExpA e, int d) {
        indent(d);

        String opStr;
        switch (e.oper) {
            case AssignExpA.ASSIGN:     opStr = "="; break;
            case AssignExpA.PASSIGN:    opStr = "+="; break;
            case AssignExpA.MASSIGN:    opStr = "-="; break;
            case AssignExpA.MULASSIGN:  opStr = "*="; break;
            case AssignExpA.DIVASSIGN:  opStr = "/="; break;
            case AssignExpA.MODASSIGN:  opStr = "%="; break;
            case AssignExpA.BANDASSIGN: opStr = "&="; break;
            case AssignExpA.XORASSIGN:  opStr = "^="; break;
            case AssignExpA.LSASSIGN:   opStr = "<<="; break;
            case AssignExpA.RSASSIGN:   opStr = ">>="; break;
            case AssignExpA.ORASSIGN:   opStr = "|="; break;
            default:                    opStr = "?="; break;
        }

        out.println("AssignExp: " + opStr);

        if (e.left != null) {
            prExp(e.left, d + 1);
        }

        if (e.right != null) {
            prExp(e.right, d + 1);
        }
    }



    private void prExpList(ExpList list, int d) {
        while (list != null) {
            prExp(list.head, d + 1);
            list = list.tail;
        }
    }

    private String opToString(int op) {
        switch (op) {
            case BinOpExp.PLUS: return "+";
            case BinOpExp.MINUS: return "-";
            case BinOpExp.TIMES: return "*";
            case BinOpExp.DIVIDE: return "/";
            case BinOpExp.MODULUS: return "%";
            case BinOpExp.LT: return "<";
            case BinOpExp.LEQ: return "<=";
            case BinOpExp.GT: return ">";
            case BinOpExp.GEQ: return ">=";
            case BinOpExp.EQ: return "==";
            case BinOpExp.NEQ: return "!=";
            case BinOpExp.LSHIFT: return "<<";
            case BinOpExp.RSHIFT: return ">>";
            default: return "op(" + op + ")";
        }
    }

    private String unaryOpToString(int op) {
        switch (op) {
            case UnaryOpExp.PLUS: return "+";
            case UnaryOpExp.MINUS: return "-";
            case UnaryOpExp.NOT: return "!";
            case UnaryOpExp.TILDE: return "~";
            case UnaryOpExp.BITWISEAND: return "&";
            case UnaryOpExp.TIMES: return "*";
            case UnaryOpExp.POSTINC: return "post++";
            case UnaryOpExp.POSTDEC: return "post--";
            case UnaryOpExp.PREINC: return "++pre";
            case UnaryOpExp.PREDEC: return "--pre";
            default: return "unop(" + op + ")";
        }
    }
}
