package LocalizationServiceImplTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    LocalizationServiceImpl localizationService;

    @BeforeEach
    public void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale(Country country) {
        if (country.equals(Country.RUSSIA)) {
            String russianText = localizationService.locale(Country.RUSSIA);
            assertTrue(russianText.matches("[а-яА-Я\\s]*"));
        } else {
            String englishText = localizationService.locale(country);
            assertTrue(englishText.matches("[a-zA-Z\\s]*"));
        }
    }

}