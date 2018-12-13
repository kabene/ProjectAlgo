package jeu;

public class Niveau {
    private int experience;
    private int lvl;

    public Niveau(){
        experience=0;
        lvl=1;
    }

    /**
     *
     * @param experience experience ajoutée
     * @return un boolean qui indique si le lvl a augmenté
     */
    public boolean ajouterExperience(int experience){
        this.experience+=experience;
        if(lvl==1)
            if(this.experience>=100){
                lvl=2;
                this.experience-=100;
                return true;
            }
        if(lvl==2)
            if(this.experience>150){
                lvl=3;
                this.experience-=150;
                return true;
            }
        if(lvl==3)
            if(this.experience>200){
                lvl=4;
                this.experience-=200;
                return true;
            }
        return false;
    }
    public int getExperience(){
        return experience;
    }

    public int getLvl(){
        return lvl;
    }

    public String toString(){
        return "Niveau " + lvl + " à " + experience + " pts d'expérience";
    }
}
