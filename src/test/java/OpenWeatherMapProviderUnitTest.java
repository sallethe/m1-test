
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.rouen.mastergil.tptest.meteo.OpenWeatherMapProvider;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherMapProviderUnitTest {

    // ATTRIBUTES

    private OpenWeatherMapProvider testTemplate;

    @BeforeEach
    public void setUp() {
        testTemplate = new OpenWeatherMapProvider();
    }

    @Test
    public void shouldThrowException() {
        // GIVEN
        String city = "vrenikefoihfmkoieyhzfgief";
        // THEN
        assertThatThrownBy(() -> {
            // WHEN
            testTemplate.getForecastByCity(city);
        }).isInstanceOf(RuntimeException.class).message().startsWith("Failed");
    }

}
