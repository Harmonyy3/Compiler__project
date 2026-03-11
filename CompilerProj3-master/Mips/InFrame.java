package Mips;

class InFrame extends Frame.Access {
  int offset;
  InFrame(int o) {
    offset = o;
  }

  public String toString() {
    //report a warning here
    //removal] Integer(int) in Integer has been deprecated and marked for removal
    //Integer offset = new Integer(this.offset);
    Integer offset = this.offset;
    return offset.toString();
  }
}
