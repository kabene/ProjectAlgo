package jeu;

/**
 * @author Lecharlier Lo�c
 * 
 * Classe repr�sentant l'�tat du jeu des guerriers
 * 
 */

public class GrilleJeu {

	private Joueur[] tableJoueurs; //tableau des joueurs
	private Guerrier[] cases; //cases du jeu
	private int nombreDeTours; //nombre de tours � faire pour gagner
	
	/**
	 * initialise et construit la table des joueurs
	 * initialise et construit la table repr�sentant les cases du jeu
	 * initialise le nombre de tour � faire pour gagner
	 * 
	 * @param nombreJoueurs : le nombre de joueurs participants
	 * @param nombreDeCases : le nombre de cases du plateau de jeu
	 * @param nombreDeGuerriersParJoueur : le nombre de guerriers par joueurs
	 * @param nombreDeTours : le nombre de tours � effectuer pour gagner
	 * @param ptsVieDeDepart : le nombre de points de vie de d�part des guerriers
	 * @param nomDesJoueurs : tableau contenant le nom des joueurs
	 * 
	 */
	
	public GrilleJeu(int nombreJoueurs, int nombreDeCases, int nombreDeGuerriersParJoueur, int nombreDeTours,int ptsVieDeDepart,String[] nomDesJoueurs) {
		//TODO
	} 	

	/**
	 * renvoie le joueur dont le numero est passe en parametre
	 * 
	 * @param numJoueur : le numero d'ordre du joueur dans le jeu
	 * @return le joueur
	 */
	public Joueur donnerJoueur(int numJoueur) {
		// TODO
		return null ;
	}

	/**
	 * Renvoie le guerrier se trouvant � la case dont le num�ro est numCase
	 * @param numCase : le num�ro de la case
	 * @return le guerrier se trouvant � la case dont le num�ro est numCase s'il y en a un
	 *         null sinon
	 */
	public Guerrier donnerPion(int numCase) {
		return null ;
	}

	
	/**
	 * Bouge le guerrier se trouvant � la case num�ro caseDepart et le met � la case num�ro caseArriv�e
	 * 
	 * @param caseDepart : num�ro de la case o� se trouve le guerrier � bouger
	 * @param caseArrivee : num�ro de la case o� il faut mettre le guerrier
	 *
	 */
	
	public void bougerPion(int caseDepart, int caseArrivee) {
		//TODO
	}
	
	/**
	 * D�termine si le guerrier se trouvant sur la case num�ro numCase appartient au joueur joueur
	 * 
	 * @param numCase : num�ro de la case
	 * @param joueur : le joueur
	 * @return true  si le guerrier se trouvant � la case num�ro numCase appartient au joueur Joueur
	 *         false sinon
	 */
	public boolean estUnPionDuJoueur(int numCase, Joueur joueur) {
		//TODO
		return false ;
	}
	
	/**
	 * supprime le guerrier se trouvant � la case num�ro numCase (vide la case)
	 * @param numCase : num�ro de la case
	 */
	
	public void supprimerPion(int numCase) {
		//TODO
	}
	
	/**
	 * Classe les guerriers encore un jeu d'abord selon le nombre tour d�j� effectu� (du plus grand au plus petit) et ensuite (si dans le m�me tour) par num�ro de case occup�e (du plus grand au plus petit)  
	 * @return un tableau de pion repr�sentant le classement des pions selon les crit�res ci-dessus
	 */
	public Guerrier[] classerGuerriers() {
		//TODO
		return null ;
	}

}
