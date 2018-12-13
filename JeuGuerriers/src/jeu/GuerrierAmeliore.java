package jeu;

public class GuerrierAmeliore extends Guerrier{
    private Niveau niveau; // niveau du guerrier

    public GuerrierAmeliore(int numJoueur, int ptsVie, int numGuerrier){
        super(numJoueur, ptsVie, numGuerrier);
        this.niveau = new Niveau();
    }

    public Niveau getNiveau(){
        return niveau;
    }

    public String toString(){
        return super.toString() + niveau.toString();
    }
}
