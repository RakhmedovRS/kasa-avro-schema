package com.github.RakhmedovRS.kasa.kp115.util;

import com.github.RakhmedovRS.kasa.kp115.Emeter;
import com.github.RakhmedovRS.kasa.kp115.EmeterRealtime;
import com.github.RakhmedovRS.kasa.kp115.GetRealtime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AvroUtilsTest {
    private final static String VALID_JSON = "{\"emeter\":{\"get_realtime\":{\"current_ma\":2163,\"voltage_mv\":116869,\"power_mw\":239639,\"total_wh\":1208,\"err_code\":0}}}";

    @Test
    void testReadAvroJson() {
        EmeterRealtime emeterRealtime = AvroUtils.readAvroJson(VALID_JSON.getBytes(StandardCharsets.UTF_8), EmeterRealtime.class);
        assertNotNull(emeterRealtime);
        Emeter emeter = emeterRealtime.getEmeter();
        assertNotNull(emeter);
        GetRealtime realtime = emeter.getGetRealtime();
        Assertions.assertNotNull(realtime);

        Assertions.assertEquals(2163, realtime.getCurrentMa());
        Assertions.assertEquals(116869, realtime.getVoltageMv());
        Assertions.assertEquals(239639, realtime.getPowerMw());
        Assertions.assertEquals(1208, realtime.getTotalWh());
        Assertions.assertEquals(0, realtime.getErrCode());
    }

    @Test
    void testReadAvroJsonThrowsExceptionForIncorrectInput() {
        Assertions.assertThrows(RuntimeException.class, () -> AvroUtils.readAvroJson(new byte[1], EmeterRealtime.class));
    }

    @Test
    void testReadAvroJsonThrowsExceptionForIncorrectClass() {
        InputStream inputStream = new ByteArrayInputStream(VALID_JSON.getBytes(StandardCharsets.UTF_8));
        Assertions.assertThrows(RuntimeException.class, () -> AvroUtils.readAvroJson(inputStream, GetRealtime.class));
    }

    @Test
    public void testWriteAvroJson() {
        GetRealtime realtime = new GetRealtime();
        realtime.setCurrentMa(2163);
        realtime.setVoltageMv(116869);
        realtime.setPowerMw(239639);
        realtime.setTotalWh(1208);
        realtime.setErrCode(0);

        Emeter emeter = new Emeter();
        emeter.setGetRealtime(realtime);

        EmeterRealtime emeterRealtime = new EmeterRealtime();
        emeterRealtime.setEmeter(emeter);

        String json = AvroUtils.writeAvroJson(emeterRealtime);
        Assertions.assertEquals(VALID_JSON, json);
    }
}