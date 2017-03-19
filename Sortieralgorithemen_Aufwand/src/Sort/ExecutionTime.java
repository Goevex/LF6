package Sort;

public class ExecutionTime {
	public static long getDuration(long startTime, long endTime) {
		return endTime - startTime;
	}

	public static long getAverageDuration(long startTime[], long endTime[]) {
		long averageDuration = 0;
		try {
			for (int i = 0; i < endTime.length; i++) {
				averageDuration = averageDuration + (endTime[i] - startTime[i]);
			}
		} catch (Exception e) {
			System.out.println("Es ist ein Fehler aufgetreten " + e.toString());
		}
		return averageDuration / (long) endTime.length;
	}

	/*
	 * With these words I insist on an excursion to the great world of
	 * mathematic... which fades in obscurity over time. It's a shame that I'm
	 * not able to use my one year old knowledge about math.
	 */
	public static String calcAufwandsklasse(float ratio, long[] duration) {
		String aufwandsklasse = "Aufwandsklasse konnte nicht ermittelt werden.:(";
		int[] inclineArray = new int[duration.length - 1];
		boolean[][] hup = new boolean[duration.length - 1][5];
		for (int i = 1; i < duration.length - 1; i++) {
			int tmpPriorDur = (int) duration[i - 1];
			int tmpCurrentDur = (int) duration[i];
			int tmpIncline = tmpCurrentDur - tmpPriorDur;
			inclineArray[i - 1] = tmpIncline;
		}
		for (int i = 1; i < inclineArray.length - 1; i++) {
			int tmpPriorDInc = (int) inclineArray[i - 1];
			int tmpCurrentInc = (int) inclineArray[i];
			hup[i][0] = tmpPriorDInc == 0 && tmpCurrentInc == 0;
			hup[i][1] = tmpPriorDInc == tmpCurrentInc;
			hup[i][2] = (Math.pow(tmpPriorDInc, 2)) == tmpCurrentInc;
			hup[i][3] = Math.log(tmpPriorDInc) == tmpCurrentInc;
			hup[i][4] = tmpPriorDInc * Math.log(tmpPriorDInc) == tmpCurrentInc;
		}

		int[] hupCount = new int[5];
		for (int j = 0; j < hupCount.length; j++)
			for (int i = 0; i < hup.length; i++) {
				if (hup[i][j])
					hupCount[j]++;

			}

		int maxCountIndex = -1;
		if (hupCount[0] > 0)
			maxCountIndex = 0;
		int maxCount = hupCount[0];
		for (int i = 1; i < hupCount.length; i++) {
			if (hupCount[i] > maxCount) {
				maxCountIndex = i;
			}
		}

		switch (maxCountIndex) {
		case 0:
			aufwandsklasse = "O(1)";
			break;
		case 1:
			aufwandsklasse = "O(N)";
			break;
		case 2:
			aufwandsklasse = "O(N^2)";
			break;
		case 3:
			aufwandsklasse = "O(log2(N))";
			break;
		case 4:
			aufwandsklasse = "O(N*log2(N))";
			break;
		default:
			break;
		}
		return aufwandsklasse;
	}

	public static void printArray(long[] Array) {
		System.out.println("Laufzeiten:");
		for (int i = 0; i <= Array.length - 1; i++) {
			System.out.printf("%.9f sek", (double) Array[i]
					/ (double) 1000000000);
			if (i != (Array.length - 1)) {
				System.out.print(", ");
				if (i != 0 && i % 1000 == 0) {
					System.out.printf("\n %-20s", "");
				}
			} else
				System.out.printf("\n", "");
		}
	}
}
