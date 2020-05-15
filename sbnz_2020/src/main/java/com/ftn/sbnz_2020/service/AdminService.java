package com.ftn.sbnz_2020.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.sbnz_2020.facts.Admin;
import com.ftn.sbnz_2020.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
    AdminRepository adminRepository;

    public Admin findById(Long id) {
        return adminRepository.findById(id).get();
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Page<Admin> findAll(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    public Admin save(Admin admin) {
        //admin.setId(null);
        return adminRepository.save(admin);
    }

    public Admin update(Admin adminUpdate) {
        Admin admin = this.findById(adminUpdate.getId());
        admin.setFirstName(adminUpdate.getFirstName());
        admin.setLastName(adminUpdate.getLastName());
        return adminRepository.save(admin);
    }

    public void delete(Long id) {
        adminRepository.delete(this.findById(id));
    }

    public void deleteAll() {
        adminRepository.deleteAllInBatch();
    }
}
