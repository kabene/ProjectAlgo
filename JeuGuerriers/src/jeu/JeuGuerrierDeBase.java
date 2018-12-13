package jeu;
import java.util.Scanner;
/**
 * @author Abene Karim, Sivixay Celestin
 *
 *         Classe d'ex�cution du jeu
 *
 */
public class JeuGuerrierDeBase {

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
        nbrCases=nbrCorrecte(nbrCases,nbreJoueurs,nbreJetons);
        if(nbrCases%2!=0){
            nbrCases++;// Si un nombre de cases impair est entré, le tableau est quand même créé, mais avec une case en plus
            System.out.println("Le nombre de case étant impair, le tableau a été défini avec une case de plus");
        }

        int tourMax=0;
        int nbrJoueursEnVie=nbreJoueurs;
        int tourActuel = 1;



        grille = new GrilleJeu(nbreJoueurs, nbrCases, nbreJetons, nbrTours, ptsVie, nomJoueurs);
        plateau = new PlateauDeJeu(nbrCases,nbreJoueurs, nbreJetons, grille);

        plateau.afficherGuerriers(grille.classerGuerriers()); //Afficher le classement au début du jeu
        while(tourMax<nbrTours && nbrJoueursEnVie>1) {
            plateau.afficherInformation("Tour " + tourActuel);
            for (int i = 0; i < nbreJoueurs; i++) {
                if (tourMax < nbrTours && nbrJoueursEnVie > 1) { // Evite que les joueurs finissent le tour si un joueur a atteint les prérequis de victoire
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

                                            while (grille.donnerPion(nbr) != null && nbr!=caseChoisie)
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
                                            while (grille.donnerPion(nbr) != null && nbr!=caseChoisie) {
                                                if (nbr != nbrCases) {
                                                    nbr++;
                                                } else {
                                                    nbr = 1;
                                                    grille.donnerPion(caseChoisie).ajouterUnTour();
                                                    if (grille.donnerPion(caseChoisie).getNombreDeTours() > tourMax)
                                                        tourMax = grille.donnerPion(caseChoisie).getNombreDeTours();
                                                }
                                            }
                                            grille.bougerPion(caseChoisie, nbr);
                                        }

                                }
                                // actualisation de l'interface graphique
                                plateau.actualiser(grille);
                            }
                        }
                        if(tourMax==nbrTours) {
                            plateau.afficherInformation2("Le joueur gagnant est le joueur numero " + (i+1));
                            plateau.afficherGagnant(grille.donnerJoueur(i + 1));
                        } else {
                            for (int j = 0; j < nbreJoueurs; j++) {
                                Joueur joueur = grille.donnerJoueur(j + 1);
                                if (joueur.estEnVie())
                                    if (joueur.nombreDeGuerriersEnVie() == 0) {
                                        joueur.plusEnVie();
                                        nbrJoueursEnVie--;
                                    }
                            }
                            plateau.afficherGuerriers(grille.classerGuerriers());
                            if (nbrJoueursEnVie == 1) {
                                Joueur joueurGagnant = null;
                                for (int k = 0; k < nbreJoueurs; k++) {
                                    if (grille.donnerJoueur(k + 1).nombreDeGuerriersEnVie() > 0) {
                                        joueurGagnant = grille.donnerJoueur(k + 1);
                                        plateau.afficherInformation2("Le joueur gagnant est le joueur numero " + (k + 1));
                                    }
                                }

                                plateau.afficherGagnant(joueurGagnant); //Affiche le joueur gagnant
                            } else if (nbrJoueursEnVie == 0) {
                                plateau.afficherGagnant(null);
                                plateau.afficherInformation("Tous les guerriers sont morts, il n'y a aucun gagnant !"); // sSi il ne reste plus que 2guerriers et qu'ils s'entretuent, il n'y a aucun gagnant
                            }
                        }

                    }
                }
            }
            tourActuel++;
        }
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
                System.out.print("nbr cases trop petit par rapport au nombre de guerrier par joueur veuillez réentrez un nombre de case supérieur à " + nbrjoueur * nbrguerrier+":");
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
            plateau.afficherInformation2("Les deux guerriers sont morts !");
            return 1;
        }else if(defenseur.getPtsVie()<=0) {
            grille.bougerPion(caseAtt, caseDef);
            plateau.afficherInformation2("Le defenseur est mort!");
            return 2;
        }else if(attaquant.getPtsVie()<=0){
            grille.supprimerPion(caseAtt);
            plateau.afficherInformation2("L'attaquant est mort!");
            return 3;
        }else if(valAtt>valDef){
            plateau.afficherInformation2("<html> L'attaquant a infligé "+valAtt+" pts de degat !<br>Le defenseur riposte de "+valDef + " !<br>L'attaquant a réussi son attaque !</html>");
            return 4;
        }
        plateau.afficherInformation2("<html> L'attaquant a infligé "+valAtt+" pts de degat !<br>Le defenseur riposte de "+valDef + " !<br>L'attaquant a raté son attaque ! </html>");
        return 5;
    }


}
