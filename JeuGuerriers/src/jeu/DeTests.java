package jeu;

/*
 * @author Lecharlier Lo�c
 * 
 * Classe qui simule un d� dont les r�sultats des lanc�s seront 1,1,2,2,4,6,1,1,2,2,4,6,1,1,2,2,4,6,...
 */
public class DeTests implements De {
	int lanceSuivant ;
	int[] lances = {1,1,2,2,4,6} ;
	public DeTests() {
		lanceSuivant = 0 ;
	}
	@Override
	public int lancer() {
		// TODO Auto-generated method stub
		int lance = lances[lanceSuivant] ;
		lanceSuivant = (lanceSuivant+1)%lances.length ;
		return lance;
	}

}
