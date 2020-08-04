import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private JButton planterDesArbres = new JButton("Planter des arbres");
	private JPanel container = new JPanel();
	private JComboBox<Integer> combo = new JComboBox<Integer>();
	private int arbreAPlanter = 10, arbreDansMaForet;
	final private Integer[] arbrePlanterTableau = { 10, 20, 30, 40 };
	private JLabel ecran = new JLabel();
	String arbrePlanterString, quantiteArbreString;

	public Fenetre() {
		this.setTitle("Ma foret");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		initComposant();
		this.setContentPane(container);
		this.setVisible(true);
	}

	private void initComposant() {

		JLabel label = new JLabel("Il y a:");
		ecran = new JLabel("0");
		JLabel label1 = new JLabel("arbres dans ma foret");

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		planterDesArbres.addActionListener(new PlanterArbresListener());

		// Ici je prend mon tableau de int pour le mettre dans mon menu déroulant,
		// chaque fois que j'utilise mon menu déroulant, il y a une suite d'erreur que
		// je ne comprends pas, je ne sais pas si le probleme vient de ma syntaxe ici,
		// ou de la méthode qui y est rattaché
		for (int i = 0; i < arbrePlanterTableau.length; i++) {
			combo.addItem(arbrePlanterTableau[i]);
		}
		combo.addActionListener(new ComboListener());
		JPanel south = new JPanel();
		JPanel north = new JPanel();
		south.add(planterDesArbres);
		south.add(combo);
		container.add(south, BorderLayout.SOUTH);
		north.add(label);
		north.add(ecran);
		north.add(label1);
		north.setBorder(BorderFactory.createLineBorder(Color.black));
		container.add(north, BorderLayout.NORTH);

		// Le timer tueur d'arbre 
		Timer arbrepocalypse = new Timer();
		arbrepocalypse.schedule(new TimerTask() {
			public void run() {
				if (arbreDansMaForet > 50) {
					arbreDansMaForet -= (arbreDansMaForet / 20);
					quantiteArbreString = Integer.toString(arbreDansMaForet);
					ecran.setText(quantiteArbreString);
				}
			}
		}, 0, 500);
	}

	// Cette méthode est censé définir le nombre d'arbre qui seront planté à chaque
	// clique sur le bouton "planter des arbres", je ne sais pas si elle fonctionne,
	// ou si le problème vient de l'ajout du tableau de int dans le menu
	public class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			arbreAPlanter = (Integer) cb.getSelectedItem();
		}
	}

	public class PlanterArbresListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			arbreDansMaForet += arbreAPlanter;
			quantiteArbreString = Integer.toString(arbreDansMaForet);
			ecran.setText(quantiteArbreString);
		}
	}

	public static void main(String[] arg) {
		Fenetre fen = new Fenetre();
	}
}
