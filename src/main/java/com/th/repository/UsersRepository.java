package com.th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.th.model.Users;

public interface UsersRepository extends JpaRepository<Users,String> {
	
	@Query(value = "select * from users where useremail=:userEmail", nativeQuery = true)
	Users getUesrRec(@Param("userEmail") String email);

	

}
