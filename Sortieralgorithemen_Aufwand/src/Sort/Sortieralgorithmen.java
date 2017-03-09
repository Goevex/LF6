package Sort;

public class Sortieralgorithmen {
	final static boolean ASC = true;
	final static boolean DESC = false;

	public static void bubbleSort(IntArray randomSortArray,
			boolean direction) {
		int[] sortArray = randomSortArray.getIntArray();
		int n = randomSortArray.getMaxIndex();
		if (direction == ASC) {
			do {
				int newn = 1;
				for (int i = 0; i < n; ++i) {
					if (sortArray[i] > sortArray[i + 1]) {
						swap(sortArray, i, i + 1);
						newn = i + 1;
					}
				}
				n = newn;
			} while (n > 1);
		} else if (direction == DESC) {
			do {
				int newn = 1;
				for (int i = 0; i < n; ++i) {
					if (sortArray[i] < sortArray[i + 1]) {
						swap(sortArray, i, i + 1);
						newn = i + 1;
					}
				}
				n = newn;
			} while (n > 1);
		}

//		return randomSortArray;
	}

	private static int[] swap(int[] swapArray, int pos1, int pos2) {
		int cache = swapArray[pos1];
		swapArray[pos1] = swapArray[pos2];
		swapArray[pos2] = cache;
		return swapArray;
	}

	public static void quickSort(IntArray intArray, boolean direction) {
		int low = 0;
		int high = intArray.getMaxIndex();
		int[] A = intArray.getIntArray();
		qSort(A, low, high, direction);
//		return new IntArray(qSort(A, low, high, direction));
	}

	private static int[] qSort(int[] intArr, int l, int r, boolean direction) {
		int q;
		if (l < r) {
			q = partition(intArr, l, r, direction);
			qSort(intArr, l, q, direction);
			qSort(intArr, q + 1, r, direction);
		}
		return intArr;
	}

	private static int partition(int[] intArr, int l, int r, boolean direction) {
		int i, j, x = intArr[(l + r) / 2];
		i = l - 1;
		j = r + 1;

		if (direction == ASC) {
			do {
				i++;
			} while (intArr[i] < x);

			do {
				j--;
			} while (intArr[j] > x);
		} else if (direction == DESC) {
			do {
				i++;
			} while (intArr[i] > x);

			do {
				j--;
			} while (intArr[j] < x);
		}

		if (i < j) {
			int k = intArr[i];
			intArr[i] = intArr[j];
			intArr[j] = k;
		} else {
			return j;
		}

		return -1;
	}

	public static void insertionSort(IntArray intArray, boolean direction) {
		int[] sortArray = intArray.getIntArray();
		int j;
		int sortValue;
		for (int i = 1; i <= intArray.getMaxIndex(); i++) {
			sortValue = sortArray[i];
			j = i;
			if (direction == ASC)
				while ((j > 0) && (sortArray[j - 1] > sortValue)) {
					sortArray[j] = sortArray[j - 1];
					j--;
				}
			else if (direction == DESC)
				while ((j > 0) && (sortArray[j - 1] < sortValue)) {
					sortArray[j] = sortArray[j - 1];
					j--;
				}
			sortArray[j] = sortValue;
		}
		intArray.setIntArray(sortArray);
		//return intArray;
	}
	
	public static void selectionSort(IntArray sortIntArray, boolean direction) {
		int[] sortArray = sortIntArray.getIntArray();
		if(direction==ASC)
		for (int i = 0; i <= sortIntArray.getMaxIndex() - 1; i++) {
			for (int j = i + 1; j <= sortIntArray.getMaxIndex(); j++) {
				if (sortArray[i] > sortArray[j]) {
					int temp = sortArray[i];
					sortArray[i] = sortArray[j];
					sortArray[j] = temp;
				}
			}
		}
		else if(direction==DESC)
			for (int i = 0; i <= sortIntArray.getMaxIndex() - 1; i++) {
				for (int j = i + 1; j <= sortIntArray.getMaxIndex(); j++) {
					if (sortArray[i] < sortArray[j]) {
						int temp = sortArray[i];
						sortArray[i] = sortArray[j];
						sortArray[j] = temp;
					}
				}
			}
		sortIntArray.setIntArray(sortArray);
//		return sortIntArray;
	}
}
