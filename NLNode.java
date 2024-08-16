/*
 * NLNode class that creates the tree structure
 * @author Dikran Kahiaian
 */
import java.util.Iterator;
import java.util.Comparator;
public class NLNode<T>{
	// instance variables for tree structure
	 private NLNode<T> parent;
	 private ListNodes<NLNode<T>> children;
	 private T data;
	 // constructors
	 public NLNode() {
		 parent=null;
		 data=null;
		 children=new ListNodes<NLNode<T>>();
	 }
	 public NLNode(T d, NLNode<T> p) {
		 children=new ListNodes<NLNode<T>>();
		 data=d;
		 parent=p;
	 }
	 public void setParent(NLNode<T> p) {
		 parent=p;
	 }
	 public NLNode<T> getParent(){
		 return parent;
	 }
	 
	 public void addChild(NLNode <T> newChild) {
		 // adding the child to the tree and setting its parent
		 children.add(newChild);
		 newChild.setParent(this);
	 }
	 public Iterator<NLNode<T>> getChildren(){
		 return children.getList();
	 }
	 public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter){
		 return children.sortedList(sorter);
	 }
	 public T getData() {
		 return data;
	 }
	 public void setData(T d) {
		 data=d;
	 }
}
