package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.rouen.mastergil.tptest.meteo.IWeatherProvider;
import fr.rouen.mastergil.tptest.meteo.StationMeteo;

@ExtendWith(MockitoExtension.class)
public class StationMeteoUnitTest {

    // ATTRIBUTE

    @Mock
    private IWeatherProvider weatherProviderMock;

    @InjectMocks
    private StationMeteo stationTemplate;

    // TESTS

    @Test
    public void shouldThrowExceptionOn400() {
        // GIVEN
        Mockito.when(weatherProviderMock.getForecastByCity("Paris")).thenThrow(new RuntimeException("Failed : HTTP"));
        // WHEN
        Exception e = assertThrows(RuntimeException.class, () -> {
            stationTemplate.majPrevision("Paris");
        });
        // THEN
        assertThat(e.getMessage()).startsWith("Failed");
    }

    @Test
    public void shouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            stationTemplate.majPrevision(null);
        });
        // THEN
        assertThat(e.getMessage().equals("City is not optional")).isTrue();
    }

}
