package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class BankAccountUnitTest {

    @Test
    public void shouldBeDefault() {
        // GIVEN
        BankAccount testAccount = new BankAccount();
        Money defaultMoney = new Money();
        // WHEN
        testAccount.creerCompte();
        // THEN
        assertThat(testAccount.solde.getMontant() == defaultMoney.getMontant()).isTrue();
        assertThat(testAccount.solde.getDevise() == defaultMoney.getDevise()).isTrue();
    }

    @Test
    public void shouldBeInitializedWithParam() {
        // GIVEN
        BankAccount testAccount = new BankAccount();
        Money testMoney = new Money(10, Devise.DOLLAR);
        // WHEN
        testAccount.creerCompte(testMoney.getMontant(), testMoney.getDevise());
        // THEN
        assertThat(testAccount.solde.getMontant() == testMoney.getMontant()).isTrue();
        assertThat(testAccount.solde.getDevise() == testMoney.getDevise()).isTrue();
    }

    @Test
    public void shouldReturnDefaultString() {
        // GIVEN
        String msg = "Votre solde actuel est de 0 EURO";
        BankAccount testAccount = new BankAccount();
        // WHEN
        testAccount.creerCompte();
        // THEN
        assertThat(testAccount.consulterSolde().equals(msg)).isTrue();

    }

    @Test
    public void shouldReturnStringFromInitialisedWithParam() {
        // GIVEN
        String msg = "Votre solde actuel est de 10 YEN";
        BankAccount testAccount = new BankAccount();
        // WHEN
        testAccount.creerCompte(10, Devise.YEN);
        // THEN
        assertThat(testAccount.consulterSolde().equals(msg)).isTrue();

    }

    @Test
    public void shouldAlterSolde() {
        // GIVEN
        String msg = "Votre solde actuel est de 10 EUR";
        BankAccount testAccount = new BankAccount();
        testAccount.creerCompte(5, Devise.EURO);
        // WHEN
        testAccount.deposerArgent(5);
        // THEN
        assertThat(testAccount.solde.getMontant() == 10).isTrue();
    }

    @Test
    public void shouldBePositive() {
        // GIVEN
        BankAccount testAccount = new BankAccount();
        testAccount.creerCompte(10, Devise.EURO);
        // WHEN
        testAccount.retirerArgent(5);
        // THEN
        assertThat(testAccount.isSoldePositif()).isTrue();
    }

    @Test
    public void shouldBeNegative() {
        // GIVEN
        BankAccount testAccount = new BankAccount();
        testAccount.creerCompte(10, Devise.EURO);
        // WHEN
        testAccount.retirerArgent(15);
        // THEN
        assertThat(testAccount.isSoldePositif()).isFalse();
    }

}
