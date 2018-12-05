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
		if(nombreDeGuerriersParJoueur*nombreJoueurs>nombreDeCases)
		    throw new IllegalArgumentException("Pas assez de cases");
	    this.tableJoueurs=new Joueur[nombreJoueurs];
	    for(int i=0;i<nomDesJoueurs.length; i++){
	        tableJoueurs[i]=new Joueur(nomDesJoueurs[i],nombreDeGuerriersParJoueur,ptsVieDeDepart,i+1);
        }
		this.cases=new Guerrier[nombreDeCases];
	    int indCase=0;
	    for(int i=0; i<nombreDeGuerriersParJoueur; i++){
	        for(int j=0; j<nombreJoueurs; j++){
	            cases[indCase]=tableJoueurs[j].getGuerrier(i+1);
	            indCase++;
            }
        }
		this.nombreDeTours=nombreDeTours;

	} 	

	/**
	 * renvoie le joueur dont le numero est passe en parametre
	 * 
	 * @param numJoueur : le numero d'ordre du joueur dans le jeu
	 * @return le joueur
	 */
	public Joueur donnerJoueur(int numJoueur) {
		return tableJoueurs[numJoueur-1] ;
	}

	/**
	 * Renvoie le guerrier se trouvant � la case dont le num�ro est numCase
	 * @param numCase : le num�ro de la case
	 * @return le guerrier se trouvant � la case dont le num�ro est numCase s'il y en a un
	 *         null sinon
	 */
	public Guerrier donnerPion(int numCase) {
		return cases[numCase-1];
	}

	
	/**
	 * Bouge le guerrier se trouvant � la case num�ro caseDepart et le met � la case num�ro caseArriv�e
	 * 
	 * @param caseDepart : num�ro de la case o� se trouve le guerrier � bouger
	 * @param caseArrivee : num�ro de la case o� il faut mettre le guerrier
	 *
	 */
	
	public void bougerPion(int caseDepart, int caseArrivee) {
		Guerrier a=cases[caseDepart-1];
		cases[caseDepart-1]=null;
		cases[caseArrivee-1]=a;
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
		if(cases[numCase-1].getNumJoueur()==joueur.getNumJoueur()){
		    return true;
        }
		return false ;
	}
	
	/**
	 * supprime le guerrier se trouvant � la case num�ro numCase (vide la case)
	 * @param numCase : num�ro de la case
	 */
	
	public void supprimerPion(int numCase) {
		cases[numCase-1]=null;
	}
	
	/**
	 * Classe les guerriers encore un jeu d'abord selon le nombre tour d�j� effectu� (du plus grand au plus petit) et ensuite (si dans le m�me tour) par num�ro de case occup�e (du plus grand au plus petit)  
	 * @return un tableau de pion repr�sentant le classement des pions selon les crit�res ci-dessus
	 */
	public Guerrier[] classerGuerriers() {
        int nombreenvie = 0;
        for (int i = 1; i <= tableJoueurs.length; i++) nombreenvie += tableJoueurs[i].nombreDeGuerriersEnVie();
        Guerrier[] classement = new Guerrier[nombreenvie];

        int nbrGuerrierClasse=0;
        for(int i=1; i<=cases.length; i++){
            int indClassement=0;
            if(donnerPion(i)!=null){
                if(classement[indClassement]==null){
                    classement[indClassement]=donnerPion(i);
                    nbrGuerrierClasse++;
                }else{
                    while(classement[indClassement]!=null && donnerPion(i).getNombreDeTours()<classement[indClassement].getNombreDeTours()){
                        indClassement++;
                    }
                    Guerrier memo=classement[indClassement];
                    classement[indClassement]=donnerPion(i);
                    nbrGuerrierClasse++;
                    indClassement++;
                    while(indClassement<nbrGuerrierClasse){
                        Guerrier memo2 = classement[indClassement];
                        classement[indClassement]=memo;
                        memo=memo2;
                        indClassement++;
                    }
                }
            }

        }

        return classement;

	}

}
