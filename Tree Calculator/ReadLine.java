package ce325.hw1;
import java.lang.Object;
import java.io.*;
import java.util.*;

public class ReadLine {
   	
	static final int numOfValids = 23;
	
	public static void main (String args[]) {
		
		char valid_symbols[] = { '^', '+', '-', '*', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', 'x', '.', '\t', '\r', '\n', ' ' };
		
		int counter1 = 0;
		int counter2 = 0;
		int valid = 0;
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.out.print("Enter math expression: ");
		String arithmetic = sc.nextLine();
		//System.out.println("Math expresssion is: " + arithmetic);
		
		for (int i=0; i<arithmetic.length(); i++) {
			
			if (arithmetic.charAt(i) == '(') counter1++;
			if (arithmetic.charAt(i) == ')') counter2++;
			if (counter2>counter1) {																						//case 2
				valid = 0;
				break;
			}
			
			for (int j=0; j<numOfValids; j++) {																				//case 3
				if (arithmetic.charAt(i) == valid_symbols[j]) {
					valid++;
					break;
				}
			} 
		}
		
		if (valid == arithmetic.length()) { 																				//case 1
			if (counter1 != counter2) {
				System.out.println("Error in arithmetical expression. Try again!");
				System.exit(0);
			}
		}
		else {
			System.out.println("Error in arithmetical expression. Try again!");
			System.exit(0);	
		}
		
		BinaryTree myTree = new BinaryTree();
		treeNode treeRoot = myTree.exprToWork(arithmetic);
		String output = myTree.toString(treeRoot);
		System.out.println(output);
		double result = myTree.calculate(treeRoot);
		System.out.println("Result = " + result);
		try {        
			PrintWriter pfile = new PrintWriter("ArithmeticExpression.dot");
			pfile.println(myTree.toDotString(treeRoot, ""));
			pfile.println("}");
			pfile.close();
			System.out.println("PRINT DOT FILE OK!");
			Process p = Runtime.getRuntime().exec("dot -Tpng ArithmeticExpression.dot " + "-o ArithmeticExpression.png");
			p.waitFor();
			System.out.println("PRINT PNG FILE OK!");
		} catch(Exception ex) {
			System.err.println("Unable to write dotString!!!");
			ex.printStackTrace();
			System.exit(1);
		}

	}
  
}

