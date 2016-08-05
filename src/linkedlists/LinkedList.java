package linkedlists;

public final class LinkedList<T> {
	private LinkedNode<T> myHead;
	private LinkedNode<T> myRunner;
	private LinkedNode<T> myTail;
	private int mySize;
	
	public LinkedList(){
		myHead = null;
		myRunner = null;
		myTail = null;
		mySize = 0;
	}
	public LinkedList(LinkedNode<T> theHead) {
		myHead = theHead;
		myRunner = theHead;
		mySize = 1;
		while(myRunner.getNext() != null) {
			mySize++;
			myRunner = myRunner.getNext();
			myTail = myRunner;
		}
	}
	/**
	 * Adds a node to the end of the list.
	 * 
	 * @param theElement the data to add to a node
	 */
	public void add(final T theElement) {
		if (myHead == null) {
			myHead = new LinkedNode<T>(theElement);
			myTail = myHead;
			mySize++;
		} else {
			myRunner = myTail;
			myRunner.setNext(new LinkedNode<T>(theElement));
			myTail = myRunner.getNext();
			mySize++;
		}
	}
	/**
	 * Sets node.next at thePosition to a new node that contains theElement.
	 * 
	 * @param theElement the element to add.
	 * @param thePosition the position to add it to.
	 */
	public void add(final T theElement, final int thePosition) {
		goToNode(thePosition);
		LinkedNode<T> newNode = new LinkedNode<T>(theElement);
		newNode.setNext(myRunner.getNext());
		myRunner.setNext(newNode);
		mySize++;
	}
	public void addHead(final T theElement) {
		LinkedNode<T> newFront = new LinkedNode<T>(theElement);
		newFront.setNext(myHead);
		myHead = newFront;
		mySize++;
	}
	/**
	 * Moves the runner to a position
	 * 
	 * @param thePosition the position to move to
	 * @return the runner at thePosition
	 */
	public LinkedNode<T> goToNode(final int thePosition) {
		if (thePosition >= mySize - 1) {
			myRunner = myTail;
		} else if (thePosition <= 0) {
			myRunner = myHead;
		} else {
			myRunner = myHead;
			for (int i = 0; i != thePosition; i++) {
				myRunner = myRunner.getNext();
			}
		}
		return myRunner;
	}
	/**
	 * Remove the head of the list.
	 */
	public void remove() {
		myHead = myHead.getNext();
		mySize--;
	}
	public void removeLast() {
		myRunner = myHead;
		while (myRunner.getNext().getNext() != null) {
			myRunner = myRunner.getNext();
		}
		myTail = myRunner;
		myRunner.setNext(null);
		mySize--;
		}
	/**
	 * Remove a value at a position.
	 * 
	 * @param thePosition the position of the value to be removed
	 */
	public void removeAt(final int thePosition) {
		if (thePosition >= mySize - 1) {
			removeLast();
		} else if (thePosition <= 0) {
			remove();
		} else {
			myRunner = goToNode(thePosition - 1);
			myRunner.setNext(myRunner.getNext().getNext());
		}
	}
	public void clear() {
		while(myHead != null) {
			remove();
		}
		mySize = 0;
	}
	public int getSize() {
		return mySize;
	}
	public LinkedNode<T> getHead() {
		return myHead;
	}
	public LinkedNode<T> getTail() {
		return myTail;
	}
	//switch to string return?
	public String getList(){
		final StringBuilder sb = new StringBuilder();
		if (myHead == null) {
			sb.append("empty");
		} else {
			myRunner = myHead;
			while (myRunner.getNext() != null) {
				sb.append(myRunner.getData());
				sb.append('\n');
				myRunner = myRunner.getNext();
			}
			sb.append(myRunner.getData());
		}
		return sb.toString();
	}
}









//MORE BAD IDEAS -----------------------------------------------------
///**
//* Swap a node with the one before it
//* [1] -> [2]
//* swapBubble(2)
//* [2] -> [1]
//* 
//* @param index of the second node
//*/
//public void swapBubble(final int index){
//	LinkedNode<T> blueTrail, blue, orange;
//	if (index == 0 ) {
//		blueTrail = null;
//		blue = getFirst();
//		orange = blue.getNext();
//		
//		blue.setNext(orange.getNext());
//		myHead = orange;
//		orange.setNext(blue);
//	} else if (index == getSize() - 1) {
//		blueTrail = goToNode(index - 2);
//		blue = blueTrail.getNext();
//		orange = blue.getNext();
//		
//		blueTrail.setNext(orange);
//		orange.setNext(blue);
//		blue.setNext(null);
//		myTail = blue;
//	} else {
//		blueTrail = goToNode(index - 1);
//		blue = blueTrail.getNext();
//		orange = blue.getNext();
//		
//		blueTrail.setNext(orange);	
//		blue.setNext(orange.getNext());			
//		orange.setNext(blue);		
//	}
//	
//}
//
///**
//* Nodes positions are swapped. 
//* 
//* @param blue first node
//* @param blueTrail behind first node
//* @param orange second node
//* @param orangeTrail behind second node
//*/
//public void swap(final LinkedNode<T> blue, final LinkedNode<T> orange,
//		final LinkedNode<T> blueTrail, final LinkedNode<T> orangeTrail) {
//	//yeah dunno how to make this nicer
//	if (blueTrail == null) {
//		myFront = orange;
//	}
//	if (orangeTrail == null) {
//		myFront = blue;
//	}
//	if (blue.getNext() == null) {
//		myTail = orange;
//		myTail.setNext(null);
//	}
//	if (orange.getNext() == null) {
//		myTail = blue;
//		myTail.setNext(null);
//	}
//	
//	
//	
//	LinkedNode<T> temp = new LinkedNode<T>(blue);
//	if (orangeTrail != null) {
//		orangeTrail.setNext(blue);
//	}
//	blue.setNext(orange.getNext());
//	if (blueTrail != null) {
//		blueTrail.setNext(orange);			
//	}
//	orange.setNext(temp.getNext());
//}
//public void swap(final LinkedNode<T> blue, final LinkedNode<T> orange,
//		final LinkedNode<T> blueTrail) {
//	//yeah dunno how to make this nicer
//	if (blueTrail == null) {
//		myFront = orange;
//	}
//	if (blue.getNext() == null) {
//		myTail = orange;
//		myTail.setNext(null);
//	}
//	if (orange.getNext() == null) {
//		myTail = blue;
//		myTail.setNext(null);
//	}
//	if (blueTrail != null) {
//		blueTrail.setNext(orange);			
//	}
//	blue.setNext(orange.getNext());
//	orange.setNext(blue);
//}
