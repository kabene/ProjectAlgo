package jeu;

import java.util.Scanner;

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
        System.out.print("tous les n tours se déroule un match à mort choissisez ce nombre: ");
        int tourMatchAmort= scanner.nextInt();


		String[] nomJoueurs = new String[nbreJoueurs];
		System.out.println("Entrez les noms des joueurs selon l'ordre du jeu : ");
		for (int numJoueur = 1; numJoueur <= nbreJoueurs; numJoueur++) {
			System.out.print("Entrez le nom du joueur " + numJoueur + " : ");
			nomJoueurs[numJoueur - 1] = UtilitairesJeux.lireStringNonVide("Le nom doit contenir au moins une lettre");
		}

		nbrCases=nbrCorrecte(nbrCases,nbreJoueurs,nbreJetons);

		grille = new GrilleJeu(nbreJoueurs, nbrCases, nbreJetons, nbrTours, ptsVie, nomJoueurs);
		plateau = new PlateauDeJeu(nbrCases,nbreJoueurs, nbreJetons, grille);

		int tourMax=0;
		Joueur Gagnant=null;

		while(tourMax<nbrTours) {
			for (int i = 0; i < nbreJoueurs; i++) {
                if (grille.donnerJoueur(i + 1).nombreDeGuerriersEnVie() > 0) {
                    plateau.afficherJoueur(grille.donnerJoueur(i + 1));
                    int nbr = de.lancer();
                    plateau.afficherResultatDe(nbr);
                    int caseChoisie = plateau.jouer();


                    if (grille.donnerPion(caseChoisie) != null && grille.estUnPionDuJoueur(caseChoisie, grille.donnerJoueur(i + 1))) {
                        // déplacement si passant par la case départ
                        if (nbr + caseChoisie > nbrCases) {
                            nbr = nbr + caseChoisie - nbrCases;
                            // déplacement si pas de conflicts, dépendant de la méthode seBattre
                            if (grille.donnerPion(nbr) == null) {
                                grille.bougerPion(caseChoisie, nbr);
                                grille.donnerPion(nbr).ajouterUnTour();
                                if (grille.donnerPion(nbr).getNombreDeTours() > tourMax)
                                    tourMax = grille.donnerPion(nbr).getNombreDeTours();

                            } else {
                                // déplacements si conflicts => combats
                                int resultat = seBattre(caseChoisie, nbr);
                                if (resultat == 2) {
                                    grille.donnerPion(nbr).ajouterUnTour();
                                    if (grille.donnerPion(nbr).getNombreDeTours() > tourMax)
                                        tourMax = grille.donnerPion(nbr).getNombreDeTours();

                                } else if (resultat == 4) {

                                    while (grille.donnerPion(nbr) != null)
                                        nbr++;
                                    grille.bougerPion(caseChoisie, nbr);
                                    grille.donnerPion(nbr).ajouterUnTour();
                                    if (grille.donnerPion(nbr).getNombreDeTours() > tourMax)
                                        tourMax = grille.donnerPion(nbr).getNombreDeTours();
                                }
                            }
                            //actualisation interface graphique
                            plateau.actualiser(grille);
                        } else {
                             //déplacement ne passant pas par la case départ
                            nbr = nbr + caseChoisie;
                            if (grille.donnerPion(nbr) == null) {
                                grille.bougerPion(caseChoisie, nbr);
                            } else {
                                // cas conflicts, se battre
                                int resultat = seBattre(caseChoisie, nbr);
                                if (resultat == 4) {
                                    while (grille.donnerPion(nbr) != null) {
                                        if (nbr != nbrCases) {
                                            nbr++;
                                        } else {
                                            nbr = 1;
                                            grille.donnerPion(caseChoisie).ajouterUnTour();
                                        }
                                    }
                                    grille.bougerPion(caseChoisie, nbr);
                                }
                            }
                            // actualisation de l'interface graphique
                            plateau.actualiser(grille);
                        }
                    }
                }
                plateau.afficherGuerriers(grille.classerGuerriers());
            }
		}
		//plateau.afficherGagnant();

	}
    /**
     * @param nbrcase:nbr de cases choisies
     * @param nbrjoueur:nbr de joueur choisi
     * @param nbrguerrier:nbr de guerrier choisis
     * @return nbr de case corriger en fonction du nombre de joueur et de guerrier
     */
    private static int nbrCorrecte( int nbrcase,int nbrjoueur,int nbrguerrier){
        if(nbrguerrier * nbrjoueur > nbrcase){
            while (nbrcase< nbrjoueur*nbrguerrier) {
                System.out.print("nbr cases trop petit par rapport au nombre de guerrier par joueur veuillez réantrez un nombre de case supérieur à " + nbrjoueur * nbrguerrier+":");
                nbrcase = scanner.nextInt();
            }
            return nbrcase;
        }
        return nbrcase;
    }


    /**
     * @param caseAtt:case de l'attaquant
     * @param caseDef: case de l'attaqué
     * @return INT  entre 1 et 5  pour les différents cas de combats
     */
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
            plateau.afficherInformation("Les deux guerriers sont morts !");
            return 1;
        }else if(defenseur.getPtsVie()<=0) {
            grille.bougerPion(caseAtt, caseDef);
            plateau.afficherInformation("Le defenseur est mort!");
            return 2;
        }else if(attaquant.getPtsVie()<=0){
	        grille.supprimerPion(caseAtt);
            plateau.afficherInformation("L'attaquant est mort!");
	        return 3;
        }else if(valAtt>valDef){
	        plateau.afficherInformation("L'attaquant a infligé "+valAtt+" pts de degat !\n"+"Le defensseur riposte de "+valDef + " !");
	        plateau.afficherInformation2("L'attaquant a réussi son attaque !");
	        return 4;
        }
        plateau.afficherInformation("L'attaquant a infligé "+valAtt+" pts de degat !\n" +"le defensseur riposte de "+valDef + " !");
        plateau.afficherInformation2("L'attaquant a raté son attaque !");
        return 5;
    }



}
