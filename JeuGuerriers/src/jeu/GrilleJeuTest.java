package jeu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrilleJeuTest {
    private  String []nomTest=new String[]{"jhon","lam","ram","rami","42"};
    private GrilleJeu test=new GrilleJeu(5,15,2,3,10,nomTest);
    //UTILS

    @Test
    void donnerJoueur() {
        assertNotEquals("jhon",test.donnerPion(1),"TEST Donner Joueur 1 échoué");
        System.out.println("Test donner joueur 1 réussi");
    }
    @Test
    void supprimerPion() {
        Guerrier a=test.donnerPion(1);
        test.supprimerPion(1);
        assertEquals(null,test.donnerPion(1),"test suppression échoué");
        System.out.println("test suppréssion réussi");

    }

    @Test
    void classerGuerriers() {
    }
}