package askisi4;

public class LinkedStack<T> implements Stack {
  int size;
  LinkedNode head;
 
  public LinkedStack() {
    size=0;
    head = null;
  }
 
  public int size() {
    return size;
  }
 
  public void push(Object o) {
    // the following is OK even if head == null.
    head = new LinkedNode(head, o);
    size++;
  }
 
  public Object pop() {
    LinkedNode pN = head;
    head = head.getNext();
    size--;
    return pN.getElement();
  }
 
  public Object top() {
    return head;
  }
 
  public String toString() {
    String str = "@@@@@@@@ - Stack - @@@@@@@@\n";
    LinkedNode curr = head;
    while(curr != null)
      str += curr.getElement().toString();
    return str + "@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
  }
}