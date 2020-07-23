import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame {
	private JPanel container = new JPanel();
	private JLabel ecran = new JLabel();
	
	String[] tab_string = {"1","2","3","4","5","6","7","8","9","0",".","=","C","+","-","*","/"};
	JButton[] tab_button = new JButton[tab_string.length];
	
	private Dimension dim = new Dimension(50,40);
	private Dimension dim2 = new Dimension(50,31);
	private String operateur = "";
	private double chiffre1;
	private boolean clicOperateur = false, update = false;
	
	public Calculatrice() { //CONSTRUCTEUR PAR DEFAULT
		this.setSize(270, 270);
		this.setTitle("Calculatrice");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		initComposant(); // initialisation du conteneur avec tous les composants
		this.setContentPane(container);//On ajoute le conteneur
		this.setVisible(true);
	}
	
	private void initComposant() {
		//Création de l'ecran
		Font police = new Font("Arial",Font.BOLD,19);
		ecran = new JLabel("0");
		ecran.setFont(police);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(210,20));
		
		//Création des boutons "chiffre" et "opérateur"
		JPanel chiffre = new JPanel();
		chiffre.setPreferredSize(new Dimension(165,225));
		JPanel operateur = new JPanel();
		operateur.setPreferredSize(new Dimension(55,225));
		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(220,30));
		
		//Initialisation des boutons à partir du tableau
		for (int i=0; i<tab_string.length;i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			switch(i) {
			case 11:
				tab_button[i].addActionListener(new EgalListener());
				chiffre.add(tab_button[i]);
				break;
			case 12:
				tab_button[i].setForeground(Color.red);
				tab_button[i].addActionListener(new ResetListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 13:
				tab_button[i].addActionListener(new PlusListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 14:
				tab_button[i].addActionListener(new MoinsListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 15:
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			case 16:
				tab_button[i].addActionListener(new DivListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				break;
			default:
				chiffre.add(tab_button[i]);
				tab_button[i].addActionListener(new ChiffreListener());
				break;
			}
		}
		panEcran.add(ecran);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(panEcran, BorderLayout.NORTH);
		container.add(chiffre, BorderLayout.CENTER);
		container.add(operateur, BorderLayout.EAST);
		
	}
	
	//Methode permettant de calculer suivant l'opérateur
	private void calcul() {
		if(operateur.equals("+")) {
			chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("-")) {
			chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("*")) {
			chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("/")) {
			try {
			chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
			} catch(ArithmeticException e) {
				ecran.setText("0");
			}
		}
	}
	
	//Listener
	class ChiffreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton)e.getSource()).getText();
			if(update) {
				update = false;
			}
			else {
				if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
		
	}
	class EgalListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calcul();
			update = true;
			clicOperateur = false;
		}
		
	}
	class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("0");
			
		}
	
	}
	class PlusListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1=Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	
	}
	class MoinsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1=Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	
	}
	class MultiListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1=Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	
	}
	class DivListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(clicOperateur) {
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else {
				chiffre1=Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	
	}
	
}
