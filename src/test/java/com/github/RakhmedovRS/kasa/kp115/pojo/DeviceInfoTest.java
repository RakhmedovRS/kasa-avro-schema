package com.github.RakhmedovRS.kasa.kp115.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeviceInfoTest {
    @Test
    public void testGettingFullName(){
        DeviceInfo deviceInfo = new DeviceInfo("10.0.0.0", "dummy", "dummy");
        Assertions.assertEquals("dummy(dummy:10.0.0.0)", deviceInfo.getFullDeviceName());
    }
}