package jeu;

/**
 * @author Lecharlier Lo�c
 *
 * Classe repr�sentant un d� � 6 face non pip�
 * 
 */
public class DeJeu implements De{
	
	public DeJeu() {
		super() ;
	}
		
	/**
	 * Simule le lancer du d�
	 * @return le r�sutat (entre 1 et 6) du lanc� du d�
	 */
	public int lancer() {
		return unEntierAuHasardEntre(1,6) ;
	}
	
	private int unEntierAuHasardEntre(int valeurMinimale, int valeurMaximale) {
		double nombreReel;
		int resultat;

		nombreReel = Math.random();
		resultat = (int) (nombreReel * (valeurMaximale - valeurMinimale + 1)) + valeurMinimale;
		return resultat;
	}
}
