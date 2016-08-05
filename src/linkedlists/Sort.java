package linkedlists;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides the shell and bubble sort algorithms on a linked list.
 * 
 * @author noxor
 *
 */
public final class Sort {
	/**
	 * Some class wide variables
	 */
	private static int bubbleSwaps;
	private static int bubbleComps;
	private static int bubblePass;
	private static int[][] shellData;
	private static double bubbleElapsed;
	private static double shellElapsed;	
	
	private Sort() {
		//don't do this
	}
	
	/**
	 * Sorts the list by swapping bigger values with the adjacent value.
	 * 
	 * @param theHead The head node of the list to be sorted
	 * @return the head of a sorted list
	 */
	public static <T> LinkedNode<T> bubbleSort(final LinkedNode<T> theHead) {
		final double start = System.nanoTime();
		LinkedNode<T> head = new LinkedNode<T>(theHead);
		//the runners
		LinkedNode<T> prev = null;
		LinkedNode<T> curr = head;
		LinkedNode<T> next = curr.getNext();
		LinkedNode<T> hold = null;
		int swaps = 0;
		int cmp = 0;
		int pass = 0;
		while (hold != head.getNext()){
			while (next != null && next != hold) {
				cmp++;
				if (curr.compareTo(next.getData()) > 0) {
					if (prev == null) {
						curr.setNext(next.getNext());
						next.setNext(curr);
						head = next;
						head.setNext(curr);
						next = curr.getNext();
						prev = head;
					} else {
						curr.setNext(next.getNext());
						next.setNext(curr);
						prev.setNext(next);
						next = curr.getNext();
						prev = prev.getNext();
					}
					swaps++;
				} else {
					if (prev == null) {
						curr = curr.getNext();
						next = next.getNext();
						prev = head;
					} else {
						prev = prev.getNext();
						curr = curr.getNext();
						next = next.getNext();
					}
				}
			}
			hold = prev.getNext();
			curr = head;
			next = curr.getNext();
			prev = null;
			pass++;
		}
		final double stop = System.nanoTime();
		final double elapsed = (stop - start) / 1000000000;
		Sort.setBubbleStats(swaps, cmp, pass, elapsed);
		return head;
	}

	/**
	 * Sorts the list in the same way as a bubbleSort, but with a gap.
	 * 
	 * @param theList the list to be sorted
	 * @return returns the head of a sorted list
	 */
	public static <T> LinkedNode<T> shellSort(final LinkedList<T> theList) {
		final double start = System.nanoTime();
		LinkedNode<T> head = theList.getHead();
		LinkedNode<T> pos1, pos2, pre2, pre1 = null;
		boolean hasSwap;
		int rowSize = 9;
		int[][] outputTable = new int[rowSize][4];
		int col = 0;
		int row = 0;
		double k = 15;
		int seq = theList.getSize() + 1;
		
		while (seq > theList.getSize()) {
			seq = (int) ((Math.pow(3, k) - 1 ) / 2);
			k--;
		}
		for (int gap = seq; gap > 1; k--) {
			int swaps = 0;
			int cmp = 0;
			int pass = 0;
			hasSwap = true;
			while (hasSwap) {
				hasSwap = false;
				pos1 = new LinkedNode<T>(head);
				pre2 = pos1;
				//gap set
				for (int i = 0; i < gap - 1; i++) {
					pre2 = pre2.getNext();
				}				
				pos2 = pre2.getNext();
				while (pos2 != null) {
					if (pre1 == null) {
						cmp++;
						if (pos1.compareTo(pos2.getData()) > 0) {
							swaps++;
							pre2.setNext(pos1);
							pos1.setNext(pos2.getNext());
							pos2.setNext(head.getNext());
							head = pos2;
							hasSwap = true;
						}
					} else {
						cmp++;
						if (pos1.compareTo(pos2.getData()) > 0) {
							swaps++;
							LinkedNode<T> tmp = new LinkedNode<T>(pos1);
							pre2.setNext(pos1);
							pos1.setNext(pos2.getNext());
							pos2.setNext(tmp.getNext());
							pre1.setNext(pos2);
							hasSwap = true;
						}
					}		
					if (pre1 == null) {
						pre1 = head;
					} else {
						pre1 = pre1.getNext();
					}
					pos1 = pre1.getNext();
					pre2 = pre2.getNext();
					pos2 = pre2.getNext();
				}
				pre1 = null;
				pass++;
			}
			col = 0;
			outputTable[row][col++] = gap;
			outputTable[row][col++] = pass;
			outputTable[row][col++] = cmp;
			outputTable[row][col++] = swaps;
			row = rowSize - (int) k;
					
			gap = (int) ((Math.pow(3, k) - 1 ) / 2);
		}		
		hasSwap = true;
		pre1 = null;
		pos1 = head;
		pos2 = pos1.getNext();
		
		int swaps = 0;
		int cmp = 0;
		int pass = 0;
		while(hasSwap) {
			hasSwap = false;
			while (pos2 != null) {
				cmp++;
				if (pos1.compareTo(pos2.getData()) > 0) {
					if (pre1 == null) {
						pos1.setNext(pos2.getNext());
						pos2.setNext(pos1);
						head = pos2;
						head.setNext(pos1);
						pos2 = pos1.getNext();
						pre1 = head;
						hasSwap = true;
					} else {
						pos1.setNext(pos2.getNext());
						pos2.setNext(pos1);
						pre1.setNext(pos2);
						pos2 = pos1.getNext();
						pre1 = pre1.getNext();
						hasSwap = true;
					}
					swaps++;
				} else {
					if (pre1 == null) {
						pos1 = pos1.getNext();
						pos2 = pos2.getNext();
						pre1 = head;
					} else {
						pre1 = pre1.getNext();
						pos1 = pos1.getNext();
						pos2 = pos2.getNext();
					}
				}
			}
			pass++;
			pos1 = head;
			pos2 = pos1.getNext();
			pre1 = null;	
		}
		col = 0;
		outputTable[row][col++] = 1;
		outputTable[row][col++] = pass;
		outputTable[row][col++] = cmp;
		outputTable[row][col++] = swaps;
		
		final double stop = System.nanoTime();
		final double elapsed = (stop - start) / 1000000000;
		Sort.setShellStats(outputTable, elapsed);
		return head;
	}

