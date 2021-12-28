package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.th.model.Groceries;

@Repository
public interface GroceryRepository extends JpaRepository<Groceries, Integer> {	

}