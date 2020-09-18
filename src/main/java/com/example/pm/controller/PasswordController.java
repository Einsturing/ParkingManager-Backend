package com.example.pm.controller;


import com.example.pm.commons.ConstantUtils;
import com.example.pm.commons.CookieUtils;
import com.example.pm.entity.User;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/password")
@EnableAutoConfiguration
public class PasswordController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updatePassword(@RequestBody User Requser, String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            User now_user = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
            System.out.println("PassworController 27: " + now_user.getType());
            if(userService.getUserByUsername(Requser.getUsername()) == null) {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"This username doesn't exist\"}", HttpStatus.UNAUTHORIZED);
            }
            if(now_user.getType().equals("admin") || Requser.getUsername().equals(now_user.getUsername())) {
                System.out.println("PassworController 32: " + now_user.getType());
                User user = userService.getUserByUsername(Requser.getUsername());
                user.setPassword(Requser.getPassword());
                userService.updateUser(user);
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(user, HttpStatus.OK);
            }
            else {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"No Permission\"}", HttpStatus.UNAUTHORIZED);
            }
        }
        else {
            httpServletResponse.setContentType("application/json");
            return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
        }
    }
}
