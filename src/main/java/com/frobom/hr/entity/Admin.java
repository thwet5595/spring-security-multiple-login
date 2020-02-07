package com.frobom.hr.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Admin extends UniqueNamedEntity {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
