package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountTest {

    public BankAccount bank;

    @BeforeEach
    void initBankAccount() {
        bank = new BankAccount();
    }

    @Test
    void shouldCreateBankAccountWithDefaultMoneyObject() {
        // GIVEN
        Money defaultMoney = new Money();

        // WHEN
        bank.creerCompte();

        // THEN
        assertThat(bank.solde)
                .usingRecursiveComparison()
                .isEqualTo(defaultMoney);
    }

    @Test
    void shouldCreateBankAccountWithSpecificMoneyObject() {
        // GIVEN
        int initAmount = 43;
        Devise initDevise = Devise.PESO;
        Money money = new Money(initAmount, initDevise);

        // WHEN
        bank.creerCompte(initAmount, initDevise);

        // THEN
        assertThat(bank.solde)
                .usingRecursiveComparison()
                .isEqualTo(money);
    }

    @Test
    void shoudDisplayAccurateText() {
        // GIVEN
        int initAmount = 42;
        Devise initDevise = Devise.DOLLAR;
        bank.creerCompte(initAmount, initDevise);

        // WHEN
        String res = bank.consulterSolde();

        // THEN
        assertThat(res)
                .contains(String.valueOf(initAmount))
                .contains(initDevise.name());
    }

    @Test
    void shouldDepositMoneyCorrectly() {
        // GIVEN
        int initAmount = 41;
        int amountToAdd = 69;
        Devise initDevise = Devise.DINAR;
        bank.creerCompte(initAmount, initDevise);
        Money money = new Money(initAmount, initDevise);

        // WHEN
        bank.deposerArgent(amountToAdd);
        money.setMontant(initAmount + amountToAdd);

        // THEN
        assertThat(bank.solde)
                .usingRecursiveComparison()
                .isEqualTo(money);
    }

    @Test
    void shouldWithdrawMoneyCorrectly() {
        // GIVEN
        int initAmount = 40;
        int amountToRemove = 32;
        Devise initDevise = Devise.LIVRE;
        bank.creerCompte(initAmount, initDevise);
        Money money = new Money(initAmount, initDevise);

        // WHEN
        bank.retirerArgent(amountToRemove);
        money.setMontant(initAmount - amountToRemove);

        // THEN
        assertThat(bank.solde)
                .usingRecursiveComparison()
                .isEqualTo(money);
    }

    @Test
    void shouldReturnIsNegative() {
        // GIVEN
        int initAmount = -31;
        Devise initDevise = Devise.LIVRE;

        bank.creerCompte(initAmount, initDevise);
        Money money = new Money(initAmount, initDevise);

        // WHEN
        boolean res = bank.isSoldePositif();

        // THEN
        assertThat(res).isEqualTo(money.isPositif());
    }

    @Test
    void shouldReturnIsPositive() {
        // GIVEN
        int initAmount = 30;
        Devise initDevise = Devise.LIVRE;

        bank.creerCompte(initAmount, initDevise);
        Money money = new Money(initAmount, initDevise);

        // WHEN
        boolean res = bank.isSoldePositif();

        // THEN
        assertThat(res).isEqualTo(money.isPositif());
    }

}