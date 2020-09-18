package com.example.pm.commons;
//
public class MoneyUtils {
    public static Long calcMoney(Long entryTime, Long departureTime, String type) {
        Long lastTime = departureTime - entryTime;
        Long fee;

        switch(type){
            case "军警车辆":{
                fee = 0L;
                return fee;
            }
            case "社会车辆":{
                lastTime /= 60000;
                if(lastTime < 15){
                    fee = 0L;
                    return fee;
                }
                lastTime /= 60;
                if(lastTime < 1){
                    fee = 16L;
                    return fee;
                }else{
                    fee = 16L + 25 * (lastTime - 1);
                    return fee;
                }
            }
            case "货车":{
                lastTime /= 60000;
                if(lastTime < 15){
                    fee = 0L;
                    return fee;
                }
                lastTime /= 60;
                if(lastTime < 1){
                    fee = 50L;
                    return fee;
                }else{
                    fee = 50L + 75 * (lastTime - 1);
                    return fee;
                }
            }
            default:
                return -1L;
        }

    }
}
