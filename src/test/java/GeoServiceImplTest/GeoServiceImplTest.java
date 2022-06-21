package GeoServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    GeoServiceImpl geoService;

    @BeforeEach
    public void init() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @ValueSource(strings = {"127.0.01", "172.0.32.11", "96.44.183.149", "172.6334", "96.524", " "})
    void byIp(String ip) {
        Location location = geoService.byIp(ip);
        if (ip.equals(GeoServiceImpl.LOCALHOST)) {
            assertEquals(0, location.getBuiling());
            assertNull(location.getCountry());
            assertNull(location.getCity());
            assertNull(location.getStreet());
            return;
        }
        if (ip.equals(GeoServiceImpl.MOSCOW_IP)) {
            assertEquals("Moscow", location.getCity());
            assertEquals(Country.RUSSIA, location.getCountry());
            assertEquals("Lenina", location.getStreet());
            assertEquals(15, location.getBuiling());
            return;
        }
        if (ip.equals(GeoServiceImpl.NEW_YORK_IP)) {
            assertEquals("New York", location.getCity());
            assertEquals(Country.USA, location.getCountry());
            assertEquals(" 10th Avenue", location.getStreet());
            assertEquals(32, location.getBuiling());
            return;
        }
        if (ip.startsWith("172.")) {
            assertEquals("Moscow", location.getCity());
            assertEquals(Country.RUSSIA, location.getCountry());
            assertNull(location.getStreet());
            assertEquals(0, location.getBuiling());
            return;
        }
        if (ip.startsWith("96.")) {
            assertEquals("New York", location.getCity());
            assertEquals(Country.USA, location.getCountry());
            assertNull(location.getStreet());
            assertEquals(0, location.getBuiling());
            return;
        }
        assertNull(location);

    }

    @Test
    public void byCoordinates() {
        assertThrows(RuntimeException.class, () -> geoService.byCoordinates(3, 5));
    }
}