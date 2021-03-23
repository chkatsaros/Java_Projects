package ce325.hw1;
import java.lang.Object;

public class treeNode {
	
	public String value;
	public treeNode left;
	public treeNode right;
	public treeNode parent;
	
	public treeNode (String initValue,treeNode initLeft,treeNode initRight, treeNode initParent) {
		value = initValue;
		left = initLeft;
		right = initRight;	
		parent = initParent;
	}
	
	public void setParent(treeNode newParent) { parent = newParent; }
	public boolean isLeaf() {											//ama o kombos einai arithmos den exei aristera kai dexia paidia (null)
        return (left == null) && (right == null);
    }
	public int getKey() { return hashCode(); }	
	public treeNode getLeft() { return left; }
	public treeNode getRight() { return right; }
	public String getValue() { return value; }
	public treeNode getParent() { return parent; }
}

