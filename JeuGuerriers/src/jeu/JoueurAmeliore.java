package jeu;

public class JoueurAmeliore {
    private Guerrier[] guerriers ; // tableau contenant les guerriers du joueur
    private int numJoueur ; // num�ro du joueur
    private String nom; //nom du joueur
    private boolean enVie; // Savoir si un joueur était encore en vie au début du tour

    /**
     * Cr�e un joueur du jeu des guerriers
     * @param nom : le nom du joueur
     * @param nbGuerriers : le nombre de guerriers du joueur
     * @param ptsVie : le nombre de points de vie de d�part des guerriers du joueur
     * @param numJoueur : num�ro du joueur
     */
    public JoueurAmeliore(String nom, int nbGuerriers, int ptsVie, int numJoueur) {
        this.nom=nom;
        this.guerriers=new Guerrier[nbGuerriers];
        for(int i=0; i<nbGuerriers; i++){
            guerriers[i]=new Guerrier(numJoueur,ptsVie,i+1);
        }
        this.numJoueur=numJoueur;
        enVie=true;

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

        return guerriers[numGuerrier-1] ;
    }

    public boolean estEnVie(){
        return enVie;
    }

    public void plusEnVie(){
        enVie=false;
    }
    /**
     * D�termine le nombre de guerrier encore en vie du joueur
     * @return le nombre de guerrier encore en vie du joueur
     */
    public int nombreDeGuerriersEnVie() {
        int nbrEnVie=0;
        for(Guerrier guerrier : guerriers){
            if(guerrier.getPtsVie()>0)
                nbrEnVie++;
        }
        return nbrEnVie;
    }

}


