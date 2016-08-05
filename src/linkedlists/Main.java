package linkedlists;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Main { 
	/* 
	 * readValues(): read values from a file and 
	 * build a linked list of integers.
	 * displayList():  Write  a  routine  
	 * to  display  the  contents  of  the linked list.
	 */
	public static void main(String[] args) throws IOException {
		String filePrefix = "./src/input/";
		String fileSufix = ".txt";
		LinkedList<String> fileList = new LinkedList<>();
		fileList.add("inorder100");
		fileList.add("inorder1000");
		fileList.add("inorder10000");
		fileList.add("random100");
		fileList.add("random1000");
		fileList.add("random10000");
		fileList.add("reverse100");
		fileList.add("reverse1000");
		fileList.add("reverse10000");
		
		//files
		LinkedNode<String> runner = fileList.getHead();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < fileList.getSize(); i++) {
			LinkedList<Integer> inputForBubble = 
					readValues(filePrefix + runner.getData() + fileSufix);
			LinkedList<Integer> inputForShell = 
					readValues(filePrefix + runner.getData() + fileSufix);
			LinkedList<Integer> bubbleList =
					new LinkedList<Integer>(Sort.bubbleSort(inputForBubble.getHead()));	
			LinkedList<Integer> shellList = 
					new LinkedList<Integer>(Sort.shellSort(inputForShell));	
			sb.append(displayList(runner.getData()));
			if (bubbleList.getSize() <= 20) {
				sb.append("Sorted values: \n");
				sb.append(getValuesofList(bubbleList));
			}
			runner = runner.getNext();
		}
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		writer.print(sb.toString());
		writer.close();

		
	}
	
	private static LinkedList<Integer> readValues(final String theFile) 
			throws IOException {
		Scanner s = new Scanner(new File(theFile));
		LinkedList<Integer> newList = new LinkedList<>();
		while(s.hasNextInt()) {
			newList.add(s.nextInt());
		}
		s.close();
		return newList;
	}
	private static String displayList(String thePath) {
		return "\n" + thePath + " > output.txt\n" + 
				Sort.getBubbleStats() + Sort.getShellStats();
	}
	private static String getValuesofList(LinkedList<Integer> theList) {
		return theList.getList();
	}
}

//test stuff---------------------
//

//inputList.add(r.nextInt(numb));
//inputList2.add(r.nextInt(numb));
//inputList.add(i);
//inputList2.add(i);