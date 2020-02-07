package com.frobom.hr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frobom.hr.entity.Admin;
import com.frobom.hr.repository.AdminRepository;
import com.frobom.hr.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public void save(Admin admin) {
        this.adminRepository.save(admin);        
    }

    @Override
    @Transactional
    public Admin findByName(String name) {
        return this.adminRepository.findByName(name);
    }

    @Override
    @Transactional
    public Admin findById(int id) {
        return this.adminRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Admin> findAll() {
        return this.adminRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Admin admin) {
        this.adminRepository.delete(admin);
    }

}
