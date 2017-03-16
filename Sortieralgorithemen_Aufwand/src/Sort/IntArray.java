package Sort;

import java.util.Random;

public class IntArray {
	final static byte UNSORTED = 0;
	final static byte SORTED_ASC = 1;
	final static byte SORTED_DESC = 2;
	final static boolean RANDOM = true;
	final static boolean NOT_RANDOM = false;

	private int MaxIndex;
	private int[] IntArray;
	
	private static long startSortTime;
	private static long endSortTime;
	private static long duration;
	
	
	public long getDuration() {
		return duration;
	}

	public void setDuration(long d) {
		duration = d;
	}

	public long getEndSortTime() {
		return endSortTime;
	}

	public void setEndSortTime(long endsorttime) {
		endSortTime = endsorttime;
	}

	public long getStartSortTime() {
		return startSortTime;
	}

	public void setStartSortTime(long startortime) {
		startSortTime = startortime;
	}

	public int[] getIntArray() {
		return IntArray;
	}

	public void setIntArray(int[] intArray) {
		IntArray = intArray;
	}

	public int getMaxIndex() {
		return MaxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		MaxIndex = maxIndex;
	}

//	public IntArray(int size) {
//		int[] intArray = new int[size];
//		setMaxIndex(size - 1);
//		for (int i = 0; i <= this.MaxIndex; i++) {
//			intArray[i] = new Random().nextInt(1000);
//			IntArray = intArray;
//		}
//	}

	public IntArray(int size, byte sorted, boolean random) {
		int[] intArray = new int[size];
		setMaxIndex(size - 1);
		int min = 0;
		int max = 999;
		for (int i = 0; i <= this.MaxIndex; i++) {
			switch (sorted) {
			case UNSORTED:
				intArray[i] = new Random().nextInt(1000);
				break;
			case SORTED_ASC:  //Please dont try this :/
				if (random == RANDOM) {
					if (i != 0)
						min = intArray[i - 1];
					else
						min = 0;
					max = 1000;
					intArray[i] = randomIntInRangeOf(min, max);
				} else if (random == NOT_RANDOM) {
					intArray[i] = i;
				}
				break;
			case SORTED_DESC: //either this
				if (random == RANDOM) {
					min = 0;
					if (i != 0)
						max = intArray[i - 1];
					else
						max = 1000;
					intArray[i] = randomIntInRangeOf(min, max);
				} else if (random == NOT_RANDOM) {
					intArray[i] = MaxIndex-i;
				}
				break;
			}
		}
		IntArray = intArray;
	}

	public IntArray(int[] intArray) {
		int length = intArray.length;
		IntArray = new int[length];
		System.arraycopy(intArray, 0, IntArray, 0, length);
		MaxIndex = length - 1;
	}

	private int randomIntInRangeOf(int min, int max) {
		int randomInt = 0;
		if (!(min == 0 && max == 0))
			randomInt = new Random().nextInt(max - min) + min;
		return randomInt;
	}
	
	public IntArray copy(){
		IntArray copyArray = new IntArray(this.getIntArray());
		return copyArray;
	}
	
	public void print(){
		System.out.print("[");
		for (int i = 0; i <= this.getMaxIndex(); i++) {
			System.out.printf("%3s", this.getIntArray()[i]);
			if (i != this.getMaxIndex()) {
				System.out.print(", ");
				if (i != 0 &&i % 1000 == 0){
					System.out.printf("\n %-20s", "");
				}
			}
		}
		System.out.println("]");
	}
}
