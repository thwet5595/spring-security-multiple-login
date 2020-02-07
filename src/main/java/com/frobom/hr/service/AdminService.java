package com.frobom.hr.service;

import java.util.List;

import com.frobom.hr.entity.Admin;

public interface AdminService {
    void save(Admin admin);

    Admin findByName(String name);

    Admin findById(int id);

    List<Admin> findAll();

    void delete(Admin admin);
}
