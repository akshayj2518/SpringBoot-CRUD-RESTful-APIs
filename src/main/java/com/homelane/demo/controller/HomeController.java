package com.homelane.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homelane.demo.entity.Home;
import com.homelane.demo.exception.HomeNotFoundException;
import com.homelane.demo.service.HomeService;

@RestController
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


	@Autowired
	private HomeService homeService;
	
	@PostMapping("/home/save")
	public Home createHouse(@RequestBody Home home) {
		
		LOGGER.info("inside createHouse of HomeController");
		return homeService.create(home);
	}
	
	@GetMapping("/home/fetch")
	public List<Home> allHomes()
	{
		LOGGER.info("inside allHomes of HomeController");
		return homeService.getAllHomes();
	}
	
	@GetMapping("/home/fetch/{id}")
	public Home fetchHomeById(@PathVariable("id") Long houseId) throws HomeNotFoundException
	{
		LOGGER.info("inside fetchHomeById of HomeController");
		
		Optional<Home> home = homeService.fetchHomeById(houseId);
		if(!home.isPresent())
			throw new HomeNotFoundException("house Id not found");
		else
			return home.get();
	}
	

	@DeleteMapping("/home/delete/{id}")
	public String deleteHomeById(@PathVariable("id") Long houseId)
	{
		LOGGER.info("inside deleteHomeById of HomeController");
		
		homeService.deleteHomeById(houseId);
		return "Home deleted successfully.";
	}
	
	@PutMapping("/home/update/{id}")
	public Home deleteHomeById(@PathVariable("id") Long houseId,@RequestBody Home home)
	{
		return homeService.updateHomeById(houseId,home);
	}
	
	@GetMapping("/home/budgethomes/{price}")
	public List<Home> homesUnderBudget(@PathVariable("price") double prices)
	{
		LOGGER.info("inside homesUnderBudget of HomeController");
		return homeService.homesUnderBudget(prices);
	}
}
