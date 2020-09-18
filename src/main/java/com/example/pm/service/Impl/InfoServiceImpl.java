package com.example.pm.service.Impl;

import com.example.pm.dao.ParkingCarMapper;
import com.example.pm.dao.ParkingCardMapper;
import com.example.pm.dao.ParkingInformationMapper;
import com.example.pm.entity.ParkingCar;
import com.example.pm.entity.ParkingInformation;
import com.example.pm.entity.User;
import com.example.pm.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private ParkingCarMapper parkingCarMapper;
    @Autowired
    private ParkingInformationMapper parkingInformationMapper;

    @Override
    public boolean isCarExistByPlate(String plate) {
        Example example = new Example(ParkingCar.class);
        example.createCriteria().andEqualTo("plate", plate);
        ParkingCar pkCar = parkingCarMapper.selectOneByExample(example);
        if(pkCar == null) {
            System.out.println("this pkcar is null");
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void insertPkCar(ParkingCar pkCar) {
        System.out.println("insert: " + pkCar.getTimestamp());
        parkingCarMapper.insert(pkCar);
        System.out.println("insert: " + pkCar.getTimestamp());
    }

    @Override
    public ParkingCar getPkCarByPlate(String plate) {
        Example example = new Example(ParkingCar.class);
        example.createCriteria().andEqualTo("plate", plate);
        ParkingCar parkingCar = parkingCarMapper.selectOneByExample(example);
        if(parkingCar == null) {
            return null;
        }
        else {
            return parkingCar;
        }
    }

    @Override
    public void deletePkCar(ParkingCar pkCar) {
        parkingCarMapper.delete(pkCar);
    }

    @Override
    public List<ParkingCar> getAllPkCar() {
        Example example = new Example(ParkingCar.class);
        List<ParkingCar> pkCars = parkingCarMapper.selectAll();
        if(pkCars == null) {
            System.out.println("pkCars is null");
            return null;
        }
        else {
            return pkCars;
        }
    }

    @Override
    public List<ParkingInformation> getAllPkInfo() {
        Example example = new Example(ParkingInformation.class);
        List<ParkingInformation> pkInfos = parkingInformationMapper.selectAll();
        if(pkInfos == null) {
            System.out.println("pkInfos is null");
            return null;
        }
        else {
            return pkInfos;
        }
    }

    @Override
    public void insertInfo(ParkingInformation Info) {
        parkingInformationMapper.insert(Info);
    }
}
