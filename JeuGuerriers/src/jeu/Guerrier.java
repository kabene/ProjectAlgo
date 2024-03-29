package jeu;

/**
 * @author Abene Karim, Sivixay Celestin
 * 
 * Classe repr�sentant un guerrier du jeu des guerriers
 *
 */

public class Guerrier {
	private int nombreDeTours ; // nombre de tour d�j� effectu�s par le guerrier
	private int ptsVie; // nombre de point de vie du guerrier
	private int numJoueur; //num�ro du joueur � qui appartient le guerrier
	private int numGuerrier; // num�ro du guerrier
	private Niveau niveau; // niveau du guerrier (jeu amélioré
	private int ptsVieMax;// )
	
	/**
	 * Cr�e un guerrier num�ro numero du joueur dont le num�ro est numJoueur et qui a ptsVie point(s) de vie
	 * @param numJoueur : num�ro du joueur auquel appartient le guerrier
	 * @param ptsVie : nombre de points de vie de d�part du guerrier
	 * @param numGuerrier : num�ro du guerrier
	 */
	public Guerrier(int numJoueur, int ptsVie, int numGuerrier) {
		this.numJoueur = numJoueur;
		this.numGuerrier = numGuerrier;
		this.nombreDeTours = 0 ;
		this.ptsVie = ptsVie ;
		this.niveau = new Niveau();
		ptsVieMax=ptsVie;
	}
	
	/**
	 * renvoie le num�ro du guerrier
	 * @return le num�ro du guerrier
	 */
	public int getNumGuerrier() {
		return numGuerrier;
	}

	/**
	 * renvoie le num�ro du joueur auquel appartient le guerrier
	 * @return le num�ro du joueur auquel appartient le guerrier
	 */
	public int getNumJoueur() {
		return numJoueur;
	}
	
	/**
	 * renvoie le nombre de tour d�j� effectu� par le guerrier
	 * @return le nombre de tour d�j� effectu� par le guerrier
	 */
	public int getNombreDeTours() {
		return this.nombreDeTours ;
	}

	/**
	 * renvoie le niveau du guerrier
	 * @return le niveau du guerrier
	 */
	public Niveau getNiveau(){
		return niveau;
	}
	/**
	 * renvoie le nombre de points de vie du guerrier
	 * @return le nombre de points de vie du guerrier
	 */
	public int getPtsVie() {
		return ptsVie ;
	}

	public int getPtsVieMax(){ return ptsVieMax; }

	/**
	 * Lorsqu'un guerrier augmente de niveau, ses points de vie max augmentent de 10% et il récupère tous es points de vie
	 */
	public void levelUp(){
		ptsVieMax=(int)(ptsVieMax*1.1);
		ptsVie=ptsVieMax;
	}
	
	/**
	 * Mets le nombre de points de vie du guerrier � ptsVie ;
	 * @param ptsVie : nombre de points de vie de d�part du guerrier
	 */
	public void setPtsVie(int ptsVie) {
		this.ptsVie = ptsVie;
	}
	
	/**
	 * augmente de 1 le nombre de tour
	 */
	
	public void ajouterUnTour() {
		this.nombreDeTours++ ;
	}

}
