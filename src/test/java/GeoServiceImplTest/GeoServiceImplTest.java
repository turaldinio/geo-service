package GeoServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    GeoServiceImpl geoService;

    @BeforeEach
    public void init() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("source")
    void byIp(String ip, Location location) {
        Location location1 = geoService.byIp(ip);
        assertEquals(location, location1);

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.243211", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.24872491", new Location("New York", Country.USA, null, 0))

        );
    }


    @Test
    public void byCoordinates() {
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(3, 5));
    }
}