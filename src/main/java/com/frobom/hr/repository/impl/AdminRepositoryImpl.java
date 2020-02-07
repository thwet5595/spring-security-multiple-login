package com.frobom.hr.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.frobom.hr.entity.Admin;
import com.frobom.hr.repository.AdminRepository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Admin admin) {
        if (admin.getId() == null) {
            this.entityManager.persist(admin);
        } else {
            this.entityManager.merge(admin);
        }
    }

    @Override
    public Admin findById(int id) {
        return this.entityManager.find(Admin.class, id);
    }

    @Override
    public Admin findByName(String name) {
        Query query = this.entityManager.createQuery("SELECT u from Admin u WHERE u.name = ?", Admin.class);
        query.setParameter(1, name);
        Admin user = null;
        try {
            user = (Admin) query.getSingleResult();
        } catch (Exception e) {
            // to log
        }
        return user;
    }

    @Override
    public void delete(Admin admin) {
        this.entityManager.remove(admin);
    }

    @Override
    public List<Admin> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
