package com.example.pm.controller;

import com.example.pm.commons.ConstantUtils;
import com.example.pm.commons.CookieUtils;
import com.example.pm.commons.DateUtils;
import com.example.pm.commons.EncryptUtils;
import com.example.pm.entity.User;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@EnableAutoConfiguration
public class LoginController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity login(String cookie, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Long userid = null;
        if(CookieUtils.CookieConfirm(cookie)) {
            if(ConstantUtils.userLoginMap.get(cookie) == null) {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.BAD_REQUEST);
            }
            else {
                userid = ConstantUtils.userLoginMap.get(cookie);
                User user = userService.getUserById(userid);
                user.setPassword(null);
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(user, HttpStatus.OK);
            }
        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody User ReqUser, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //System.out.println(ReqUser.getUsername());
       // System.out.println(ReqUser.getPassword());
        Object user = userService.login(ReqUser.getUsername(), ReqUser.getPassword());
        if(user == null) {
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity("User not exits!", HttpStatus.BAD_REQUEST);
        }
        else if(user.equals("Wrong password!")){
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity("{\"status\": \"Wrong password!\"}", HttpStatus.BAD_REQUEST);
        }
        else {
            User loginUser = (User) user;
            String cookie = new EncryptUtils().DESencode(loginUser.getUsername() + ":" + DateUtils.getStringDate(), "Salt");
            ConstantUtils.userLoginMap.put(cookie, ((User) user).getId());
            Map<String, Object> mp = new HashMap<String, Object>();
            ((User) user).setPassword(null);
            mp.put("userInfo", user);
            mp.put("cookieID", cookie);
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity(mp, HttpStatus.OK);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity info(String cookie, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Long userid = null;
        if(CookieUtils.CookieConfirm(cookie)) {
            if(ConstantUtils.userLoginMap.get(cookie) == null) {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"User not exits!\"}", HttpStatus.BAD_REQUEST);
            }
            else {
                userid = ConstantUtils.userLoginMap.get(cookie);
                User user = userService.getUserById(userid);
                user.setPassword(null);
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(user, HttpStatus.OK);
            }
        }
        else {
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity("{\"status\": \"Cookie not exits!\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity logout(String cookie, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        System.out.println(cookie);
        if (ConstantUtils.userLoginMap.get(cookie) == null) {
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity("{\"status\": \"Cookie not Exit!\"}", HttpStatus.BAD_REQUEST);
        }
        else {
            ConstantUtils.userLoginMap.remove(cookie);
        }

        //httpServletResponse.setContentType("text/plain");
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"ok\"}", HttpStatus.OK);
    }
}
