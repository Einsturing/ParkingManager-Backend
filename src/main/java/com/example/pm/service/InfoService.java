package com.example.pm.service;

import com.example.pm.entity.ParkingCar;
import com.example.pm.entity.ParkingInformation;

import java.util.List;

public interface InfoService {
    public boolean isCarExistByPlate(String plate);
    public void insertPkCar(ParkingCar PkCar);
    public ParkingCar getPkCarByPlate(String plate);
    public void deletePkCar(ParkingCar pkCar);
    public List<ParkingCar> getAllPkCar();
    public List<ParkingInformation> getAllPkInfo();
    public void insertInfo(ParkingInformation Info);
//    public  ParkingInformation getPkInfoBy
}
