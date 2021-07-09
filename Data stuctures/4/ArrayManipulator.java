package askisi4;

public class ArrayManipulator {
      public static void main(String []args) {
        Character alphabet[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        printCharacterArray(alphabet);
        System.out.println("-----------");
     
        Stack<Character> stack = new LinkedStack<Character>();
        invertArray(alphabet, stack);
     
        printCharacterArray(alphabet);
      }
     
      public static <T> void invertArray(T []array, Stack<T> stk) {
        for(int i=0; i<array.length; i++)
          stk.push(array[i]);
        for(int i=0; i<array.length; i++)
          array[i] = stk.pop();
      }
     
      public static <T> void printCharacterArray(T []array) {
        for(T c : array)
          System.out.print(c.toString()+"   ");
        System.out.println("");
      }
    }

