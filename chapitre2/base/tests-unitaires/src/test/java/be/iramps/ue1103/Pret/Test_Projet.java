package be.iramps.ue1103.Pret;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

public class Test_Projet {

  private static Projet projet;
  private static Projet mockedProject;

  private static Pret pret;

  @BeforeAll
  public static void init() {
    projet = new Projet();
    pret = new Pret();
    mockedProject = Mockito.spy(projet);
  }

  @Nested
  @DisplayName("Regroupement des tests pour les Getters")
  public class TestGetters {

    @Test
    public void testGettersProjet() {
      Assertions.assertAll(
          () -> {
            projet.setFraisNotaireAchat(20_000.00);
            Assertions.assertEquals(20_000.00, projet.getFraisNotaireAchat());
          },
          () -> {
            projet.setFraisTransformation(10_000.00);
            Assertions.assertEquals(10_000.00, projet.getFraisTransformation());
          },
          () -> {
            projet.setPrixHabitation(500_000.00);
            Assertions.assertEquals(500_000.00, projet.getPrixHabitation());
          },
          () -> {
            projet.setRevenuCadastral(700);
            Assertions.assertEquals(700, projet.getRevenuCadastral());
          });
    }

    @Test
    public void testGettersPret() {
      Assertions.assertAll(
          () -> {
            pret.setFraisDossierBancaire(500);
            Assertions.assertEquals(500, pret.getFraisDossierBancaire());
          },
          () -> {
            pret.setFraisNotaireCredit(7_000.00);
            Assertions.assertEquals(7_000.00, pret.getFraisNotaireCredit());
          },
          () -> {
            pret.setNombreAnnees(20);
            Assertions.assertEquals(20, pret.getNombreAnnees());
          },
          () -> {
            pret.setTauxAnnuel(0.05);
            Assertions.assertEquals(0.05, pret.getTauxAnnuel());
          });
    }

  }

  @Disabled
  @Nested
  @DisplayName("Regroupement des tests pour la methode calculProjetTotalAchat")
  public class TestCalculTotalProjetAchat {

