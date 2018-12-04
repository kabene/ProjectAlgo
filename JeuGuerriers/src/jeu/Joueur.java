package jeu;


/**
 * @author Lecharlier Lo�c
 *
 * Classe repr�sentant un joueur du jeu des guerriers
 */

public class Joueur {
	
	private Guerrier[] guerriers ; // tableau contenant les guerriers du joueur
	private int numJoueur ; // num�ro du joueur
	private String nom; //nom du joueur
	
	/**
	 * Cr�e un joueur du jeu des guerriers 
	 * @param nom : le nom du joueur
	 * @param nbGuerriers : le nombre de guerriers du joueur
	 * @param ptsVie : le nombre de points de vie de d�part des guerriers du joueur
	 * @param numJoueur : num�ro du joueur
	 */
	public Joueur(String nom, int nbGuerriers, int ptsVie, int numJoueur) {
		this.nom=nom;
		this.guerriers=new Guerrier[nbGuerriers];
		for(int i=0;i<guerriers.length;i++){
		    guerriers[i]=new Guerrier(numJoueur,ptsVie,i+1);
		    guerriers[i].setPtsVie(ptsVie);
        }
		this.numJoueur=numJoueur;
	}
	
	/**
	 * renvoie le nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * renvoie le num�ro du joueur
	 * @return le num�ro du joueur
	 */
	public int getNumJoueur() {
		return numJoueur ;
	}
	
	
	/**
	 * Renvoie le guerrier num�ro numGuerrier du joueur
	 * @param numGuerrier : le num�ro du guerrier
	 * @return le guerrier num�ro numGuerrier du joueur
	 */
	public Guerrier getGuerrier(int numGuerrier) {
		return guerriers[numGuerrier-1];
	}
	
	/** 
	 * D�termine le nombre de guerrier encore en vie du joueur
	 * @return le nombre de guerrier encore en vie du joueur
	 */
	public int nombreDeGuerriersEnVie() {
	    int count=0;
	    for(int i=0;i<guerriers.length;i++){
	        if(guerriers[i].getPtsVie()>0){
	            count++;
            }
        }
		return count;
	}

}
