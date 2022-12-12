package com.example.tp2sifmarchane;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class UtilisateurUnitTest {
    AuthActivity authActivity = mock(AuthActivity.class);
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void AuthentificationUtilisateur() throws Exception{
        AuthActivity authActivity = new AuthActivity();
        when(authActivity.getUtilisateurToken()).thenReturn("SuperTokenIdentificationUtilisateur");
        String jeton = authActivity.getUtilisateurToken();


        assertEquals("SuperTokenIdentificationUtilisateur", jeton);
    }
    @Test
    public void createUtilisateur() throws Exception{
        utilisateur usager = new utilisateur("Sif", "20", "Attaquant", "Maroc");

        assertEquals("Sif", usager.getNom());


    }
}