    @Test
    public void testCalculProjetTotalAchat_ValeursPos() {

      Assertions.assertAll(
          () -> {
            mockedProject.setPrixHabitation(0.00);
            mockedProject.setFraisNotaireAchat(0.00);
            mockedProject.setFraisTransformation(0.00);

            Mockito.doReturn(0.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(0.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Le prix de l'habitation et des frais de notaire doivent être plus grand que 0",
                mockedProject.calculTotalProjetAchat(), "should be 0");
          },
          () -> {
            mockedProject.setPrixHabitation(0.00);
            mockedProject.setFraisNotaireAchat(0.00);
            mockedProject.setFraisTransformation(0.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Le prix de l'habitation et des frais de notaire doivent être plus grand que 0",
                mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(300_000.00);
            mockedProject.setFraisNotaireAchat(50_000.00);
            mockedProject.setFraisTransformation(50_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals(415_500.00, mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(500_000.00);
            mockedProject.setFraisNotaireAchat(100_000.00);
            mockedProject.setFraisTransformation(100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals(715_500.00, mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(1_000_000.00);
            mockedProject.setFraisNotaireAchat(150_000.00);
            mockedProject.setFraisTransformation(200_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals(1_365_500.00, mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(0.00);
            mockedProject.setFraisNotaireAchat(100_000.00);
            mockedProject.setFraisTransformation(100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Le prix de l'habitation et des frais de notaire doivent être plus grand que 0",
                mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(500_000.00);
            mockedProject.setFraisNotaireAchat(0.00);
            mockedProject.setFraisTransformation(100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Le prix de l'habitation et des frais de notaire doivent être plus grand que 0",
                mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(500_000.00);
            mockedProject.setFraisNotaireAchat(100_000.00);
            mockedProject.setFraisTransformation(0.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals(615_500.00, mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(0.01);
            mockedProject.setFraisNotaireAchat(0.01);
            mockedProject.setFraisTransformation(0.01);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals(15_500.03, mockedProject.calculTotalProjetAchat());
          });
    }

    @Test
    public void testCaculProjetTotalAchat_ValeursNegEtImprobable() {

      Assertions.assertAll(
          () -> {
            mockedProject.setPrixHabitation(-100_000.00);
            mockedProject.setFraisNotaireAchat(-50_000.00);
            mockedProject.setFraisTransformation(-100_000.00);

            Mockito.doReturn(-15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(-500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(100_000.00);
            mockedProject.setFraisNotaireAchat(50_000.00);
            mockedProject.setFraisTransformation(-100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(100_000.00);
            mockedProject.setFraisNotaireAchat(-50_000);
            mockedProject.setFraisTransformation(100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(-100_000.00);
            mockedProject.setFraisNotaireAchat(50_000.00);
            mockedProject.setFraisTransformation(100_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(Double.MAX_VALUE);
            mockedProject.setFraisNotaireAchat(5_000.00);
            mockedProject.setFraisTransformation(10_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur est trop grande", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(Double.MIN_VALUE);
            mockedProject.setFraisNotaireAchat(5_000.00);
            mockedProject.setFraisTransformation(10_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          },
          () -> {
            mockedProject.setPrixHabitation(Double.MIN_VALUE);
            mockedProject.setFraisNotaireAchat(5_000.00);
            mockedProject.setFraisTransformation(10_000.00);

            Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
            Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

            Assertions.assertEquals("Valeur ne peut pas être négatif", mockedProject.calculTotalProjetAchat());
          });
    }
  }

  @Disabled
  @Nested
  @DisplayName("Regroupement des tests pour la methode calculResteAEmprunter")
  public class TestCalculResteAEmprunter {

    @Test
    public void testCalculResteAEmprunter_ValeursPos() {

      Assertions.assertAll(
          () -> {
            Mockito.doReturn(0.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(0.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals(0.00, mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(500_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(20_000.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals(480_000.00, mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(300_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(0.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals(300_000.00, mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(20_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(50_000.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals("L'apport doit être inférieur ou égal au total du projet",
                mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(50_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(50_000.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals(0.00, mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(125_146.19).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(10_012.75).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals(115_133.44, mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(-10_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(20_000.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals("Erreur, un nombre négatif n'est pas autorisé",
                mockedProject.calculResteAEmprunter());
          },
          () -> {
            Mockito.doReturn(100_000.00).when(mockedProject).calculTotalProjetAchat();
            Mockito.doReturn(-20_000.00).when(mockedProject).calculApportMinimal();

            Assertions.assertEquals("Erreur, un nombre négatif n'est pas autorisé",
                mockedProject.calculResteAEmprunter());
          });
    }
  }

  @Disabled
  @Nested
  @DisplayName("Regroupement des tests pour la methode calculAbattement")
  public class TestCalculAbattement {

    @Test
    public void testCalculAbattement() {

      Assertions.assertAll(

          () -> {
            mockedProject.setPrixHabitation(200_000.00);

            Assertions.assertEquals(40_000.00, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(700_000.00);

            Assertions.assertEquals(20_000.00, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(350_000.00);

            Assertions.assertEquals(20_000.00, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(400_000.00);

            Assertions.assertEquals(33_333.34, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(499_999.00);

            Assertions.assertEquals(20_000.13, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(500_000.00);

            Assertions.assertEquals(20_000.00, mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(0);

            Assertions.assertEquals("La valeur de la maison ne peut pas être 0", mockedProject.calculAbattement());
          },
          () -> {
            mockedProject.setPrixHabitation(-100_000);

            Assertions.assertEquals("La valeur de la maison ne peut pas être négatif", mockedProject.calculAbattement());
          }
      );
    }
  }

  @Disabled
  @Nested
  @DisplayName("Regroupement des tests pour la methode calculDroitEnregistrement")
  public class TestCalculDroitEnregistrement {

    @Test
    public void testCalculDroitEnregistrement() {

      Assertions.assertAll(
          () -> {
            mockedProject.setPrixHabitation(500_000.00);
            mockedProject.setRevenuCadastral(800);

            Mockito.doReturn(40_000.00).when(mockedProject).calculAbattement();

            Assertions.assertEquals(57_500.00, mockedProject.calculDroitEnregistrement());
          },
          () -> {
            mockedProject.setPrixHabitation(300_000.00);
            mockedProject.setRevenuCadastral(700);

            Mockito.doReturn(40_000.00).when(mockedProject).calculAbattement();

            Assertions.assertEquals(15_600.00, mockedProject.calculDroitEnregistrement());
          });
    }
  }
}
