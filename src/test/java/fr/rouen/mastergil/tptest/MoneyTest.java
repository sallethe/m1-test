package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyTest {

    @Test
    public void shouldInstantiateWithoutParameters() {
        // GIVEN
        Money money;

        // WHEN
        money = new Money();

        // THEN
        assertThat(money.getMontant())
                .isEqualTo(0);
        assertThat(money.getDevise())
                .isEqualTo(Devise.EURO);
    }

    @Test
    public void shouldInstantiateWithParameters() {
        // GIVEN
        Devise deviseTest = Devise.YEN;
        int amountTest = 69;
        Money money;

        // WHEN
        money = new Money(amountTest, deviseTest);

        // THEN
        assertThat(money.getDevise())
                .isEqualTo(deviseTest);
        assertThat(money.getMontant())
                .isEqualTo(amountTest);
    }

    @Test
    public void shouldBePositiveWithZero() {
        // GIVEN
        int moneyTest = 0;
        Devise deviseTest = Devise.DINAR;
        Money money = new Money(moneyTest, deviseTest);

        // WHEN
        boolean res = money.isPositif();

        // THEN
        assertThat(res)
                .isTrue();
    }

    @Test
    public void shouldBeNotPositiveWithNegative() {
        // GIVEN
        int moneyTest = -20;
        Devise deviseTest = Devise.DINAR;
        Money money = new Money(moneyTest, deviseTest);

        // WHEN
        boolean res = money.isPositif();

        // THEN
        assertThat(res)
                .isFalse();
    }

    @Test
    public void shouldBePositiveWithPositive() {
        // GIVEN
        int moneyTest = 10;
        Devise deviseTest = Devise.DINAR;
        Money money = new Money(moneyTest, deviseTest);

        // WHEN
        boolean res = money.isPositif();

        // THEN
        assertTrue(res);
    }

    @Test
    public void shouldSetCorrectDevise() {
        // GIVEN
        int amountBase = 42;
        Devise deviseBase = Devise.PESO;
        Devise deviseTest = Devise.LIVRE;
        Money money = new Money(amountBase, deviseBase);

        // WHEN
        money.setDevise(deviseTest);

        // THEN
        assertThat(money.getDevise())
                .isEqualTo(deviseTest);
        assertThat(money.getMontant())
                .isEqualTo(amountBase);
    }

    @Test
    public void shouldThrowErrorOnNullDevise() {
        // GIVEN
        Devise deviseBase = Devise.DOLLAR;
        int amountBase = 42;
        Money money = new Money(amountBase, deviseBase);
        String errorMessage = "Devise doit être spécifiée";

        Exception exception = assertThrowsExactly(IllegalArgumentException.class, () -> {
            // WHEN
            money.setDevise(null);
        }, "Une exception aurait dû être lancée.");

        // THEN
        assertThat(exception.getClass())
                .isEqualTo(IllegalArgumentException.class);
        assertThat(exception.getMessage())
                .isEqualTo(errorMessage);
        assertThat(money.getMontant())
                .isEqualTo(amountBase);
        assertThat(money.getDevise())
                .isEqualTo(deviseBase);
    }
}