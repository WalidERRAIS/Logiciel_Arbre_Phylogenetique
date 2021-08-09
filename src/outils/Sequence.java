package outils;


/**
 * La classe Sequence représente une séquence s avec un nom, 
 * une taille, un type (protéine ou adn)
 * @author walid
 */
public class Sequence {
	protected String sequence;
	protected String nomSequence;
	protected int tailleSequence;
	protected String typeSequence;
	protected Sequence[] sequences;
	
	/**
	 * Créer une sequence
	 * @param s correspond à la séquence
	 * @param n correspond au nom de la séquence
	 * @param t correspond au type de la séquence (protéine ou adn)
	 */
	public Sequence(String s, String n, String t) {
		int nbSeq= nbSequences(s);
		if (nbSeq>=2) {
			this.sequence= s;
			this.nomSequence= n;
			this.typeSequence= t;
			//Instancie taleau de sequences avec autant de séquences : Sequence_i
			this.sequences= new Sequence[nbSeq];
			for (int i=0; i<this.sequences.length; i++) {
				this.sequences[i]= new Sequence("Sequence_"+(i+1), t);
			}
		}
		else
			throw new IllegalArgumentException();
	}
	public Sequence(String n, String t) {
		this.nomSequence= n;
		this.typeSequence= t;
	}

	public String getNomSequence() {
		return this.nomSequence;
	}
	
	public void setNomSequence(String n) {
		this.nomSequence = n;
	}

	public String getSequence() {
		return this.sequence;
	}

	public int getTailleSequence() {
		return this.tailleSequence;
	}

	public String getTypeSequence() {
		return this.typeSequence;
	}
	
	public Sequence[] getAllSequences() {
		return this.sequences;
	}
	
	// renvoie nb de séquences dans l'entrez de l'user
	public static int nbSequences(String s) {
		int nbSequences=0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i)=='>')
				nbSequences++;
		}
		return nbSequences;
	}
	
	@Override
	public String toString() {
		return "sequences = \n\n" + this.sequence + " \n\ntype = " + this.typeSequence;
	}
	
	public void AfficheAllSequences() {
		for (int i=0; i<this.sequences.length; i++) {
			System.out.println("nom séquence : "+this.sequences[i].getNomSequence()+
					" ; type séquence : "+this.sequences[i].getTypeSequence());
		}
	}
	
//	public static void main (String[] args) {
//	}
}