	/**
	 * Sets the class wide variables to the most recent sort's data.
	 * 
	 * @param theData the number of exch, cmp, pass, and gap value.
	 * @param theElapsed time taken to complete sort
	 */
	private static void setShellStats(final int[][] theData, final double theElapsed) {
		shellData = theData;
		shellElapsed = theElapsed;
	}
	/**
	 * Sets the class wide variables to the most recent sort's data.
	 * 
	 * @param theSwp amount of data exchanges
	 * @param theCmp amount of comparisons
	 * @param theElapsed time elapsed
	 */
	private static void setBubbleStats(final int theSwp, 
			final int theCmp, final int thePass, final double theElapsed) {
		bubbleComps = theCmp;
		bubbleSwaps = theSwp;
		bubblePass = thePass;
		bubbleElapsed = theElapsed;
	}
	/**
	 * @return the stats of the most recent sort.
	 */
	public static String getShellStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nShell Sort-----------\n");
		sb.append("k   pass   cmp   exch\n");
		sb.append("---------------------\n");
		int passTotal = 0;
		int cmpTotal = 0;
		int exchTotal = 0;
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 4; col++) {
				sb.append(shellData[row][col] + "   ");
				
				if(col == 1) {
					passTotal += shellData[row][col];
				} else if (col == 2) {
					cmpTotal += shellData[row][col];
				} else {
					exchTotal += shellData[row][col]; 
				}	
			}
			sb.append("\n");
		}
		sb.append("---------------------\n");
		sb.append("Total: " + passTotal + " " 
				+ cmpTotal + " " + exchTotal + '\n');
		sb.append("Time Elapsed: " + new BigDecimal
				(shellElapsed).setScale(3, RoundingMode.FLOOR));
		sb.append(" seconds\n");
		return sb.toString();
	}
	/**
	 * @return the stats of the most recent sort.
	 */
	public static String getBubbleStats() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bubble Sort----------\n");
		sb.append("cmp: " + bubbleComps + "\n");
		sb.append("exch: " + bubbleSwaps + "\n");
		sb.append("pass: " + bubblePass + "\n");
		sb.append("Time Elapsed: " + new BigDecimal
				(bubbleElapsed).setScale(3, RoundingMode.FLOOR));
		sb.append(" seconds\n");
		return sb.toString();
	}
}

// LOTS OF OLD WORK / IDEAS--------------------------------------------------------------

//	LinkedList<Integer> list = new LinkedList<>();
//	LinkedNode<Integer> orange = theHead;
//	//populate list
//	while (orange.getNext() != null) {
//		list.add(orange.getData());
//		orange = orange.getNext();
//	}
//	list.add(orange.getData());
//	System.out.println("before:\n" + list.getList());
//	
//		LinkedNode<Integer> blue = list.getFirst();
//	orange = blue.getNext();
//	
//	for(int o = 0; o < list.getSize(); o++) {
//		for (int b = 0; b <= list.getSize() - o; b++) {
//			orange = list.goToNode(b);
//			if (orange.getNext() != null) {
//				if (orange.getData() > orange.getNext().getData()) {
//					list.swapBubble(b);			
//				}
//			}
//		}
//	}
//	System.out.println("after:\n" + list.getList());
//	return list;
	//Super sad this idea didnt work out :(
//	/**
//	 * @param theHead
//	 * @return
//	 */
//	public static LinkedList<Integer> bubbleSort(LinkedNode<Integer> theHead) {
//		LinkedList<Integer> list = new LinkedList<Integer>();
//		LinkedNode<Integer> orange = theHead;
//		//populate list
//		while (orange.getNext() != null) {
//			list.add(orange.getData());
//			orange = orange.getNext();
//		}
//		list.add(orange.getData());
//		
//		System.out.println("before:\n" + list.getList());
//		
//		LinkedNode<Integer> holder = list.getFirst();
//		
//		for(int i = 0; i < list.getSize(); i++) {
//			LinkedNode<Integer> blueTrail = null;
//			LinkedNode<Integer> blue = holder;
//			orange = blue.getNext();
//			while (orange.getNext() != null) {
//				System.out.println("During:\n" + list.getList());
////			for (int j = i; j < list.getSize(); i++) {
////				System.out.println(blueTrail + " " + blue + " " + orange);
//				if (orange.getData() < blue.getData()) {
////					list.swap(blue, orange, blueTrail);
//				}
//				//move a step down
//				if (blueTrail == null) {
//					blueTrail = holder;
//					blue = blueTrail.getNext();
//					orange = blue.getNext();
//				} 
//				if (blueTrail != null){
//					blueTrail = blueTrail.getNext();
//					blue = blueTrail.getNext();
//					orange = blue.getNext();
//				}
//				System.out.println("-----");
//			}
//			System.out.println("-------------");
//			holder = holder.getNext();
//		}
//		
//		System.out.println("After:\n" + list.getList());
//		return list;
//	}
//	
//}