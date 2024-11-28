package com.github.RakhmedovRS.kasa.kp115.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmeterResponseJson {
    private EmeterJson emeter;

    @Data
    public static class EmeterJson {
        @JsonProperty("get_realtime")
        private RealtimeJson getRealtime;
    }

    @Data
    public static class RealtimeJson {
        @JsonProperty("current_ma")
        private int currentMa;

        @JsonProperty("voltage_mv")
        private int voltageMv;

        @JsonProperty("power_mw")
        private int powerMw;

        @JsonProperty("total_wh")
        private int totalWh;

        @JsonProperty("err_code")
        private int errCode;
    }
}
