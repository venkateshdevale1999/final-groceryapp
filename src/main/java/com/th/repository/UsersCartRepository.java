package com.th.repository;



import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import com.th.model.Groceries;
import com.th.model.Userscartitems1;



@Repository
public interface UsersCartRepository extends JpaRepository<Userscartitems1,Integer> {

@Query(value = "select * from userscartitems1 where useremail=:email", nativeQuery = true)
List<Userscartitems1> findByUseremail(@Param("email") String email);


@Modifying(clearAutomatically = true)
@Query(value = "delete from userscartitems1 where useremail=:email", nativeQuery = true)
void deletecart(@Param("email") String email);



}