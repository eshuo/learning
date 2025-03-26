//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.demo.etcommon;

import com.wyci.utils.json.JsonUtil;
import java.util.Map;

public class Test {

    public Test() {
    }

    public static void main(String[] args) {
//        try {
//            Common.nativeLoad("EtCommon_vc9_x64_dbg");
//        } catch (Exception var6) {
//            throw new RuntimeException(var6);
//        }
//
////        String version = Crypt.EtGetVersion();
////        System.out.println(version);
//        String key = "123456781234567";
//        String digital = "123";
//        String encDigital = Crypt.DigitalEnc(key, digital);
//        String decDigital = Crypt.DigitalDec(key, encDigital);
//        System.out.println(encDigital);
//        System.out.println(digital.equals(decDigital));
//        digital = "12345619999999999X";
//        encDigital = Crypt.DigitalEnc(key, digital);
//        decDigital = Crypt.DigitalDec(key, encDigital);
//        System.out.println(encDigital);
//        System.out.println(digital.equals(decDigital));
//
//        System.err.println( Crypt.DigitalEnc("18810585582", "500C6779CE15F06599B4B228D08AA576F4DA6362738E6835E48640FC037AEB71"));
//

        String param = "reqMessage=8pzM3u%2BunEBkNSwWmbLi9kmBy8JFOQG3Y446RuaKncAxf46QFmUiWyBurXaSj%2B07MRqaSR0LQJRmScDe3D80O8PNYd3Fw8jOWOmPEFnKE0M4y%2FWwZcD"
            + "%2BAJIS7Zck8RhFguVXl8EdPEQbKQZM3S7iq6yFWcvCXFp1T9px4sUFf6LQVsK14T1GttaU4lRsEgGv6SxZFJ7lRtKjIcElx%2B2qrnQH0uW66iCTWrDlmgM2cEYn70TtFtlopQ9Mm"
             +"S9c8D2Uw1%2FWEOVwokmk1cRSM2axxdZOZPsbzXiDzGriuG2QhxBaCgFFhoKjPHfDfYka9IOOtSTpv6xIvORwqJdsE0KWnNaK1NVfyGVT";


            for (String s : param.split("&")) {
                if (s.contains("reqMessage=")) {
                  System.err.println(s.split("reqMessage=")[1]);
                }
            }

//        ResultLong  resultLong = new ResultLong(1);
//        resultLong.setData(123);
//        resultLong.setResult(2);
//
//        Map<?, ?> map = JsonUtil.pojoToMap(resultLong);
//
//
//        map.forEach((k,v)->{
//            System.err.println("k="+k);
//            System.err.println("v="+v);
//        });

    }
}
