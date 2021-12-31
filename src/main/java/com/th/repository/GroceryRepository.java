package com.th.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.th.model.Groceries;

@Repository
public interface GroceryRepository extends JpaRepository<Groceries, Integer> {	
	
	@Query(value="select * from Groceries where categories='fruit'",nativeQuery=true)
    List<Groceries> fruittable(String chars);
	
	@Query(value="select * from Groceries where categories='vegetable'",nativeQuery=true)
    List<Groceries> vegetabletable(String chars);
	
	@Query(value="select * from Groceries where categories='meat'",nativeQuery=true)
    List<Groceries> meattable(String chars);
	
	@Query(value="select * from Groceries where categories='dailyneeds'",nativeQuery=true)
    List<Groceries> dailytable(String chars);

	
	
	

}