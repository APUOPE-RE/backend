package com.apuope.apuope_re.utils;

public class Constants {
    public enum MessageSource {
        USER(1),
        LLM(2);

        private final int value;

        MessageSource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
