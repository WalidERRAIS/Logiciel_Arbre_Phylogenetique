package outils;

import java.util.regex.Matcher;
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


	/**
	 * retourne le nombre de séquence à aligner  au format fasta 
	 * @param s correspond à la chaîne de caractère entrée par l'utilisateur
	 * @return nbSequences correspond au nombre de séquence contenu dans la chaîne entrée en paramètre
	 */
	public static int nbSequencesFormatFasta(String s) {
		int nbSequence=0;
		Pattern fastaPattern= Pattern.compile("^>.+\\n[ABCDEFGHIKLMNPQRSTVWXYZ]+\\n?", Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
		Matcher fastaMatch = fastaPattern.matcher(s);
		while (fastaMatch.find()) {
			nbSequence++;
		}
		return nbSequence;
	}

	/**
	 * Si au moins deux sequences au format fasta sont entrées créer une sequence query
	 * contenant toutes les séquences à aligner et fait appel au second constructeur pour créer 
	 * un tableau de n sequences où chaque indice correspond à une sequence de la query
	 * 
	 * @param nbSeq correspond au nombre de séquence au format fasta
	 * @param s correspond à la séquence
	 * @param n correspond au nom de la séquence
	 * @param t correspond au type de la séquence (protéine ou adn)
	 */
	public Sequence(int nbSeq, String s, String n, String t) {
		this.sequence= s;
		this.nomSequence= n;
		this.typeSequence= t;
		//Instancie un taleau de sequences avec autant de séquences : Sequence_1, Sequence_i...
		//et de meme type que la sequence query
		this.sequences= new Sequence[nbSeq];
		for (int i=0; i<this.sequences.length; i++) {
			this.sequences[i]= new Sequence("Sequence_"+(i+1), t);
		}
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
	 * modifie le nom des séquences 
	 */
	public void setNomAllSequences() {
		Pattern nomSeqPattern= Pattern.compile("^>.+\\n?", Pattern.MULTILINE);
		Matcher nomSeqMatch = nomSeqPattern.matcher(this.sequence);
		for (int i=0; i<this.sequences.length; i++) {
			if (nomSeqMatch.find()) {
				//supprime les ">" des noms de séquence
				this.sequences[i].setNomSequence(nomSeqMatch.group().replace(">", ""));
			}
		}
	}

	/**
	 * modifie les séquences
	 */
	public void setAllSequences() {
		Pattern seqPattern= Pattern.compile("^[ABCDEFGHIKLMNPQRSTVWXYZ]+", Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
		Matcher seqMatch = seqPattern.matcher(this.sequence);
		for (int i=0; i<this.sequences.length; i++) {
			if (seqMatch.find()) {
				this.sequences[i].setSequence(seqMatch.group());
			}
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