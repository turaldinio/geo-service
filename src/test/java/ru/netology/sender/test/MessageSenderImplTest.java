package ru.netology.sender.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

class MessageSenderImplTest {
    private Map<String, String> headers;

    @BeforeEach
    public void init() {
        headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, "172.123.12.19");

    }

    @Test
    void send() {

        GeoService geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.spy(LocalizationServiceImpl.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);
        if (headers.get(IP_ADDRESS_HEADER).startsWith("172.")) {
            assertEquals("Добро пожаловать", result);
        } else {
            assertEquals("Welcome", result);
        }
    }
}