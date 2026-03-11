package Mips;
import java.util.Hashtable;
import Symbol.Symbol;
import Temp.Temp;
import Temp.Label;
import Frame.Frame;
import Frame.Access;
import Frame.AccessList;

//For task 2, implement a method allocLocal in Mips.MipsFrame and write code for allocating formal parameters.
// parameters, local variables, saved register, and return address 
public class MipsFrame extends Frame {

  private int count = 0;
  private int localCount = 0;

  public Frame newFrame(Symbol name, Util.BoolList formals) {
    Label label;
    if (name == null)
      label = new Label();
    else if (this.name != null)
      label = new Label(this.name + "." + name + "." + count++);
    else
      label = new Label(name);
    return new MipsFrame(label, formals);
  }

  public MipsFrame() {}
  private MipsFrame(Label n, Util.BoolList f) {
    name = n;
    formals = makeFormals(f);
  }
  
  //for testing purposes
  public MipsFrame(Symbol name, Util.BoolList formals) {
    this(new Label(name), formals);
  }

  private static final int wordSize = 4;
  public int wordSize() { return wordSize; }

  //if escape is true, allocate in frame; else allocate in temperay register
  public Access allocLocal(boolean escape) {
    if (escape) {
      localCount++;
      return new InFrame(-localCount * wordSize);
    } else {
      return new InReg(new Temp());
    }
  }
  //allocate formals based on whether they escape or not
  private AccessList makeFormals(Util.BoolList formals) {
    if (formals == null) return null;

    Access first;
    if (formals.head) {
      localCount++;
      first = new InFrame(localCount * wordSize);
    } else {
      first = new InReg(new Temp());
    }

    return new AccessList(first, makeFormals(formals.tail));
  }
}