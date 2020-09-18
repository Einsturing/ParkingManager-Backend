package com.example.pm.service;

import com.example.pm.entity.ParkingCard;
import com.example.pm.entity.User;

import java.util.List;

public interface CardService {
    //public ParkingCard getCardById(Long id);
    public ParkingCard getCardByPlate(String plate);
    public List<ParkingCard> getAllCard();
    public void insertCard(ParkingCard card);
    //public void updateCard(ParkingCard card);
}
