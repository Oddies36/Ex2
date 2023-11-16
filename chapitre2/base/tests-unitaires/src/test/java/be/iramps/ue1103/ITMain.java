package be.iramps.ue1103;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import be.iramps.ue1103.Pret.Pret;
import be.iramps.ue1103.Pret.Projet;

public class ITMain {
  private static Projet projet;
  private static Pret pret;

  @BeforeAll
  public static void init(){
    projet = new Projet();
    pret = new Pret();
  }

  @Test
  public void happyPath(){

    projet.setPrixHabitation(100_000);
    projet.setRevenuCadastral(700);
    projet.setFraisNotaireAchat(4_150);
    projet.setFraisTransformation(60_000);
    double apportPersonnel = projet.calculApportMinimal();
    double montantEmprunt = projet.calculResteAEmprunter();
    pret.setFraisDossierBancaire(500);
    pret.setFraisNotaireCredit(5_475);
    pret.setNombreAnnees(15);
    pret.setTauxAnnuel(0.04);

    Assertions.assertEquals(true, true);

    double apportBancaire = pret.calculRestantDu(montantEmprunt);
  }

  @Test
  public void ITMain_ValeursNormaux(){

    Assertions.assertAll(
      () -> {
        projet.setPrixHabitation(100_000);
        projet.setRevenuCadastral(700);
        projet.setFraisNotaireAchat(4_150);
        projet.setFraisTransformation(60_000);
        pret.setFraisDossierBancaire(500);
        pret.setFraisNotaireCredit(5_475);
        pret.setNombreAnnees(15);
        pret.setTauxAnnuel(0.04);

        Assertions.assertEquals(true, true);

      }
    );

  }
}
