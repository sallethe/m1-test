package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MoneyUnitTest {

    // ATTRIBUTES

    private Money moneySample;

    @BeforeEach
    public void instanciate() {
        moneySample = new Money();
    }

    @Test
    public void shouldBeDefault() {
        // GIVEN
        Money testMoney;
        // WHEN
        testMoney = new Money();
        // THEN
        assertThat(testMoney.getMontant() == 0).isTrue();
        assertThat(testMoney.getDevise() == Devise.EURO).isTrue();
    }

    @Test
    public void shouldBeInitialisedWithParam() {
        // GIVEN
        int amount = 12;
        Devise devise = Devise.DOLLAR;
        // WHEN
        Money testMoney = new Money(amount, devise);
        // THEN
        assertThat(testMoney.getMontant() == amount && testMoney.getDevise() == devise).isTrue();
    }

    @Test
    public void shouldBePositive() {
        // GIVEN
        moneySample.setMontant(0);
        // WHEN
        boolean res = moneySample.isPositif();
        // THEN
        assertThat(res).isTrue();
    }

    @Test
    public void shouldBeNegative() {
        // GIVEN
        moneySample.setMontant(-1);
        // WHEN
        boolean res = moneySample.isPositif();
        // THEN
        assertThat(res).isFalse();
    }

    @Test
    public void shouldReturnDeviseException() {
        assertThatThrownBy(() -> {
            moneySample.setDevise(null);
        }).hasMessage("Devise doit être spécifiée");
    }

    @Test
    public void shouldSetDevise() {
        // GIVEN
        Devise testDevise = Devise.DINAR;
        // WHEN
        moneySample.setDevise(testDevise);
        // THEN
        assertThat(moneySample.getDevise() == testDevise).isTrue();
    }

}
