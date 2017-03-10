import java.util.Scanner;

import Sort.*;

public class Oberflaeche {
	private static int arraySize;
	private static int countSort;
	private static int incArraySize;
	private static int[] incedArraySize;

	private static IntArray intArray[];
	private static IntArray sortedIntArray[];

	private static byte arrayOption;
	private static byte sortOption;
	private static boolean sortDirectionOption;
	private static boolean randomOption;
	private static boolean countSortOption;

	private static long[] startSortTime;
	private static long[] endSortTime;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Wie viele Arrays sollen erstellt werden?");
			try {
				countSort = scanner.nextInt();
				intArray = new IntArray[countSort];
				sortedIntArray = new IntArray[countSort];
				incedArraySize = new int[countSort];
				startSortTime = new long[countSort];
				endSortTime = new long[countSort];
				break;
			} catch (Exception e) {
				System.out.println("Ungültige Eingabe!");
				scanner.next();
			}
		}
		if (countSort > 1)
			while (true) {
				System.out
						.println("Soll die größe der Arrays konstant(K) bleiben oder zunehmen(Z)?");
				String optCountSortOption = scanner.next();
				if (optCountSortOption.equalsIgnoreCase("K")) {
					countSortOption = false;
					break;
				} else if (optCountSortOption.equalsIgnoreCase("Z")) {
					countSortOption = true;
					break;
				} else
					System.out.println("Ungültige Eingabe!");
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
		if (countSortOption)
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
		else
			incArraySize = 0;
		while (true) {
			System.out
					.println("Sollen die Arrays unsortiert(U) generiert werden?");
			System.out
					.println("Aufsteigend sortiert(A) oder absteigend sortiert(D) [nicht empfohlen]");
			String optGenerate = scanner.next();
			String optRandom;
			if (optGenerate.equalsIgnoreCase("U")) {
				arrayOption = 0;
				randomOption = true;
				break;
			} else if (optGenerate.equalsIgnoreCase("A")) {
				arrayOption = 1;
				while (true) {
					System.out
							.println("Sollen die Zahlen zufällig(Z) oder aufeinanderfolgend(A) sortiert sein?");
					optRandom = scanner.next();
					if (optRandom.equalsIgnoreCase("Z")) {
						randomOption = true;
						break;
					} else if (optRandom.equalsIgnoreCase("A")) {
						randomOption = false;
						break;
					} else
						System.out.println("Ungültige Eingabe!");
				}
				break;

			} else if (optGenerate.equalsIgnoreCase("D")) {
				arrayOption = 2;
				while (true) {
					System.out
							.println("Sollen die Zahlen zufällig(Z) oder aufeinanderfolgend(A) sortiert sein?");
					optRandom = scanner.next();
					if (optRandom.equalsIgnoreCase("Z")) {
						randomOption = true;
						break;
					} else if (optRandom.equalsIgnoreCase("A")) {
						randomOption = false;
						break;
					} else
						System.out.println("Ungültige Eingabe!");
				}
				break;
			} else
				System.out.println("Ungültige Eingabe!");
		}
		System.out.println("Arrays werden erstellt...");
		for (int i = 0; i < countSort; i++) {
			incedArraySize[i] = arraySize + (i * incArraySize);
			intArray[i] = new IntArray(incedArraySize[i],
					arrayOption, randomOption);
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
				startSortTime[i] = System.nanoTime();
				switch (sortOption) {
				case 0:
					Sortieralgorithmen.bubbleSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 1:
					Sortieralgorithmen.quickSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 2:
					Sortieralgorithmen.insertionSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				case 3:
					Sortieralgorithmen.selectionSort(sortedIntArray[i],
							sortDirectionOption);
					break;
				}
				endSortTime[i] = System.nanoTime();
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
			System.out.printf("%-20s", "Laufzeit: ");
			System.out.printf("%.9f sek%n", ((double) Laufzeit.getDuration(
					startSortTime[i], endSortTime[i]) / 1000000000));
			System.out.printf("%-20s", "Sortiertes-Array:");
			sortedIntArray[i].print();
		}
	}
	
	private static void printLaufzeit(){
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
		long[] duration = new long[startSortTime.length];
		for (int i = 0;i<duration.length;i++)
		duration[i] = Laufzeit.getDuration(
				startSortTime[i], endSortTime[i]);
		System.out.print("Sortiert mit " + tempSortName
				+ "\nAufwandsklasse im Durchschnitt: " + Laufzeit.calcAufwandsklasse(incedArraySize, duration));
		System.out.println("");
		if (countSort > 1) {
			System.out.println("");
			System.out.printf("%-20s", "Durchschnittliche Lauftzeit: ");
			System.out.printf("%.9f sek%n", ((double) Laufzeit
					.getAverageDuration(startSortTime, endSortTime) / 1000000000));
		}
	}
}
