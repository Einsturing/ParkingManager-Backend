package com.example.pm.entity;

import javax.persistence.*;

@Table(name = "parking_system.parking_information")
public class ParkingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate;

    private Long departuretime;

    private Long fee;

    private Long entrytime;

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
     * @return departuretime
     */
    public Long getDeparturetime() {
        return departuretime;
    }

    /**
     * @param departuretime
     */
    public void setDeparturetime(Long departuretime) {
        this.departuretime = departuretime;
    }

    /**
     * @return fee
     */
    public Long getFee() {
        return fee;
    }

    /**
     * @param fee
     */
    public void setFee(Long fee) {
        this.fee = fee;
    }

    /**
     * @return entrytime
     */
    public Long getEntrytime() {
        return entrytime;
    }

    /**
     * @param entrytime
     */
    public void setEntrytime(Long entrytime) {
        this.entrytime = entrytime;
    }
}