package com.example.pm.entity;

import javax.persistence.*;

@Table(name = "parking_system.parking_car")
public class ParkingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    private Long timestamp;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @param plate
     */
    public void setPlate(String plate) {
        this.plate = plate;
    }

    /**
     * @return timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}