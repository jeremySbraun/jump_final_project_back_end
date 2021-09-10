package com.cognixia.jump.springcloud.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.springcloud.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

	List<UserModel> findByUsername(String username);
	


}
