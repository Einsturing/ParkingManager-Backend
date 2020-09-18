package com.example.pm.controller;

import com.example.pm.commons.ConstantUtils;
import com.example.pm.commons.CookieUtils;
import com.example.pm.commons.MoneyUtils;
import com.example.pm.entity.ParkingCar;
import com.example.pm.entity.ParkingCard;
import com.example.pm.entity.ParkingInformation;
import com.example.pm.entity.User;
import com.example.pm.service.CardService;
import com.example.pm.service.InfoService;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class infoController {
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;
    @Autowired
    private InfoService infoService;

    @CrossOrigin
    @RequestMapping(value = "/information", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity parkingIn(@RequestBody ParkingCar ReqPkCar, String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            if (infoService.isCarExistByPlate(ReqPkCar.getPlate()) == false) {
                infoService.insertPkCar(ReqPkCar);
                ParkingCar pkCar = infoService.getPkCarByPlate(ReqPkCar.getPlate());
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(pkCar, HttpStatus.OK);
            }
            else {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"This car have existed\"}", HttpStatus.BAD_REQUEST);
            }
        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPkCar(String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            List<ParkingCar> pkCars = infoService.getAllPkCar();
            System.out.println(pkCars.size());
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity(pkCars, HttpStatus.OK);
        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getPkInfo(String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            List<ParkingInformation> pkInfos = infoService.getAllPkInfo();
           // System.out.println(pkInfo.size());
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity(pkInfos, HttpStatus.OK);
        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @RequestMapping(value = "/information", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity parkingOut(@RequestBody ParkingCar ReqPkCar, String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            if (infoService.isCarExistByPlate(ReqPkCar.getPlate()) == false) {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"This car hasn't existed\"}", HttpStatus.UNAUTHORIZED);
            }
            else {
                ParkingInformation pkInfo = new ParkingInformation();
                ParkingCar pkCar = infoService.getPkCarByPlate(ReqPkCar.getPlate());
                pkInfo.setPlate(ReqPkCar.getPlate());
                pkInfo.setEntrytime(pkCar.getTimestamp());
                pkInfo.setDeparturetime(ReqPkCar.getTimestamp());
                String tp = cardService.getCardByPlate(ReqPkCar.getPlate()).getType();
                Long Money = MoneyUtils.calcMoney(pkInfo.getEntrytime(), pkInfo.getDeparturetime(), tp);
                if(Money < 0L) {
                    httpServletResponse.setContentType("application/json");
                    return new ResponseEntity("{\"status\": \"The money is illegal\"}", HttpStatus.UNAUTHORIZED);
                }
                pkInfo.setFee(Money);
                infoService.insertInfo(pkInfo);
                infoService.deletePkCar(pkCar);
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(pkInfo, HttpStatus.OK);
            }
        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }
}
