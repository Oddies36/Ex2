package be.iramps.ue1103.Pret;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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

  @ParameterizedTest
  @MethodSource("getArgsITCalculTotalProjetAchat")
  public void ITCalculTotalProjetAchat_ValeursPos(double prixHab, double fraisNot, double fraisTrans, int revCad, double expected){

    projet.setPrixHabitation(prixHab);
    projet.setFraisNotaireAchat(fraisNot);
    projet.setFraisTransformation(fraisTrans);
    projet.setRevenuCadastral(revCad);

    Assertions.assertEquals(expected, projet.calculTotalProjetAchat());
  }

  static Stream<Arguments> getArgsITCalculTotalProjetAchat(){
    
    return Stream.of(
      Arguments.arguments(15_000.00, 1_000.00, 100_000.00, 40, 122_000.00),
      Arguments.arguments(39_999.00, 3_000.00, 30_000.00, 100, 74_799.00),
      Arguments.arguments(40_000.00, 3_000.00, 40_000.00, 150, 85_400.00),
      Arguments.arguments(50_000.00, 4_500.00, 20_000.00, 170, 76_300.00),
      Arguments.arguments(200_000.00, 24_000.00, 10_000.00, 745, 244_200.00),
      Arguments.arguments(350_000.00, 43_000.00, 5_000.00, 746, 437_050.00),
      Arguments.arguments(400_000.00, 50_000.00, 0.00, 900, 495_833.33),
      Arguments.arguments(500_000.00, 65_000.00, 50_000.00, 1_000, 678_000.00),
      Arguments.arguments(700_000.00, 90_000.00, 1_000.00, 1_300, 876_060.00)
    );
  }

  @Test
  @DisplayName("IT calculTotalProjetAchat sur valeurs négatifs")
  public void ITCalculTotalProjetAchat_ValeursNeg(){
    Assertions.assertAll(
      () -> {
        projet.setPrixHabitation(-100_000.00);
        projet.setFraisNotaireAchat(5_000.00);
        projet.setFraisTransformation(30_000.00);
        projet.setRevenuCadastral(450);

        Assertions.assertEquals("Entrée invalide", projet.calculTotalProjetAchat());
      },
      () -> {
        projet.setPrixHabitation(100_000.00);
        projet.setFraisNotaireAchat(-5_000.00);
        projet.setFraisTransformation(30_000.00);
        projet.setRevenuCadastral(450);

        Assertions.assertEquals("Entrée invalide", projet.calculTotalProjetAchat());
      },
      () -> {
        projet.setPrixHabitation(100_000.00);
        projet.setFraisNotaireAchat(5_000.00);
        projet.setFraisTransformation(-30_000.00);
        projet.setRevenuCadastral(450);

        Assertions.assertEquals("Entrée invalide", projet.calculTotalProjetAchat());
      },
      () -> {
        projet.setPrixHabitation(100_000.00);
        projet.setFraisNotaireAchat(5_000.00);
        projet.setFraisTransformation(30_000.00);
        projet.setRevenuCadastral(-450);

        Assertions.assertEquals("Entrée invalide", projet.calculTotalProjetAchat());
      }
    );
  }
}
