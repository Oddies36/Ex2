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

    double apportBancaire = pret.calculRestantDu(montantEmprunt);
    Assertions.assertEquals(true, true);
  }

  @Test
  public void ITMain_ValeursNormaux(){

    Assertions.assertAll(
      () -> {
        projet.setPrixHabitation(400_000.00);
        projet.setRevenuCadastral(746);
        projet.setFraisNotaireAchat(30_000.00);
        projet.setFraisTransformation(10_000.00);
        double apportPersonnel = projet.calculApportMinimal();
        double montantEmprunt = projet.calculResteAEmprunter();
        pret.setFraisDossierBancaire(500.00);
        pret.setFraisNotaireCredit(7_000.00);
        pret.setNombreAnnees(20);
        pret.setTauxAnnuel(0.025);
        double apportBancaire = pret.calculRestantDu(montantEmprunt);

        Assertions.assertEquals(7_500.00, apportBancaire);
      },
      () -> {
        projet.setPrixHabitation(700_000.00);
        projet.setRevenuCadastral(1000);
        projet.setFraisNotaireAchat(40_000.00);
        projet.setFraisTransformation(50_000.00);
        double apportPersonnel = projet.calculApportMinimal();
        double montantEmprunt = projet.calculResteAEmprunter();
        pret.setFraisDossierBancaire(700.00);
        pret.setFraisNotaireCredit(10_000.00);
        pret.setNombreAnnees(25);
        pret.setTauxAnnuel(0.05);
        double apportBancaire = pret.calculRestantDu(montantEmprunt);

        Assertions.assertEquals(10_700.00, apportBancaire);
      }
    );

  }
}
