import java.util.Random;

public class mechanika
{
	/**
	 * @param x
	 *            Liczba wylosowana
	 * @param y
	 *            Liczba Wprowadzona
	 * @return
	 */
	public String sprawdz(int x, int y)
	{
		String wynik = "";
		if (y > x)
			wynik = "Podales za duzo!";
		if (y < x)
			wynik = "podales za malo!";
		if (y == x)
			wynik = "Wygrales";
		return wynik;
	}

	/**
	 * @param l
	 * 			Parametr zalezny od poziomu
	 * @return
	 */
	public int losowanie(int l)
	{
		Random r = new Random();
		int liczba = 0;
		if (l == 1)// Poziom ³atwy
		{
			liczba = r.nextInt(100) + 1;
		} else if (l == 2)// Poziom œredni
		{
			liczba = r.nextInt(1000) + 1;
		} else if (l == 3)// Poziom trudny
		{
			liczba = r.nextInt(10000) + 1;
		}
		return liczba;
	}

	/**
	 * @param t2
	 *            Czas koncowy(W mili sekundach)
	 * @param t1
	 *            Czas poczatkowy(W mili sekundach)
	 * @return Czas trwania w postaci ciagu znaków typu String i formacie mm:ss
	 */
	public String czas(long t2, long t1)
	{
		String czas = "";
		long tf = (t2 - t1) / 1000;// Konwersja czasu z milisekund na sekundy
		long minuty = 0;

		if (tf >= 60)
		{
			do
			{
				tf = tf - 60;
				minuty++;
			} while (tf >= 60);
		}
		if (tf < 10 && tf < 60)
		{
			czas = "0" + minuty + ":" + "0" + String.valueOf(tf);
		}
		if (tf > 10 && tf < 60)
		{
			czas = "0" + minuty + ":" + String.valueOf(tf);
		}

		return czas;
	}

}
