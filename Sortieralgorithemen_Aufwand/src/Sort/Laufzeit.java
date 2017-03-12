package Sort;

public class Laufzeit {
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

	public static String calcAufwandsklasse(int[] n, long[] duration) {
		String aufwandsklasse = "Bei der Berechnung der Aufwandsklasse ist ein Fehler aufgetreten.";
		if (n.length == duration.length) {
			aufwandsklasse = "Aufwandsklasse konnte nicht ermittelt werden.";
			boolean[][] weighArray = new boolean[5][n.length - 1];
			for (int i = 1; i < n.length - 1; i++) {
				double tmpN = (double) n[i];
				double tmpNPrior = (double) n[i - 1];
				double tmpDuration = (double) duration[i];
				double tmpDurationPrior = (double) duration[i - 1];
				weighArray[0][i - 1] = (tmpN == tmpNPrior && tmpDuration == tmpDuration);// konstant
				weighArray[1][i - 1] = (tmpN / tmpDuration == tmpNPrior
						/ tmpDuration); // proportional
				weighArray[2][i - 1] = (Math.pow(tmpN, 2) / tmpDuration == Math
						.pow(tmpNPrior, 2) / tmpDuration);// duration
															// proportional zu
															// n^2
				weighArray[3][i - 1] = ((double) (int) (tmpN / 2) / tmpDuration == (double) (int) (tmpNPrior / 2)
						/ tmpDurationPrior);// duration proportional zur anzahl,
											// in der sich n durch 2 teilen
											// lässt
				weighArray[4][i - 1] = (tmpN * Math.log(tmpN) / tmpDuration == tmpNPrior
						* Math.log(tmpNPrior) / tmpDurationPrior);// duration
																	// ist
				// proprtional zu n
				// * log(2)
			}

			int countTrue = 0;
			int[] tmpCountTrue = new int[5];
			for (int j = 0; j < 5; j++) {
				for (int i = 0; i < weighArray[j].length; i++) {
					if (weighArray[j][i] == true){
						tmpCountTrue[j]++;
						countTrue++;
					}
				}
			}

			int maxTrue = tmpCountTrue[0];
			int maxTrueIndex = -1;
			for (int i = 1; i < tmpCountTrue.length; i++) {
				if (tmpCountTrue[i] >= maxTrue) {
					maxTrue = tmpCountTrue[i];
					maxTrueIndex = i;
				}
			}
			double oddTrue = (double)tmpCountTrue[maxTrueIndex] / (double)countTrue;
			aufwandsklasse = "Mit einer Wahrscheinlichkeit von "
					+ String.format("%.15f",Double.valueOf(oddTrue)*100) + "% ist die Aufwandsklasse ";

			switch (maxTrueIndex) {
			case 0:
				aufwandsklasse = aufwandsklasse + "O(1)";
				break;
			case 1:
				aufwandsklasse = aufwandsklasse + "O(N)";
				break;
			case 2:
				aufwandsklasse = aufwandsklasse + "O(N^2)";
				break;
			case 3:
				aufwandsklasse = aufwandsklasse + "O(log2(N))";
				break;
			case 4:
				aufwandsklasse = aufwandsklasse + "O(N*log2(N))";
				break;
			default:
				break;
			}
		}
		return aufwandsklasse;
	}
}
