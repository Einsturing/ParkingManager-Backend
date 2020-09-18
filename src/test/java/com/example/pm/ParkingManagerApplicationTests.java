package com.example.pm;

import com.example.pm.dao.ParkingCardMapper;
import com.example.pm.dao.UserMapper;
import com.example.pm.entity.ParkingCard;
import com.example.pm.entity.User;
import com.example.pm.service.CardService;
import com.example.pm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ParkingManagerApplication.class)
@Transactional
@Rollback
public class ParkingManagerApplicationTests {

    @Autowired
    private UserMapper userMapper;
    private ParkingCardMapper parkingCardMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectAll();
        for(User user : userList) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testLogin() {
        String username = "hzy";
        String password = "12345";
        System.out.println(userService.login(username, password));
    }
    @Test
    public  void testAddCard() {
        String plate1 = "dasdada";
        String plate2 = null;
        System.out.println(cardService.getCardByPlate(plate1));
        System.out.println(cardService.getCardByPlate(plate2));
    }
}
