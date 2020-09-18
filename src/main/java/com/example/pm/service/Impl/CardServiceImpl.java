package com.example.pm.service.Impl;


import com.example.pm.dao.ParkingCardMapper;
import com.example.pm.dao.UserMapper;
import com.example.pm.entity.ParkingCard;
import com.example.pm.entity.User;
import com.example.pm.service.CardService;
import com.example.pm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private ParkingCardMapper parkingCardMapper;

    @Override
    public ParkingCard getCardByPlate(String plate) {
        Example example = new Example(ParkingCard.class);
        example.createCriteria().andEqualTo("plate", plate);
        ParkingCard card = parkingCardMapper.selectOneByExample(example);
        if(card == null) {
            return null;
        }
        else {
            return card;
        }
    }

    @Override
    public List<ParkingCard> getAllCard() {
        Example example = new Example(ParkingCard.class);
        List<ParkingCard> cards = parkingCardMapper.selectAll();
        if(cards == null) {
            return null;
        }
        else {
            return cards;
        }
    }

    @Override
    public void insertCard(ParkingCard card) {
        parkingCardMapper.insert(card);
    }
}
