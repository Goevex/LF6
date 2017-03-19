package Sort;

import java.util.Random;

public class IntArray {
	private int MaxIndex;
	private int[] IntArray;

	private long startSortTime;
	private long endSortTime;
	private long duration;

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

	public IntArray(int size) {
		int[] intArray = new int[size];
		setMaxIndex(size - 1);
		for (int i = 0; i <= this.MaxIndex; i++) {
			intArray[i] = new Random().nextInt(1000);
		}
		IntArray = intArray;
	}

	public IntArray(int[] intArray) {
		int length = intArray.length;
		IntArray = new int[length];
		System.arraycopy(intArray, 0, IntArray, 0, length);
		MaxIndex = length - 1;
	}

	public IntArray copy() {
		IntArray copyArray = new IntArray(this.getIntArray());
		return copyArray;
	}

	public void print() {
		System.out.print("[");
		for (int i = 0; i <= this.getMaxIndex(); i++) {
			System.out.printf("%3s", this.getIntArray()[i]);
			if (i != this.getMaxIndex()) {
				System.out.print(", ");
				if (i != 0 && i % 1000 == 0) {
					System.out.printf("\n %-20s", "");
				}
			}
		}
		System.out.println("]");
	}
}
