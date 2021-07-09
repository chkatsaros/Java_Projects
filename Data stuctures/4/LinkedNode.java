
package askisi4;

class LinkedNode<T> {
  private T o;
  private LinkedNode<T> next;
 
  public LinkedNode(LinkedNode<T> nxt, T e) {
    next = nxt;
    o = e;
  }
 
  public LinkedNode(T e) {
    this(null, e);
    
  }
 
  public Object getElement() { return o; }
  public LinkedNode getNext() { return next; }
 
  public void setElement(T e) { o = e; }
  public void setNext(LinkedNode<T> node) { next = node; }
}