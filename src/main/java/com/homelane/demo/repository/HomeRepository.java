package com.homelane.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homelane.demo.entity.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long>{

	List<Home> findByPriceLessThan(double price);
	
	Optional<Home>findByHouseId(long houseId);
	
//	public List<Home> findByPriceLessThan(double price);
}
