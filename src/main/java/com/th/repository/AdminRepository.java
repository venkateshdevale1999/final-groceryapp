package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.th.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
