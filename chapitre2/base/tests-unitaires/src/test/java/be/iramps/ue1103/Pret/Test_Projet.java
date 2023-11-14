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

  @BeforeAll
  public static void init() {
    projet = new Projet();
    mockedProject = Mockito.spy(projet);
  }


  @Disabled
  @Nested
  @DisplayName("Regroupement de tests unitaire calculResteAEmprunter")
  public class TestCalculResteAEmprunter{
    @ParameterizedTest
    @MethodSource("getArgsCalculResteAEmprunter")
    public void testCalculResteAEmprunter(double calcTotal, double calcApportMin, double result){
      Mockito.doReturn(calcTotal).when(mockedProject).calculTotalProjetAchat();
      Mockito.doReturn(calcApportMin).when(mockedProject).calculApportMinimal();

      Assertions.assertEquals(result, mockedProject.calculResteAEmprunter());
    }

    static Stream<Arguments> getArgsCalculResteAEmprunter(){
      return Stream.of(
        Arguments.arguments(100_000.00, 10_000.00, 90_000.00)
      );
    }
  }


  @Disabled
  @Nested
  @DisplayName("Regroupement de tests unitaire calculTotalProjetAchat")
  public class TestCalculTotalProjetAchat {
    @ParameterizedTest
    @MethodSource("getArgsCalculTotalProjetAchatValPos")
    public void testMockCalculTotalProjetAchatValeursPos(double mockDE, double mockFT, double prixHab, double fraisNot,
        double fraisTrans, double result) {
      mockedProject.setPrixHabitation(prixHab);
      mockedProject.setFraisNotaireAchat(fraisNot);
      mockedProject.setFraisTransformation(fraisTrans);
      Mockito.doReturn(mockDE).when(mockedProject).calculDroitEnregistrement();
      Mockito.doReturn(mockFT).when(mockedProject).calculTVAFraisTransformation();

      Assertions.assertEquals(result, mockedProject.calculTotalProjetAchat());
    }

    static Stream<Arguments> getArgsCalculTotalProjetAchatValPos() {
      return Stream.of(
          Arguments.arguments(0.00, 0.00, 0.00, 0.00, 0.00, 0.00),
          Arguments.arguments(15_000.00, 500.00, 0.00, 0.00, 0.00, 15_500.00),
          Arguments.arguments(15_000.00, 500.00, 300_000.00, 50_000.00, 50_000.00, 415_500.00),
          Arguments.arguments(15_000.00, 500.00, 500_000.00, 100_000.00, 100_000.00, 715_500.00),
          Arguments.arguments(15_000.00, 500.00, 1_000_000.00, 150_000.00, 200_000.00, 1_365_500.00),
          Arguments.arguments(15_000.00, 500.00, 0.00, 100_000.00, 100_000.00, 215_500.00),
          Arguments.arguments(15_000.00, 500.00, 500_000.00, 0.00, 100_000.00, 615_500.00),
          Arguments.arguments(15_000.00, 500.00, 500_000.00, 100_000.00, 0.00, 615_500.00),
          Arguments.arguments(15_000.00, 500.00, 0.01, 0.01, 0.01, 15_500.03));
    }

    @ParameterizedTest
    @MethodSource("getArgsCalculTotalProjetAchatErrorMsg")
    public void testCalculTotalProjetAchatErrorMsg(double prixHab, double fraisNot, double fraisTrans, String msg) {
      mockedProject.setPrixHabitation(prixHab);
      mockedProject.setFraisNotaireAchat(fraisNot);
      mockedProject.setFraisTransformation(fraisTrans);

      Mockito.doReturn(15_000.00).when(mockedProject).calculDroitEnregistrement();
      Mockito.doReturn(500.00).when(mockedProject).calculTVAFraisTransformation();

      Assertions.assertEquals(msg, mockedProject.calculTotalProjetAchat());
    }

    static Stream<Arguments> getArgsCalculTotalProjetAchatErrorMsg() {
      return Stream.of(
          Arguments.arguments(-100_000.00, -50_000.00, -100_000.00, "Valeur ne peut pas être négatif"),
          Arguments.arguments(Double.MAX_VALUE, 5_000.00, 10_000.00, "Valeur est trop grande"),
          Arguments.arguments(Double.MIN_VALUE, 5_000.00, 10_000.00, "Valeur ne peut pas être négatif"));
    }
  }
}
