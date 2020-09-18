package com.example.pm.controller;


import com.example.pm.commons.ConstantUtils;
import com.example.pm.commons.CookieUtils;
import com.example.pm.entity.ParkingCard;
import com.example.pm.entity.User;
import com.example.pm.service.CardService;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/card")
@EnableAutoConfiguration
public class cardController {
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    //add card
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addCard(@RequestBody ParkingCard Reqcard, String cookie, HttpServletResponse httpServletResponse) {
        //System.out.println(cookie);
        if(CookieUtils.CookieConfirm((cookie))) {
            User admin = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
//            System.out.println(admin.getUsername());
//            System.out.println(admin.getPassword());
//            System.out.println(admin.getType());
            if(cardService.getCardByPlate(Reqcard.getPlate()) == null) {
                //System.out.println("in");
                cardService.insertCard(Reqcard);
                ParkingCard card = cardService.getCardByPlate(Reqcard.getPlate());
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(card, HttpStatus.OK);
            }
            else {
                if(Reqcard.getRegister() == false) {
                    ParkingCard card = cardService.getCardByPlate(Reqcard.getPlate());
                    httpServletResponse.setContentType("application/json");
                    return new ResponseEntity(card, HttpStatus.OK);
                }
                else {
                    httpServletResponse.setContentType("application/json");
                    return new ResponseEntity("{\"status\": \"This card have existed\"}", HttpStatus.UNAUTHORIZED);
                }
            }
//            if(admin.getType().equals("admin") ) {
//
//            }
//            else {
//                httpServletResponse.setContentType("application/json");
//                return new ResponseEntity("{\"status\": \"No Permission\"}", HttpStatus.UNAUTHORIZED);
//            }

        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCards(String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            User admin = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
            List<ParkingCard> cards = cardService.getAllCard();
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity(cards, HttpStatus.OK);
//            if(admin.getType().equals("admin") ) {
//
//            }
//            else {
//                httpServletResponse.setContentType("application/json");
//                return new ResponseEntity("{\"status\": \"No Permission\"}", HttpStatus.UNAUTHORIZED);
//            }

        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }
}
