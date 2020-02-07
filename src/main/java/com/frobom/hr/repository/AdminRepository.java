package com.frobom.hr.repository;

import java.util.List;

import com.frobom.hr.entity.Admin;

public interface AdminRepository {

    void save(Admin admin);
    
    Admin findById(int id);
    
    Admin findByName(String name);
    
    void delete(Admin admin);

    List<Admin> findAll();
}
