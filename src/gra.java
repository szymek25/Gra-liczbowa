import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class gra extends JFrame implements ActionListener
{
	private JLabel l;
	private JButton latwy, sredni, trudny, wyjscie, sprawdz;
	private static int licznik = 0, liczba, traf, ogra = 1, odp;
	private static JTextField pole, pole2;
	private static String wynik;
	private JCheckBox check;
	private static long t1, t2;

	public gra()
	{
		setSize(400, 300);
		setTitle("prosta gra liczbowa");
		setLayout(null);

		l = new JLabel("Wybierz poziom trudnosci");
		l.setBounds(55, 50, 340, 40);
		l.setFont(new Font("Arial", Font.BOLD, 20));
		add(l);

		latwy = new JButton("Latwy(99)");
		latwy.setBounds(125, 85, 110, 20);
		latwy.addActionListener(this);
		add(latwy);

		sredni = new JButton("Sredni(999)");
		sredni.setBounds(125, 105, 110, 20);
		sredni.addActionListener(this);
		add(sredni);

		trudny = new JButton("Trudny(9999)");
		trudny.setBounds(125, 125, 110, 20);
		trudny.addActionListener(this);
		add(trudny);

		wyjscie = new JButton("Wyjscie");
		wyjscie.setBounds(125, 145, 110, 20);
		wyjscie.addActionListener(this);
		add(wyjscie);

		sprawdz = new JButton("Sprawdz");
		sprawdz.setBounds(125, 230, 110, 20);
		sprawdz.addActionListener(this);
		add(sprawdz);

		pole = new JTextField("Poni¿ej wprowadzaj cyfry");
		pole.setBounds(125, 185, 200, 20);
		add(pole);
		pole.setEditable(false);

		pole2 = new JTextField();
		pole2.setBounds(125, 210, 40, 20);
		add(pole2);
		pole2.setEditable(false);
		pole2.setBackground(Color.YELLOW);
		pole2.addActionListener(this);

		check = new JCheckBox("Wlacz ograniczenia prob");
		check.setBounds(121, 164, 200, 20);
		add(check);
		check.setToolTipText("Latwy(max 7) Sredni(max 10) Trudny(max 15)");

	}

	mechanika graj = new mechanika();

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();

		if (source == wyjscie)
		{
			odp = JOptionPane.showConfirmDialog(this, "Czy na pewno wyjsc ?", "Wyjscie", JOptionPane.YES_NO_OPTION);
			if (odp == JOptionPane.YES_OPTION)
				dispose();
		} else if (source == latwy)
		{
			rozpoczecie(1);
			if (check.isSelected())
				ogra = -7;
			JOptionPane.showMessageDialog(this, "Wylosowano liczbe od 1 do 99", "Rozpoczeto rozgrywke",
					JOptionPane.INFORMATION_MESSAGE);

		}

		else if (source == sredni)
		{
			rozpoczecie(2);
			if (check.isSelected())
				ogra = -10;
			JOptionPane.showMessageDialog(this, "Wylosowano liczbe od 1 do 999", "Rozpoczeto rozgrywke",
					JOptionPane.INFORMATION_MESSAGE);

		}

		else if (source == trudny)
		{
			rozpoczecie(3);
			if (check.isSelected())
				ogra = -15;
			JOptionPane.showMessageDialog(this, "Wylosowano liczbe od 1 do 9999", "Rozpoczeto rozgrywke",
					JOptionPane.INFORMATION_MESSAGE);

		}

		else if (source == sprawdz || source == pole2)
		{

			oblsugaPole2();

			if (wynik.equals("Wygrales"))
			{
				t2 = System.currentTimeMillis();
				JOptionPane.showMessageDialog(this,
						"Liczba twoich prob: " + licznik + "\n Czas twojej rozgrywki: " + graj.czas(t2, t1), "Wygrana",
						JOptionPane.INFORMATION_MESSAGE);
				zerowanieZmiennych();
			}
			if (ogra == 0)
			{
				JOptionPane.showMessageDialog(this, "Przekroczyles liczbe prob ", "Koniec Gry",
						JOptionPane.ERROR_MESSAGE);
				zerowanieZmiennych();
			}
			if (ogra == -3)
				JOptionPane.showMessageDialog(this, "Uwaga zostaly 3 proby!", "Ostrzezenie",
						JOptionPane.WARNING_MESSAGE);
			if (ogra == -1)
				JOptionPane.showMessageDialog(this, "Uwaga zostala 1 proba!", "Ostrzezenie",
						JOptionPane.WARNING_MESSAGE);

		}

	}

	public static void main(String[] args)
	{
		gra okno = new gra();
		okno.setVisible(true);
		okno.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void oblsugaPole2()
	{
		traf = Integer.parseInt(pole2.getText());
		wynik = graj.sprawdz(liczba, traf);
		pole.setText(wynik);
		pole2.setText("");
		licznik++;
		ogra++;
	}

	public void zerowanieZmiennych()
	{
		licznik = 0;
		liczba = 0;
		ogra = 1;
		pole.setText("Mozesz ponownie wybrac poziom");
		pole2.setEditable(false);
	}

	
	public void rozpoczecie(int x)
	{
		liczba = graj.losowanie(x);
		pole.setText("Poni¿ej wprowadzaj cyfry");
		pole2.setEditable(true);
		t1 = System.currentTimeMillis();

	}

}
