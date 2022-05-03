package com.homelane.demo.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.homelane.demo.entity.Address;
import com.homelane.demo.entity.Home;
import com.homelane.demo.repository.AddressRepository;
import com.homelane.demo.repository.HomeRepository;

@org.springframework.stereotype.Service
public class HomeService {
	
	@Autowired
	private HomeRepository homeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	public List<Home> homesUnderBudget(double price)
	{
		return homeRepository.findByPriceLessThan(price);
	}
	
	public void importData()
	{
		List<List<String>> res = new ArrayList();
		try(BufferedReader fileReader
		        = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/data.csv"))))
		{ 
//			new FileReader("/data.csv")
		  String line = "";

		  while ((line = fileReader.readLine()) != null)
		  {
		    String[] tokens = line.split(",");
		    List<String>temp = new ArrayList();
		    for(String s:tokens)
		    {
		    	temp.add(s);
		    }
		    res.add(temp);
		  }
		  for(int j=1;j<res.size();++j)
		  {
			double price = Double.parseDouble(res.get(j).get(1));
			double bedrooms = Double.parseDouble(res.get(j).get(2));;
			double bathrooms = Double.parseDouble(res.get(j).get(3));;
			long sqft_living = Long.parseLong(res.get(j).get(4));
			long sqft_lot = Long.parseLong(res.get(j).get(5));;
			double floors = Double.parseDouble(res.get(j).get(6));
			int waterfront = Integer.parseInt(res.get(j).get(7));
			int view = Integer.parseInt(res.get(j).get(8));
			int condition = Integer.parseInt(res.get(j).get(9));
			long sqft_above = Long.parseLong(res.get(j).get(10));;
			long sqft_basement = Long.parseLong(res.get(j).get(11));;
			int yr_built = Integer.parseInt(res.get(j).get(12));
			int yr_renovated = Integer.parseInt(res.get(j).get(13));
			
			String street = res.get(j).get(14);
			String city = res.get(j).get(15);
			String statezip = res.get(j).get(16);
			String country = res.get(j).get(17);
			
			Address address = new Address();
			address.setCity(city);
			address.setCountry(country);
			address.setStatezip(statezip);
			address.setStreet(street);
			
			Home home = Home.builder().bathrooms(bathrooms)
					.bedrooms(bedrooms).condition(condition).floors(floors).price(price)
					.sqft_above(sqft_above).sqft_basement(sqft_basement).sqft_living(sqft_living)
					.sqft_lot(sqft_lot).view(view).waterfront(waterfront).yr_built(yr_built)
					.yr_renovated(yr_renovated)
					.address(address).build();
			
			Home h = homeRepository.save(home);
//			System.out.println(h);
		  }

		}
		catch (IOException e) {
		  e.printStackTrace();
		}
	}

	public Home create(Home home) {
		return homeRepository.save(home);
		}

	public List<Home> getAllHomes() {
		return homeRepository.findAll();
	}

	public void deleteHomeById(Long houseId) {
		
		Optional<Home> h = homeRepository.findByHouseId(houseId);
		if(h.isPresent())
			homeRepository.delete(h.get());
	}

	public Home updateHomeById(Long houseId, Home home) {
		Optional<Home> h = homeRepository.findByHouseId(houseId);
		if(h.isPresent())
		{
			if(h.get().getHouseId() == home.getHouseId())
				homeRepository.save(home);
		}
		return h.get();
	}

	public Optional<Home> fetchHomeById(Long houseId) {
		return homeRepository.findByHouseId(houseId);
	}

}
