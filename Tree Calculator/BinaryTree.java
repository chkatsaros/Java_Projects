package ce325.hw1;
import java.util.Stack;
import java.util.Scanner;

public class BinaryTree {
	
	treeNode root;
	
	public boolean thePriority(treeNode node) {							//sunarthsh pou xrhsimopoieitai apo thn toString gia thn prosthiki twn katallhlwn paren8esewn  
				
		int priority = 69;
		int fpriority = 69;
		
		if (node.getParent().getValue()=="+"){
			fpriority=0;
		}
		else if (node.getParent().getValue()=="-"){
			fpriority=1;
		}
		else if (node.getParent().getValue()=="*"){
			fpriority=2;
		}
		else if (node.getParent().getValue()=="/"){
			fpriority=3;
		}
		else if (node.getParent().getValue()=="^"){
			fpriority=4;
		}
		
		if (node.getValue()=="+"){
			priority=0;
		}
		else if (node.getValue()=="-"){
			priority=1;
		}
		else if (node.getValue()=="*"){
			priority=2;
		}
		else if (node.getValue()=="/"){
			priority=3;
		}
		else if (node.getValue()=="^"){
			priority=4;
		}
		
		if (fpriority > priority){
			return (true);
		}
		
		return (false);
	}
	
	public boolean isOperator(char ch){
		return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == 'x' ;
	}

		
	public String toDotString(treeNode myRoot, String dotString){
		
		if (myRoot.isLeaf()){
			dotString = dotString + myRoot.getParent().getKey() + "->" + myRoot.getKey() + "\n" + myRoot.getKey() + " [label=\"" + myRoot.getValue() + "\", shape=circle, color=black]\n";
			return (dotString);
		}
		
		if (myRoot.getParent() == null){
			dotString = "digraph ArithmeticExpression{\n" + "label = \"Arithmetic Expression\"\n" + myRoot.getKey() + " [label=\"" + myRoot.getValue() + "\", shape=circle, color=green]\n";
		}
		else{
			dotString = dotString + myRoot.getParent().getKey() + "->" + myRoot.getKey() + "\n" + myRoot.getKey() + " [label=\"" + myRoot.getValue() + "\", shape=circle, color=purple]\n";
		}
		
		dotString = toDotString(myRoot.getLeft(),dotString);
		dotString = toDotString(myRoot.getRight(),dotString);
		
		return (dotString);
	}
	
	public void findParents (treeNode parent) {							//vriskei anadromika ton patera ka8e dothentos komvou
				
		if (!parent.isLeaf()) {
			parent.getLeft().setParent(parent);
			findParents(parent.getLeft());
			parent.getRight().setParent(parent);
			findParents(parent.getRight());
		}		
	}
	
	public String toString (treeNode myRoot){
		
		String output = "";
		
		if (myRoot.isLeaf()){
			output += myRoot.getValue();
        }
		else {
			if (myRoot.getParent() != null) {
				if (isOperator(myRoot.getParent().getValue().charAt(0))){
					String left = toString(myRoot.getLeft());
					String right = toString(myRoot.getRight());
					String operator = myRoot.getValue();
					if (thePriority(myRoot)) output += "(";
					switch (operator) {
						case "+" : output += left + "+" + right; break;
						case "-" : output += left + "-" + right; break;
						case "*" : output += left + "*" + right; break;
						case "/" : output += left + "/" + right; break;
						case "^" : output += left + "^" + right; break;
					}
					if (thePriority(myRoot)) output += ")";
				}
			}
			else {
				String left = toString(myRoot.getLeft());
				String right = toString(myRoot.getRight());
				String operator = myRoot.getValue();
				switch (operator) {
					case "+" : output = left + "+" + right; break;
					case "-" : output = left + "-" + right; break;
					case "*" : output = left + "*" + right; break;
					case "/" : output = left + "/" + right; break;
					case "^" : output = left + "^" + right; break;
				}
			}	
        }
        
        return output;
	}
	
	public treeNode addNode(String value, treeNode left, treeNode right){
		
		treeNode newNode = new treeNode(value, left, right, null);
		
		return (root = newNode);
	}
	
