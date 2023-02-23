package com.accendl.myweibo.security.mfa;

public class Code {

    private int code1;
    private int code2;
    private int code3;
    private int code4;
    private int code5;
    private int code6;

    @Override
    public String toString(){
        return ""+code1+code2+code3+code4+code5+code6;
    }

    public int getCode1() {
        return code1;
    }

    public void setCode1(int code1) {
        this.code1 = code1;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public int getCode3() {
        return code3;
    }

    public void setCode3(int code3) {
        this.code3 = code3;
    }

    public int getCode4() {
        return code4;
    }

    public void setCode4(int code4) {
        this.code4 = code4;
    }

    public int getCode5() {
        return code5;
    }

    public void setCode5(int code5) {
        this.code5 = code5;
    }

    public int getCode6() {
        return code6;
    }

    public void setCode6(int code6) {
        this.code6 = code6;
    }
}
