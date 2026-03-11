package Translate;

import Types.Type;

/**
 * Ex: Expression that produces a value.
 * Holds a simple constant value or expression IR node.
 */
public class Ex extends Exp {
    public Object value;  // can be int, char, string, or IR node

    public Ex(Object val, Type t) {
        super(t);       // set semantic type
        this.value = val;
    }

    // Convenience constructor for literals (type inferred)
    public Ex(Object val) {
        super(null);    // type can be set later if needed
        this.value = val;
    }

    @Override
    public String toString() {
        return "Ex(value=" + value + ", type=" + type + ")";
    }
}