	public treeNode exprToWork(String expression) {
		
		for (int i=0; i<expression.length()-1; i++) {																			
			
			expression = noSpace(expression);																				//Check some other cases of malformed expression
						
			if (expression.charAt(i) == '/' && expression.charAt(i+1) == '0') {												//division by 0 case
				 System.out.println("Division by zero is undefined!");
				 System.exit(0);	
			}
			
			if (isOperator(expression.charAt(i)) && isOperator(expression.charAt(i+1))) {									//2 operators in a row case						
				System.out.println("Malformed expression!");
				System.exit(0);	
			}
			
			if ((expression.charAt(i) == ')' || isNum(expression.charAt(i))) && expression.charAt(i+1) == '(') {			//cases that include '(' and ')'
				 System.out.println("Malformed expression!");
				 System.exit(0);	
			}
			
			if (expression.charAt(i) == '(' && expression.charAt(i+1) == ')') {												
				 System.out.println("Malformed expression!");
				 System.exit(0);	
			}
			
			if (expression.charAt(i) == ')' && isNum(expression.charAt(i+1))) {												
				 System.out.println("Malformed expression!");
				 System.exit(0);	
			}
		}	
		
		treeNode treeRoot = SplitExpression(expression);
		findParents(treeRoot);		
		
		return treeRoot;
	}
	
	private treeNode SplitExpression(String expression){				//sunarthsh pou spaei anadromika thn dotheisa ekfrash analoga me thn protaireothta tou telesth se aristero kai de3i melos
		
		int priority=69;
		int i,counter,pos;
		counter=0;
		pos=0;
		int flag=0;
		for (int j=0; j<expression.length()-1; j++){
			if ((expression.charAt(0)=='(') && (expression.charAt(expression.length()-1)==')')){
				for (i=1;i<expression.length()-1;i++){
					if (expression.charAt(i)=='('){
						counter++;
					}
					else if(expression.charAt(i)==')'){
						counter--;
					}
					if (counter<0) flag++;
					
				}
				if (counter==0 && flag==0){
					StringBuilder sb = new StringBuilder(expression);
					sb.deleteCharAt(expression.length()-1);
					sb.deleteCharAt(0);
					expression = sb.toString();
				}
				
			}
		}
		
		for (i=0;i<expression.length();i++){
			if (expression.charAt(i)=='('){
				counter++;
			}
			else if(expression.charAt(i)==')'){
				counter--;
			}
			if (counter==0){
				if (isOperator(expression.charAt(i))){
					if (expression.charAt(i)=='+' && (priority > 0)){
						priority=0;
						pos=i;
					}
					else if ((expression.charAt(i)=='-') && (priority > 1)){
						priority=1;
						pos=i;
					}
					else if ((expression.charAt(i)=='*' || expression.charAt(i)=='x') && (priority > 2)){
						priority=2;
						pos=i;
					}
					else if ((expression.charAt(i)=='/') && (priority >= 3)){
						priority=3;
						pos=i;
					}
					else if ((expression.charAt(i)=='^') && (priority > 4)){
						priority=4;
						pos=i;
					}
				}
			}
		}
		
		String left = expression.substring(0,pos);
		String right = expression.substring(pos+1);
		
		if (priority==0){
			return (addNode("+", SplitExpression(left), SplitExpression(right)));
		}
		else if (priority==1){
			return (addNode("-", SplitExpression(left), SplitExpression(right)));
		}
		else if (priority==2){
			return (addNode("*", SplitExpression(left), SplitExpression(right)));
		}
		else if (priority==3){
			return (addNode("/", SplitExpression(left), SplitExpression(right)));		
		}
		else if (priority==4){
			return (addNode("^", SplitExpression(left), SplitExpression(right)));
		}
		else {
			return (addNode(expression, null, null));
		}	
	}
	
	public double calculate (treeNode myRoot) {

		if (myRoot.isLeaf()){
			return (Double.parseDouble(myRoot.getValue()));
        }
		else {
			double result = 0.0;
            double left = calculate(myRoot.getLeft());
			double right = calculate(myRoot.getRight());
			String operator = myRoot.getValue();
			switch (operator) {
				case "+" : result = left + right; break;
				case "-" : result = left - right; break;
				case "*" : result = left * right; break;
				case "/" : result = left / right; break;
				case "^" : result = Math.pow(left,right); break;
			}
			
			return result;
        }
	}
	
	public String noSpace(String expression) {                                                      //sunarthsh pou diagrafei ta kena apo thn arithmhtikh ekfrash
		
		StringBuilder tmp = new StringBuilder(expression);
		
		for (int i=0; i < expression.length(); i++) {
			if (isSpace(expression.charAt(i))){
				tmp.deleteCharAt(i);
				expression = tmp.toString();
				i--;
			}
		}
		
		return (expression);
	}
	
	public boolean isSpace(char ch) {
		
		if (ch == '\r' || ch == '\n' || ch == '\t' || ch == ' ') return (true);						//sunarthsh pou anixneuei ta kena          
		
		return (false);
	}
	
	public boolean isNum(char ch){
		return ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7' || ch == '8' || ch == '9' ;
	}
}
			
