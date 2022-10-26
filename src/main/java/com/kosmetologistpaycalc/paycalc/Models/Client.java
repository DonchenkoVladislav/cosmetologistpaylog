package com.kosmetologistpaycalc.paycalc.Models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;
    private String phoneNumber;
    private String comment;

    private String userName;

    public Client() {}

    public Client(String data, String phoneNumber, String comment, String userName) {
        this.data = data;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
