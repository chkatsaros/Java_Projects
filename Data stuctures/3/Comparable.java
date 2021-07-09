public interface Comparable {
  /**
   * Returns -1, 0, 1 when the calling object
   * is less than, equal to or greater than obj.
   * If calling object cannot be compared with obj
   * returns -2;
   */
  int compareTo(Comparable obj);
}