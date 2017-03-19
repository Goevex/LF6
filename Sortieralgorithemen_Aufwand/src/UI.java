import java.util.Scanner;

import Sort.*;

public class UI {
	private static int arraySize;
	private static int countSort;
	private static int incArraySize;
	private static int[] incedArraySize;

	private static IntArray intArray[];
	private static IntArray sortedIntArray[];

	private static byte sortOption;
	private static boolean sortDirectionOption;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Wie viele Arrays sollen erstellt werden?");
			try {
				countSort = scanner.nextInt();
				intArray = new IntArray[countSort];
				sortedIntArray = new IntArray[countSort];
				incedArraySize = new int[countSort];
				break;
			} catch (Exception e) {
				System.out.println("Ungültige Eingabe!");
				scanner.next();
			}
		}
		while (true) {
			System.out
					.println("Welche Länge sollen die zu generierenden Arrays haben?");
			try {
				arraySize = scanner.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Ungültige Eingabe!");
				scanner.next();
			}
		}
		if (countSort > 1)
			while (true) {
				System.out
						.println("Um welche Länge sollen die Arrays sich nach jedem Durchlauf erhöhen?");
				try {
					incArraySize = scanner.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Ungültige Eingabe!");
					scanner.next();
				}
			}
		System.out.println("Arrays werden erstellt...");
		for (int i = 0; i < countSort; i++) {
			incedArraySize[i] = arraySize + (i * incArraySize);
			intArray[i] = new IntArray(incedArraySize[i]);
			sortedIntArray[i] = intArray[i].copy();
		}
		System.out.println("Arrays wurden erstellt!");
		while (true) {
			System.out
					.println("Welchen Sortieralgorithmus möchtest du anwenden?");
			System.out
					.println("Bubble-Sort(B), Quick-Sort(Q), Insertion-Sort(I), Selection-Sort(S)");
			String optSort = scanner.next();
			if (optSort.equalsIgnoreCase("B")) {
				sortOption = 0;
				break;
			} else if (optSort.equalsIgnoreCase("Q")) {
				sortOption = 1;
				break;
			} else if (optSort.equalsIgnoreCase("I")) {
				sortOption = 2;
				break;
			} else if (optSort.equalsIgnoreCase("S")) {
				sortOption = 3;
				break;
			} else
				System.out.println("Ungültige Eingabe!");
		}
		while (true) {
			System.out
					.println("Soll aufwärts(A) oder abwärts(D) sortiert werden?");
			String optDirection = scanner.next();
			if (optDirection.equalsIgnoreCase("A")) {
				sortDirectionOption = true;
				break;
			} else if (optDirection.equalsIgnoreCase("D")) {
				sortDirectionOption = false;
				break;
			} else
				System.out.println("Ungültige Eingabe!");
		}
		for (int i = 0; i < countSort; i++) {
			try {
				switch (sortOption) {
				case 0:
					SortAlgorithms.bubbleSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 1:
					SortAlgorithms.quickSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 2:
					SortAlgorithms.insertionSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 3:
					SortAlgorithms.selectionSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				}
				sortedIntArray[i].setDuration(ExecutionTime.getDuration(
						sortedIntArray[i].getStartSortTime(),
						sortedIntArray[i].getEndSortTime()));
			} catch (StackOverflowError e) {
				System.out
						.println("Ein Fehler ist aufgetreten: Die Array-Länge für diesen Sortieralgorithmus ist zu groß.");
				System.exit(0);
			}
		}
		printArrays();
		printLaufzeit();

		scanner.close();
	}

	private static void printArrays() {
		for (int i = 0; i < countSort; i++) {
			System.out.println("");
			System.out.printf("%-20s", "Original-Array:");
			intArray[i].print();
			System.out.printf("%-20s", "Sortiertes-Array:");
			sortedIntArray[i].print();
		}
	}

	private static void printLaufzeit() {
		System.out.println("");
		String tempSortName = "";
		switch (sortOption) {
		case 0:
			tempSortName = "Bubble-Sort";
			break;
		case 1:
			tempSortName = "Quick-Sort";
			break;
		case 2:
			tempSortName = "Insertion-Sort";
			break;
		case 3:
			tempSortName = "Selection-Sort";
			break;
		}

		float sizeRatio = (float) incArraySize / (float) arraySize;
		long[] duration = new long[sortedIntArray.length];
		for (int i = 0; i < duration.length; i++) {
			duration[i] = sortedIntArray[i].getDuration();
		}

		ExecutionTime.printArray(duration);
		System.out.println("");
		System.out.print("Sortiert mit:\n" + tempSortName
				+ "\n\nAufwandsklasse im Durchschnitt:\n"
				+ ExecutionTime.calcAufwandsklasse(sizeRatio, duration));
		// }
	}
}
