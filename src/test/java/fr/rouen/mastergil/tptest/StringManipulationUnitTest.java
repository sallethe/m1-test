package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import fr.rouen.mastergil.tptest.bonus.StringManipulation;
import static fr.rouen.mastergil.tptest.bonus.StringManipulation.a;

public class StringManipulationUnitTest {

    @Test
    public void aShouldReturnFalseOnEmpty() {
        // GIVEN
        String s = "";
        // WHEN
        boolean res = a(s);
        // THEN
        assertThat(!res);
    }

    @Test
    public void aShouldReturnFalseOnNull() {
        // GIVEN
        String s = null;
        // WHEN
        boolean res = a(s);
        // THEN
        assertThat(!res);
    }

    @Test
    public void aShouldReturnTrueOnPalindrome() {
        // GIVEN
        String s = "kayak";
        // WHEN
        boolean res = a(s);
        // THEN
        assertThat(res);
    }

    @Test
    public void aShouldReturnFalseOnHello() {
        // GIVEN
        String s = "Hello";
        // WHEN
        boolean res = a(s);
        // THEN
        assertThat(!res);
    }

    @Test
    public void bShouldReturnThreeOnHello() {
        // GIVEN
        String s = "hello";
        StringManipulation m = new StringManipulation();
        // WHEN
        int res = m.b(s);
        // THEN
        assertThat(res == 3).isTrue();
    }

    @Test
    public void cShouldReturnEmptyOnEmptyTab() {
        // GIVEN
        String[] strs = {};
        StringManipulation m = new StringManipulation();
        // WHEN
        String res = m.c(strs);
        // THEN
        assertThat(res.equals("")).isTrue();

    }

    @Test
    public void cShouldReturnBa() {
        // GIVEN
        String[] strs = { "ballon", "balle", "baltringue", "baka" };
        StringManipulation m = new StringManipulation();
        // WHEN
        String res = m.c(strs);
        // THEN
        assertThat(res.equals("ba")).isTrue();
    }

    @Test
    public void cShouldReturnEmptyStringOnDifferentPrefix() {
        // GIVEN
        String[] strs = { "xenophobie", "chat" };
        StringManipulation m = new StringManipulation();
        // WHEN
        String res = m.c(strs);
        // THEN
        assertThat(res.equals("")).isTrue();
    }

    @Test
    public void dShouldReturnOnEmpty() {
        // GIVEN
        String s1 = "";
        String s2 = "";
        StringManipulation m = new StringManipulation();
        // WHEN
        int res = m.d(s1, s2);
        // THEN
        assertThat(res == 0).isTrue();
    }

    @Test
    public void dShouldReturnTwo() {
        // GIVEN
        String s1 = "helllo";
        String s2 = "hello";
        StringManipulation m = new StringManipulation();
        // WHEN
        int res = m.d(s1, s2);
        // THEN
        assertThat(res == 3).isTrue();
    }

    /*
     * @Test
     * public void eShouldReturn483910572() {
     * // GIVEN
     * String str = "abegiostz";
     * StringManipulation m = new StringManipulation();
     * // WHEN
     * String res = m.e(str);
     * // THEN
     * assertThat(res.equals("483910572")).isTrue();
     * 
     * }
     */

}
