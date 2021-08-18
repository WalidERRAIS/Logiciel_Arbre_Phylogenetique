package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import outils.*;

/**
 * Affiche la fenêtre principale
 * @author walid
 *
 */
public class Menu extends JFrame {
	//Attributs
	private JInternalFrame internalFrame;
	private JLabel labelStepOne;
	private JLabel labelChoixTypeSequence;
	private JComboBox choixTypeSequence;
	private JLabel labelSequenceFormat;
	private JScrollPane scrollPane;
	private JTextArea entrezSequence;
	private JPopupMenu popupMenuEntrezSeq;
	private JMenuItem itemCopier;
	private JMenuItem itemColler;
	private JMenuItem itemCouper;
	private JLabel labelChoixFichier;
	private JLabel labelFichierChoisi;
	private JButton btnChoisirFichier;
	private StringBuilder sequenceFichier = null;
	private JLabel labelStepTwo;
	private JLabel labelGapPenalty;
	private JTextField gapPenalty;
	private JButton btnRunMultipleAlignment;


	/**
	 * Construction du Menu principal 
	 */
	public Menu() {
		/*
		 * Page principale
		 */
		super("Menu Principal");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //tue le processus
		this.setSize(899, 600); //change la taille de la fenêtre
		this.setLocationRelativeTo(null); //centre la fenêtre
		this.setResizable(false);
		/*
		 * Creation et configuration content Pane
		 */
		JPanel contentPane = (JPanel) this.getContentPane();
		getContentPane().setLayout(null);

		/*
		 * Ajout de la barre d'outils au content pane
		 */
		contentPane.add(createToolBar());

		/*
		 * Ajout du frame interne Alignement
		 */
		contentPane.add(createInternalFrameAlignement());

	}

	/**
	 * Construction de la barre d'outils
	 * @return toolBar retourne la barre d'outils
	 */
	private JToolBar createToolBar() {
		/*
		 * Configuration barre d'outils
		 */
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 79, 563);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		/*
		 * Bouton Align
		 */
		JButton btnAlign = new JButton("ALIGN");
		toolBar.add(btnAlign);

		/*
		 * Bouton Phylogeny
		 */
		JButton btnPhylogeny = new JButton("PHYLOGENY");
		toolBar.add(btnPhylogeny);

