package be.iramps.ue1103.Pret;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ITProjet {
  private static Projet projet;

  @BeforeAll
  public static void init(){
    projet = new Projet();
  }

  @Test
  public void testCalculTotalProjetAchat_happyPath(){

    projet.setPrixHabitation(150_000);
    projet.setFraisNotaireAchat(26_000);
    projet.setFraisTransformation(10_000);
    projet.setRevenuCadastral(700);

    Assertions.assertEquals(193_200, projet.calculTotalProjetAchat(), 0.01);
  }
}
