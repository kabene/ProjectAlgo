package jeu;

public class Niveau {
    private int experience;
    private int lvl;

    public Niveau() {
        experience=0;
        lvl=1;
    }

    public int getExperience() {
        return experience;
    }

    public int getLvl() {
        return lvl;
    }

    public int addExperience (int experience, Guerrier a){
            this.experience += experience;
            if (lvl == 4) {
              return 1;
            }
            if (lvl == 1) {
                if (this.experience >= 100) {
                    lvl = 2;
                    return 2;
                }
            }
            if (lvl == 2) {
                if (this.experience >= 125) {
                    lvl = 3;
                    return 3;
                }
            }
            if (lvl == 3) {
                if (this.experience  >=150) {
                    lvl = 4;
                    return 4;
                }
            }

            throw new IllegalStateException("le niveau est suppérieur à 3");
    }




}

