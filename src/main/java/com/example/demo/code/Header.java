package com.example.demo.code;

public enum Header {
    AEROSCOPE_USERNAME("x-aeroscope-username"),

    AEROSCOPE_SIGNATURE("x-aeroscope-signature");


    private final String value;

    Header(String value) {
        this.value = value;
    }

    /**
     * 获取值
     *
     * @return 值
     */
    public String getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