		return toolBar;
	}


	/**
	 * Construction d'un internal frame pour l'alignement
	 * @return internalFrame retourne le frame interne d'Alignement
	 */
	private JInternalFrame createInternalFrameAlignement() {
		internalFrame = new JInternalFrame("ALIGNEMENT Multiple");
		internalFrame.setBounds(79, 0, 806, 563);
		internalFrame.getContentPane().setLayout(null);
		internalFrame.setVisible(true);

		/*
		 * Cadre step 1
		 */
		createLabelStepOne();

		/*
		 * label choix type sequence
		 */
		createLabelChoixTypeSequence();

		/*
		 * Choix type de sequence
		 */
		createBoxChoixTypeSequence();

		/*
		 * Cadre indication format de sequence
		 */
		createLabelSequenceFormat();

		/*
		 * Cadre entrez sequences au format fasta avec barre pour scroll
		 */
		createTextAreaEntrezSeqScrollPane();

		/*
		 * popup menu copier coller couper
		 */
		createPopupMenuEntrezSeq();		

		/*
		 * cadre choix d'un fichier contenant les sequences à aligner
		 */
		createLabelChoixFichier();

		/*
		 * affiche aucun fichier choisi par default et modifie avec le nom du fichier choisi
		 */
		createLabelfichierChoisi();

		/*
		 * bouton choix d'un fichier de sequences extension fasta
		 */
		createBtnChoisirFichier();

		/*
		 * Cadre step 2
		 */
		createLabelStepTwo();

		/*
		 * label gap penalty
		 */
		createLabelGapPenalty();

		/*
		 * saisi gap penalty
		 */
		saisiGapPenalty();

		/*
		 * bouton lance alignement multiple
		 */
		createBtnRunMultipleAlignment();

		return internalFrame;
	}

	/**
	 * Affiche un cadre avec l'instruction de l'étape 1
	 */
	private void createLabelStepOne() {
		labelStepOne = new JLabel("STEP 1 - Entrez vos sequences à aligner");
		labelStepOne.setForeground(new Color(255, 0, 0));
		labelStepOne.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelStepOne.setBounds(6, 19, 314, 31);
		internalFrame.getContentPane().add(labelStepOne);
	}

	/**
	 * Affiche un cadre avec l'instruction choix du type de séquences
	 */
	private void createLabelChoixTypeSequence() {
		labelChoixTypeSequence = new JLabel("Sélectionner le type des séquences");
		labelChoixTypeSequence.setFont(new Font("SansSerif", Font.PLAIN, 14));
		labelChoixTypeSequence.setBounds(6, 62, 248, 31);
		internalFrame.getContentPane().add(labelChoixTypeSequence);
	}

	/**
	 * Affiche une liste pour choisir le type de séquences
	 */
	private void createBoxChoixTypeSequence() {
		String typeSequence[]= {"PROTEINE", "ADN"};
		choixTypeSequence = new JComboBox(typeSequence);
		choixTypeSequence.setBounds(6, 88, 773, 26);
		internalFrame.getContentPane().add(choixTypeSequence);
	}

	/**
	 * Affiche un cadre avec l'instruction du format de séquences à entrer
	 */
	private void createLabelSequenceFormat() {
		labelSequenceFormat = new JLabel("Entrez les séquences au Format Fasta");
		labelSequenceFormat.setFont(new Font("SansSerif", Font.PLAIN, 14));
		labelSequenceFormat.setBounds(6, 125, 248, 16);
		internalFrame.getContentPane().add(labelSequenceFormat);
	}

	/**
	 * Affiche un champs pour y rentrer les séquences
	 */
	private void createTextAreaEntrezSeqScrollPane() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 153, 773, 131);
		internalFrame.getContentPane().add(scrollPane);
		entrezSequence = new JTextArea();
		scrollPane.setViewportView(entrezSequence);
		entrezSequence.setLineWrap(true);
	}

	/**
	 * Affiche un menu popup au clic droit
	 * de la souris dans le champs de saisi des séquences
	 * permettant de copier, coller ou couper
	 */
	private void createPopupMenuEntrezSeq() {
		//popup menu
		popupMenuEntrezSeq = new JPopupMenu();
		addPopup(entrezSequence, popupMenuEntrezSeq);

		//item copier
		itemCopier = new JMenuItem("Copier");
		itemCopier.addActionListener(new ActionListener() {
			/**
			 * au clic permet de copier l'élément sélectionné
			 */
			public void actionPerformed(ActionEvent e) {
				entrezSequence.copy();
			}
		});
		itemCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		popupMenuEntrezSeq.add(itemCopier);

		//item coller
		itemColler = new JMenuItem("Coller");
		itemColler.addActionListener(new ActionListener() {
			/**
			 * au clic permet de coller l'élément sélectionné
			 */
			public void actionPerformed(ActionEvent e) {
				entrezSequence.paste();
			}
		});
		itemColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		popupMenuEntrezSeq.add(itemColler);

		//item couper
		itemCouper = new JMenuItem("Couper");
		itemCouper.addActionListener(new ActionListener() {
			/**
			 * au clic permet de coller l'élément sélectionné
			 */
			public void actionPerformed(ActionEvent e) {
				entrezSequence.cut();
			}
		});
		itemCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		popupMenuEntrezSeq.add(itemCouper);
	}

	/**
	 * Ajoute un menu popup dans un composant
	 * @param component composant dans lequel on ajoute le menu
	 * @param popup menu à ajouter
	 */
	private static void addPopup(Component component, final JPopupMenu popup) {
		/**
		 * Affiche menu popup et ses items lors d'un clic droit dans le coposant
		 */
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * Affiche un cadre avec l'instruction de choix d'un fichier contenant les séquences
	 */
	private void createLabelChoixFichier() {
		labelChoixFichier = new JLabel("Ou donner le fichier contenant les séquences");
		labelChoixFichier.setFont(new Font("SansSerif", Font.PLAIN, 14));
		labelChoixFichier.setBounds(16, 287, 302, 26);
		internalFrame.getContentPane().add(labelChoixFichier);
	}

	/**
	 * Affiche un cadre avec l'instruction "Aucun fichier choisi" 
	 * ou le nom du fichier choisi renvoyé par la méthode choixFichier
	 * @see choixFichier
	 */
	private void createLabelfichierChoisi() {
		labelFichierChoisi = new JLabel("Aucun fichier choisi");
		labelFichierChoisi.setFont(new Font("SansSerif", Font.PLAIN, 14));
		labelFichierChoisi.setBounds(506, 290, 273, 23);
		internalFrame.getContentPane().add(labelFichierChoisi);
	}

	/**
	 * Affiche un bouton "Choisir un fichier" 
	 * qui au clic appelle la méthode choixFichier
	 * @see choixFichier
	 */
	private void createBtnChoisirFichier() {
		btnChoisirFichier = new JButton("Choisir un fichier");
		btnChoisirFichier.addActionListener(new ActionListener() {
			/**
			 * Clic sur le boutton "Choisir un fichier"
			 * appelle la méthode choixFichier
			 * @see choixFichier 
			 */
			public void actionPerformed(ActionEvent e) {
				choixFichier();
			}
		});
		btnChoisirFichier.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnChoisirFichier.setBounds(330, 286, 135, 28);
		internalFrame.getContentPane().add(btnChoisirFichier);
	}

	/**
	 * Ouvre une fenetre de dialogue pour le choix
	 * d'un fichier avec l'extension .fasta
	 * @return une chaîne de caractere correspondant à la sequence query
	 */
	private void choixFichier() {
		String line;
		BufferedReader br;
		File f;
		String filePath;
		//instancie sequenceFichier lorsque l'utilisateur choisi un fichier
		sequenceFichier= new StringBuilder();
		JFileChooser select = new JFileChooser();
		// Filtre les fichiers pour n'afficher que les fichiers avec l'extension .fasta
		select.addChoosableFileFilter(new FiltreExtensionFichier());
		select.setAcceptAllFileFilterUsed(false);
		//affiche "ouvrir" dans la fenetre de dialogue
		int res = select.showDialog(internalFrame, "Ouvrir");
		if (res==JFileChooser.APPROVE_OPTION) {
			f = select.getSelectedFile();
			filePath= f.getPath();
			labelFichierChoisi.setText(f.getName());
			try {
				br= new BufferedReader(new FileReader(filePath));
				while ( (line= br.readLine()) !=null) {
					sequenceFichier.append(line);
					sequenceFichier.append("\n");
				}
				br.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(internalFrame,"Erreur survenu lors de l'ouverture du fichier","Alert",JOptionPane.WARNING_MESSAGE);     
			}
		}
	}

	/**
	 * Affiche un cadre avec l'instruction de l'étape 2
	 */
	private void createLabelStepTwo() {
		labelStepTwo = new JLabel("STEP 2 - Entrez vos paramètres");
		labelStepTwo.setForeground(new Color(255, 0, 0));
		labelStepTwo.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelStepTwo.setBounds(6, 330, 241, 31);
		internalFrame.getContentPane().add(labelStepTwo);
	}

	/**
	 * Affiche un cadre avec l'instruction d'entrer la pénalité de gap
	 */
	private void createLabelGapPenalty() {
		labelGapPenalty = new JLabel("Gap Penalty : ");
		labelGapPenalty.setFont(new Font("SansSerif", Font.PLAIN, 14));
		labelGapPenalty.setBounds(6, 373, 102, 31);
		internalFrame.getContentPane().add(labelGapPenalty);
	}

	/**
	 * Affiche un champs pour y entrer la valeur de pénalité de gap
	 */
	private void saisiGapPenalty() {
		gapPenalty = new JTextField();
		gapPenalty.setBounds(120, 375, 48, 28);
		internalFrame.getContentPane().add(gapPenalty);
		gapPenalty.setColumns(10);
	}

	/**
	 * Affiche un bouton qui au clic lance l'alignement multiple
	 */
	private void createBtnRunMultipleAlignment() {
		btnRunMultipleAlignment = new JButton("Lancez Alignement Multiple");
		btnRunMultipleAlignment.addActionListener(new ActionListener() {
			/**
			 * Au clic sur le bouton lance l'alignement multiple des séquences entrées 
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					String seq = null;
					//si aucun fichier choisi prend sequence cadre entrer sequence
					if (sequenceFichier==null)
						seq= entrezSequence.getText();
					//si cadre entrer sequence null prend fichier choisi
					else if (entrezSequence.getText()==null) 
						seq= sequenceFichier.toString();
					//si les deux sont pleins prend fichier choisi
					else if ((sequenceFichier!=null)&&(entrezSequence.getText()!=null))
						seq= sequenceFichier.toString();
					//si les deux sont null lance une exception
					else
						throw new IllegalArgumentException();
					//verifie au moins 2 séquences au format fasta
					int nbSeq= Sequence.nbSequencesFormatFasta(seq);
					if (nbSeq>=2) {
						Sequence query = new Sequence(nbSeq, seq, "SequenceQuery",
								(String) choixTypeSequence.getSelectedItem());
						query.setNomAllSequences();
						query.setAllSequences();
						query.AfficheAllSequences();
					}	
					else
						throw new IllegalArgumentException();
				}
				catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(entrezSequence,"Erreur! Entrez au moins deux séquences au format fasta!","Alert",JOptionPane.WARNING_MESSAGE);     
				}
			}
		});
		btnRunMultipleAlignment.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnRunMultipleAlignment.setBounds(7, 436, 218, 36);
		internalFrame.getContentPane().add(btnRunMultipleAlignment);
	}
}