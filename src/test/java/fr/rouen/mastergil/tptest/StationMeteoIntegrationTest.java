package fr.rouen.mastergil.tptest;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.rouen.mastergil.tptest.meteo.OpenWeatherMapProvider;
import fr.rouen.mastergil.tptest.meteo.Prevision;
import fr.rouen.mastergil.tptest.meteo.StationMeteo;

public class StationMeteoIntegrationTest {

    // ATTRIBUTES

    private StationMeteo stationTemplate;
    private static PrintStream stream;
    private static OutputStream os;

    // BEFORE

    @BeforeAll
    public static void init() {
        stream = System.out;
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
    }

    // AFTER

    @AfterAll
    public static void dispose() {
        System.setOut(stream);
    }

    // TESTS

    @BeforeEach
    public void setUp() {
        stationTemplate = new StationMeteo(new OpenWeatherMapProvider());
    }

    @Test
    public void shouldReturnNonEmptyList() {
        // GIVEN
        String city = "paris";
        // WHEN
        List<Prevision> res = stationTemplate.majPrevision(city);
        // THEN
        assertThat(res.isEmpty()).isFalse();
    }

    @Test
    public void shouldReturnParisData() {
        // GIVEN
        // WHEN
        StationMeteo.main(null);
        // THEN
        assertThat(os.toString().contains("Prevision")).isTrue();

    }

}
