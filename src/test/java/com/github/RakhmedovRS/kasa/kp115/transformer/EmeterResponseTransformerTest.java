package com.github.RakhmedovRS.kasa.kp115.transformer;

import com.github.RakhmedovRS.kasa.kp115.configuration.TestConfiguration;
import com.github.RakhmedovRS.kasa.kp115.pojo.EmeterResponseJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = {
        EmeterResponseJsonTransformer.class
})
@Import(TestConfiguration.class)
class EmeterResponseTransformerTest {
    private final static String VALID_JSON = "{\"emeter\":{\"get_realtime\":{\"current_ma\":2163,\"voltage_mv\":116869,\"power_mw\":239639,\"total_wh\":1208,\"err_code\":0}}}";

    @Autowired
    private EmeterResponseJsonTransformer transformer;

    @Test
    void testTransformWorksForValidInput() throws Exception {
        EmeterResponseJson response = transformer.transform(VALID_JSON);
        Assertions.assertNotNull(response);
        EmeterResponseJson.EmeterJson emeterJson = response.getEmeter();
        Assertions.assertNotNull(emeterJson);
        EmeterResponseJson.RealtimeJson realtimeJson = emeterJson.getGetRealtime();
        Assertions.assertNotNull(realtimeJson);

        Assertions.assertEquals(2163, realtimeJson.getCurrentMa());
        Assertions.assertEquals(116869, realtimeJson.getVoltageMv());
        Assertions.assertEquals(239639, realtimeJson.getPowerMw());
        Assertions.assertEquals(1208, realtimeJson.getTotalWh());
        Assertions.assertEquals(0, realtimeJson.getErrCode());
    }
}