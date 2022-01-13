package com.th.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.th.model.MyOrder;



public interface MyOrderRepository extends JpaRepository<MyOrder,String>{

	@Query(value = "select * from my_order where useremail=:userEmail", nativeQuery = true)
	List<MyOrder> getMyOrderByEmail(@Param("userEmail") String email);

}
