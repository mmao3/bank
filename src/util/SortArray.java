package util;

public class SortArray {

	public static void getSortArray(Object data[][], int index, int model) {
		System.out.println(data.length);
		System.out.println(data[0].length);
		for (int j = 0; j < data.length - 1; j++)
			for (int i = 0; i < data.length - 1 - j; i++)
				if (compareTo(data[i][index], data[i + 1][index], model) > 0) {
					Object[] t = data[i];
					data[i] = data[i + 1];
					data[i + 1] = t;

				}

	}

	public static int compareTo(Object a, Object b, int model) {

		if (model == 3)
			return ((String) a).compareTo((String) b);
		else if (model == 1) {
			if (a.getClass().getName().compareTo("5".getClass().getName()) == 0) {

				a = Integer.parseInt(((String) a).trim());

			}
			if (b.getClass().getName().compareTo("5".getClass().getName()) == 0)
				b = Integer.parseInt(((String) b).trim());
			return (Integer) a < (Integer) b ? -1 : 1;
		}

		else {
			System.out.println("Me");

			if (a.getClass().getName().compareTo("5".getClass().getName()) == 0)
				a = Double.parseDouble(((String) a).trim());
			if (b.getClass().getName().compareTo("5".getClass().getName()) == 0)
				b = Double.parseDouble(((String) b).trim());
			return (Double) a < (Double) b ? -1 : 1;
		}

	}

	public static void main(String[] argus) {

	}

}