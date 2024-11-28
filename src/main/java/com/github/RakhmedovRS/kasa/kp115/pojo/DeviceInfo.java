package com.github.RakhmedovRS.kasa.kp115.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceInfo {
    private String ip;
    private String name;

    public String getFullDeviceName() {
        return String.format("%s(%s)", name, ip);
    }
}
