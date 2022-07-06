package com.rest.all_apis.user_api;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/api")
public class UserURIMapping {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserResource userResource;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> getAllUsers(){
		return userResource.getAll();
	} 
	
	@GetMapping("/usersagefiltered")  // UsersAgeFiltered
	public MappingJacksonValue getAllUsersFiltered(){		

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "id","place");

		FilterProvider filters = new SimpleFilterProvider().addFilter("ageFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(userResource.getAll());

		mapping.setFilters(filters);

		return mapping;
	} 
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
		User createdUser =  userResource.addUser(user);
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(createdUser.getId()).
			toUri();
		return ResponseEntity.created(location).build();
	}
	

	
	
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		return userResource.deleteUser(id);
	}
	
	@GetMapping("/users/{id}")
	public User findUser(@PathVariable int id) {
		return userResource.findUser(id);
	}
	
	
	//	Internationalisation
	@GetMapping("/users/sayHello")
	public String sayHello() {
		return messageSource.getMessage("good.morning.message",null,LocaleContextHolder.getLocale());
	}
	
}
