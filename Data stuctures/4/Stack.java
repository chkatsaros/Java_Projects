package askisi4;
public interface Stack<T> {
  public int size();           // επιστ�?οφή του μεγέθους της στοίβας.
  public void push(T o);  // ένθεση στην κο�?υφή της στοίβας.
  public T pop();         // απ�?σβεση απ�? την κο�?υφή της στοίβας.
  public T top();         // επιστ�?οφή του κο�?υφαίου στοιχείου της στοίβας χω�?ίς διαγ�?αφή του.
}