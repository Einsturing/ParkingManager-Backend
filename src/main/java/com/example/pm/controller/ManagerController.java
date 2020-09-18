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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping(value = "/manage")
@EnableAutoConfiguration
public class ManagerController {
    @Autowired
    private UserService userService;

    //add normal
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(@RequestBody User user, String cookie, HttpServletResponse httpServletResponse) {
        //System.out.println(cookie);
        if(CookieUtils.CookieConfirm((cookie))) {
            User admin = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
//            System.out.println(admin.getUsername());
//            System.out.println(admin.getPassword());
//            System.out.println(admin.getType());
            if(admin.getType().equals("admin") ) {
//                System.out.println(user.getUsername());
//                System.out.println(user.getPassword());
                if(userService.getUserByUsername(user.getUsername()) == null) {
                    user.setType("normal");
                    userService.insertUser(user);
                    User user2 = userService.getUserByUsername(user.getUsername());
                    httpServletResponse.setContentType("application/json");
                    return new ResponseEntity(user2, HttpStatus.OK);
                }
                else {
                    httpServletResponse.setContentType("application/json");
                    return new ResponseEntity("{\"status\": \"This username have existed\"}", HttpStatus.UNAUTHORIZED);
                }
            }
            else {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"No Permission\"}", HttpStatus.UNAUTHORIZED);
            }

        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }

    //delete normal
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public ResponseEntity deleteBook(@PathVariable("id") Long id, String cookie, HttpServletResponse httpServletResponse) {
//        if(CookieUtils.CookieConfirm(cookie)) {
//            User admin = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
//            if(admin.getType() == "admin") {
//                User user = (User) userService.getUserById(id);
//                if(user.getType() == "admin") {
//                    httpServletResponse.setContentType("text/plain");
//                    return new ResponseEntity("Can't delete admin", HttpStatus.UNAUTHORIZED);
//                }
//                else {
//                    userService.deleteUser(user);
//                    httpServletResponse.setContentType("application/json");
//                    return new ResponseEntity(user, HttpStatus.OK);
//                }
//            }
//            else {
//                httpServletResponse.setContentType("text/plain");
//                return new ResponseEntity("No Permission", HttpStatus.UNAUTHORIZED);
//            }
//        }
//        httpServletResponse.setContentType("text/plain");
//        return new ResponseEntity("Please login!", HttpStatus.UNAUTHORIZED);
//    }

    //get all user;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUser(String cookie, HttpServletResponse httpServletResponse) {
        if(CookieUtils.CookieConfirm((cookie))) {
            User admin = userService.getUserById(ConstantUtils.userLoginMap.get(cookie));
            if(admin.getType().equals("admin") ) {
                List<User> users = userService.getAllUser();
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity(users, HttpStatus.OK);
            }
            else {
                httpServletResponse.setContentType("application/json");
                return new ResponseEntity("{\"status\": \"No Permission\"}", HttpStatus.UNAUTHORIZED);
            }

        }
        httpServletResponse.setContentType("application/json");
        return new ResponseEntity("{\"status\": \"Please login!\"}", HttpStatus.UNAUTHORIZED);
    }
}
