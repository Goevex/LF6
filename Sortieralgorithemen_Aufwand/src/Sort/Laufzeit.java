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
			boolean[] weighArray = new boolean[n.length - 1];
			for (int i = 1; i < n.length; i++) {
				weighArray[0]= (n[i] / duration[i] == n[i-1] / duration[i-1]); //proportional
				if (true) {

				} else if (true) {

				} else if (true) {

				} else if (true) {

				}

			}
		}
		return aufwandsklasse;
	}
}
