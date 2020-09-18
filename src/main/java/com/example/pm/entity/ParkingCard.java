package com.example.pm.entity;

import javax.persistence.*;

@Table(name = "parking_system.parking_card")
public class ParkingCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    private String type;

    private String phone;

    private Boolean register;

    private String name;

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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return register
     */
    public Boolean getRegister() {
        return register;
    }

    /**
     * @param register
     */
    public void setRegister(Boolean register) {
        this.register = register;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}