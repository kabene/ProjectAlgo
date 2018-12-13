package jeu;
/**
 * @author Abene Karim, Sivixay Celestin
 *
 *         Classe de test
 *
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {
    private Joueur test=new Joueur("Jhon",4,1,3);

    @Test
    void getNom() {
        String name="Jhon";
        assertTrue(name.equals(test.getNom()), "TEST Nom1 ECHOUE noms incorecte");
        System.out.println("test Nom1 réussi");
    }

    @Test
    void getNumJoueur() {
        assertNotEquals(0, test.getNumJoueur(),"TEST Num1 ECHOUE la place du joueur n'est pas actualisée ou initialisée");
        System.out.println("test1 Num1 réussi");
        int num=4;
        assertFalse(num==test.getNumJoueur(), "TEST Num2 ECHOUE numéro incorecte");
        System.out.println("test1 Num2 réussi");
    }

    @Test
    void getGuerrier() {
        Guerrier a=test.getGuerrier(1);
        assertNotNull(test.getGuerrier(1)," TEST GetGuerrier1 ECHOUE le joueur doit avoir au moins un guerrier ");
        System.out.println("test getGuerrier1 réussi");
        assertTrue(test.getGuerrier(1).getClass()==a.getClass()," TEST GetGuerrier2 ECHOUE guerrier mal initialisé");
        System.out.println("test getGuerrier2 réussi");
    }

}