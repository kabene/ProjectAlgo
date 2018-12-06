package jeu;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Lecharlier Loic
 * 
 *         Classe d'ex�cution du jeu
 *
 */

public class JeuGuerrier {

	private static Scanner scanner = new Scanner(System.in);
	private static GrilleJeu grille; // gestion des donn�es du jeu
	private static PlateauDeJeu plateau; // panneau graphique du jeu
	private static De de = new DeJeu();

	public static void main(String[] args) {

		System.out.println("Bienvenue au jeu des guerriers !");

		// configuration du jeu
		// A ne pas modifier

		System.out.print("Entrez le nombre de cases : ");
		int nbrCases = UtilitairesJeux.lireEntierPositif("Le nombre de cases doit �tre pair");
		System.out.print("Entrez le nombre de tours : ");
		int nbrTours = UtilitairesJeux.lireEntierPositif("Le nombre de tours est de minimum 1");
		System.out.print("Entrez le nombre de joueurs : ");
		int nbreJoueurs = UtilitairesJeux.lireEntierPositif("Le nombre de joueurs est de minimum 2");
		System.out.print("Entrez le nombre de guerriers par joueurs : ");
		int nbreJetons = UtilitairesJeux.lireEntierPositif("Le nombre de guerriers est de minimum 1");
		System.out.print("Entrez le nombre de points de vie des guerriers : ");
		int ptsVie = UtilitairesJeux.lireEntierPositif("Le nombre de points de vie est de minimum 1");

		String[] nomJoueurs = new String[nbreJoueurs];
		System.out.println("Entrez les noms des joueurs selon l'ordre du jeu : ");
		for (int numJoueur = 1; numJoueur <= nbreJoueurs; numJoueur++) {
			System.out.print("Entrez le nom du joueur " + numJoueur + " : ");
			nomJoueurs[numJoueur - 1] = UtilitairesJeux.lireStringNonVide("Le nom doit contenir au moins une lettre");
		}
		grille = new GrilleJeu(nbreJoueurs, nbrCases, nbreJetons, nbrTours, ptsVie, nomJoueurs);
		plateau = new PlateauDeJeu(nbrCases,nbreJoueurs, nbreJetons, grille);
		int tourMax=0;
		while(tourMax<nbrTours) {
			for (int i = 0; i < nbreJoueurs; i++) {
				int nbr = de.lancer();
				plateau.afficherResultatDe(nbr);
				int caseChoisie = plateau.jouer();
				if(nbr+caseChoisie>nbrCases){
				    nbr=nbr+caseChoisie-nbrCases;
				    if(grille.donnerPion(nbr)==null) {
                        grille.bougerPion(caseChoisie, nbr);
                        grille.donnerPion(nbr).ajouterUnTour();
                    }else{
				        int resultat = seBattre(caseChoisie,nbr);
				        if(resultat==2) {
                            grille.donnerPion(nbr).ajouterUnTour();
                            if(grille.donnerPion(nbr).getNombreDeTours()>tourMax)
                                tourMax=grille.donnerPion(nbr).getNombreDeTours();
                        }else if(resultat==4) {
                            while(grille.donnerPion(nbr)!=null)
                                nbr++;
                            grille.bougerPion(caseChoisie,nbr);
                            grille.donnerPion(nbr).ajouterUnTour();
                            if(grille.donnerPion(nbr).getNombreDeTours()>tourMax)
                                tourMax=grille.donnerPion(nbr).getNombreDeTours();
                        }
                    }
                    plateau.actualiser(grille);
                }
			}
		}

	}

	private static int seBattre(int caseAtt, int caseDef){
	    Guerrier attaquant = grille.donnerPion(caseAtt);
	    Guerrier defenseur = grille.donnerPion(caseDef);
	    int valAtt = de.lancer();
	    defenseur.setPtsVie(defenseur.getPtsVie()-valAtt);
	    int valDef = de.lancer();
	    attaquant.setPtsVie(attaquant.getPtsVie()-valDef);
	    if(attaquant.getPtsVie()<=0 && defenseur.getPtsVie()<=0) {
            grille.supprimerPion(caseAtt);
            grille.supprimerPion(caseDef);
            return 1;
        }else if(defenseur.getPtsVie()<=0) {
            grille.bougerPion(caseAtt, caseDef);
            return 2;
        }else if(attaquant.getPtsVie()<=0){
	        grille.supprimerPion(caseAtt);
	        return 3;
        }else if(valAtt>valDef){
	        return 4;
        }
        return 5;
    }



}
