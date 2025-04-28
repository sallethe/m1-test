package fr.rouen.mastergil.tptest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.rouen.mastergil.tptest.meteo.Prevision;

public class PrevisionUnitTest {

    // ATTRIBUTES

    private Prevision previsionTemplate;

    @BeforeEach
    public void instantiate() {
        previsionTemplate = new Prevision();
    }

    // TESTS

    @Test
    public void shouldSetDate() {
        // GIVEN
        Date testDate = new Date();
        // WHEN
        previsionTemplate.setDate(testDate);
        // THEN
        assertThat(previsionTemplate.getDate().equals(testDate)).isTrue();

    }

    @Test
    public void shouldSetTempMin() {
        // GIVEN
        double tempMinTest = 2.0;
        // WHEN
        previsionTemplate.setTempMin(tempMinTest);
        // THEN
        assertThat(previsionTemplate.getTempMin() == tempMinTest).isTrue();
    }

    @Test
    public void shouldSetTempMax() {
        // GIVEN
        double tempMaxTest = 2.0;
        // WHEN
        previsionTemplate.setTempMax(tempMaxTest);
        // THEN
        assertThat(previsionTemplate.getTempMax() == tempMaxTest).isTrue();
    }

    @Test
    public void shouldSetTempDay() {
        // GIVEN
        double tempDayTest = 2.0;
        // WHEN
        previsionTemplate.setTempDay(tempDayTest);
        // THEN
        assertThat(previsionTemplate.getTempDay() == tempDayTest).isTrue();
    }

    @Test
    public void shouldSetTempNight() {
        // GIVEN
        double tempNightTest = 2.0;
        // WHEN
        previsionTemplate.setTempNight(tempNightTest);
        // THEN
        assertThat(previsionTemplate.getTempNight() == tempNightTest).isTrue();
    }

    @Test
    public void shouldSetDescription() {
        // GIVEN
        String description = "description";
        // WHEN
        previsionTemplate.setDescription(description);
        // THEN
        assertThat(previsionTemplate.getDescription().equals(description));
    }

    @Test
    public void shouldReturnDefaultString() {
        // GIVEN
        String defaultString = "Prevision{date=null, tempMin=0.0, tempMax=0.0, tempDay=0.0, tempNight=0.0, description='}"
                + '\'';
        // WHEN
        String res = previsionTemplate.toString();
        // THEN
        assertThat(res.equals(defaultString));
    }
}
