package outils;

import java.util.regex.Pattern;

/**
 * La classe Sequence représente une séquence query contenant toutes les séquences à aligner 
 * @author walid
 */
public class Sequence {
	protected String sequence;
	protected String nomSequence;
	protected int tailleSequence;
	protected String typeSequence;
	protected Sequence[] sequences;
//	private static final int Multiline = 0;
	
	private static Pattern fastaPattern= Pattern.compile("^>.+\\n[ATGC]+", Pattern.DOTALL);
	
	
	/**
	 * retourne vrai si la séquence s match avec l'expression reguliere 
	 * correspondant aux fichiers fasta
	 * @param s correspond à la séquence à tester
	 * @return vrai ou faux
	 */
	public static boolean verifieFormatFasta(String s) {
		return fastaPattern.matcher(s).matches();
	}
	
	/**
	 * Si au moins deux sequences sont entrées créer une sequence query contenant toutes les séquences
	 * à aligner et fait appel au second constructeur pour créer 
	 * un tableau de n sequences où chaque indice correspond à une sequence de la query
	 * 
	 * @param s correspond à la séquence
	 * @param n correspond au nom de la séquence
	 * @param t correspond au type de la séquence (protéine ou adn)
	 */
	public Sequence(String s, String n, String t) {
		int nbSeq= nbSequences(s);
//		if (nbSeq>=2) {
			this.sequence= s;
			this.nomSequence= n;
			this.typeSequence= t;
			//Instancie un taleau de sequences avec autant de séquences : Sequence_1, Sequence_i...
			//et de meme type que la sequence query
			this.sequences= new Sequence[nbSeq];
			for (int i=0; i<this.sequences.length; i++) {
				this.sequences[i]= new Sequence("Sequence_"+(i+1), t);
			}
//		}
//		else
//			throw new IllegalArgumentException();
	}

	/**
	 * Créer une sequence 
	 * 
	 * @param n correspond au nom de la séquence à l'indice i
	 * @param t correspond au meme type que la séquence query
	 */
	public Sequence(String n, String t) {
		this.nomSequence= n;
		this.typeSequence= t;
	}

	/**
	 * retourne le nom de la sequence
	 * @return nomSequence correspond au nom de la sequence
	 */
	public String getNomSequence() {
		return this.nomSequence;
	}

	/**
	 * modifie le nom de la sequence
	 * @param n correspond au nouveau nom
	 */
	public void setNomSequence(String n) {
		this.nomSequence = n;
	}

	/**
	 * retourne la sequence
	 * @return sequence correspond à la séquence
	 */
	public String getSequence() {
		return this.sequence;
	}

	/**
	 * modifie la sequence
	 * @param s correspond à la nouvelle séquence
	 */
	public void setSequence(String s) {
		this.sequence= s;
	}

	/**
	 * retourne la taille de la séquence
	 * @return tailleSequence correspond à la taille de la séquence
	 */
	public int getTailleSequence() {
		return this.tailleSequence;
	}

	/**
	 * retourne le type de la séquence
	 * @return typeSequence correspond au type de la séquence 
	 */
	public String getTypeSequence() {
		return this.typeSequence;
	}

	/**
	 * retourne le tableau contenant toutes les séquences
	 * @return sequences correspond au tableau de Sequence
	 */
	public Sequence[] getAllSequences() {
		return this.sequences;
	}

	/**
	 * retourne le nombre de séquence à aligner si la chaîne entrée en paramètre est au format fasta
	 * @param s correspond à la chaîne de caractère entrée par l'utilisateur
	 * @return nbSequences correspond au nombre de séquence contenu dans la chaîne entrée en paramètre
	 */
	public static int nbSequences(String s) {
		int nbSequences=0;
		if (s.charAt(0)=='>') {
			for (int i=0; i<s.length(); i++) {
				if (s.charAt(i)=='>')
					nbSequences++;
			}
		}
		else 
			throw new IllegalArgumentException();
		return nbSequences;
	}

	/**
	 * modifie le nom des séquences 
	 */
	public void setNomAllSequences() {
		int debut= 0; //position du premier ">"
		int fin= this.sequence.indexOf("\n"); //fin du nom au saut de ligne 
		for (int i=0; i<this.sequences.length; i++) {
			this.sequences[i].setNomSequence(this.sequence.substring(debut, fin));
			//supprime les ">" des noms de séquence
			this.sequences[i].setNomSequence(this.sequences[i].getNomSequence().replaceAll(">", ""));
			debut= this.sequence.indexOf(">", fin);
			fin= this.sequence.indexOf("\n", debut);
		}
	}
	
	/**
	 * modifie les séquences
	 */
	public void setAllSequences() {
		int debut= this.sequence.indexOf("\n")+1; //debut sequence après saut de ligne
		int fin= this.sequence.indexOf("\n", debut); //fin de la séquence au saut de ligne
		for (int i=0; i<this.sequences.length; i++) {
			this.sequences[i].setSequence(this.sequence.substring(debut, fin));
			this.sequences[i].setSequence(this.sequences[i].getSequence().trim()); //enlève les blancs de la séquence
			debut= this.sequence.indexOf("\n", fin+1)+1;
			fin= this.sequence.indexOf("\n", debut);
			if (fin==-1)
				fin = this.sequence.length();
		}
	}
	
	public void affiche() {
		for (int i=0; i<this.sequences.length; i++) {
			for (int j=0; j<this.sequences[i].getSequence().length(); j++) {
				System.out.println(j+" : "+this.sequences[i].getSequence().charAt(j));
			}
			System.out.println();
		}
	}

	/**
	 * Affiche la séquence et son type (Adn ou Proteine)
	 */
	@Override
	public String toString() {
		return "nom séquence : \n"+this.nomSequence+ "\n\nsequences : \n" +
				this.sequence + "\n\ntype : \n" + this.typeSequence;
	}

	/**
	 * Affiche la séquence et son type (Adn ou Proteine)
	 */
	public void AfficheSequence() {
		System.out.println(this.toString());
	}

	/**
	 * Affiche le nom , la séquence et le type de chaque sequence contenu dans le tableau de Sequence
	 */
	public void AfficheAllSequences() {
		for (int i=0; i<this.sequences.length; i++) {
			System.out.println("nom séquence : \n"+this.sequences[i].getNomSequence()+"\n\nsequence : \n"+
					this.sequences[i].getSequence()+"\n\ntype séquence : \n"
					+this.sequences[i].getTypeSequence()+"\n");
		}
	}
}
