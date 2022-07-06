# Spring Boot Notes

### Important Annotions

**@SpringBootApplication** - Starting Point of Spring Boot

**@RestController** - This makes a particular class a RestController all mapping and those controls goes here.

**@Controller** - If we want something need to be autowired then it must be controller then only spring boot will see that particular class as component

**@GetMapping("_URI_")** This annotion will make a method to run on that particular mentioned URI. The return value of the method will be the content on that URI given.
	
		@GetMapping("/users")
		public List<User> getAllUsers(){ ... } 

**@PostMapping("_URI_")** The URI mentioned can recieve post request and we can use the data of the body send using **@RequestBody** annotation in parameter

**@RequestBody** This annotation will be used in paramter of function so we can get value from post req.
	
	@PostMapping("/users")
		public User addUser(@RequestBody User user){ ... }


**@PathVariable** This is used in parameter to get value passed in URI 

		@GetMapping("/users/{id}")
		public User findUser(@PathVariable int id) { ...  }

**@ResponseStatus(HttpStatus._Status_)**  Used to set HTTP status code of that particular method. In the below case 404 will be thrown if it encounters an exception.

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public class UserNotfoundException extends RuntimeException {
		public UserNotfoundException(String message) {
			super(message);
		}	
	}
	
**@RestControllerAdvice** This annotation is specialization of @Component annotation so that it is auto-detected via classpath scanning. It is a kind of interceptor that surrounds the logic in our Controllers and allows us to apply some common logic to them

**@ExceptionHandler** This annotation is used to annotate the method(s) in the controller class for handling the exceptions raised during the execution of the controller methods, The below class will be fired once any exception occurs in entire spring boot application
	
	@RestController
	@RestControllerAdvice
	public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
		@ExceptionHandler(Exception.class)
		public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
			ExceptionResponse er = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
			return new ResponseEntity(er,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
			
**@JsonIgnore** Enable Static Filtering in variable which is under the annotation  	



###Important Notes


####Created Status Code(201) and URI Builder Sample

	PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody User user){
		User createdUser =  userResource.addUser(user);
		URI location = ServletUriComponentsBuilder.
			fromCurrentRequest().
			path("/{id}").
			buildAndExpand(createdUser.getId()).
			toUri();
		return ResponseEntity.created(location).build();
	}

###Validating post values

	>> In Mapping add @Valid in param>>   
		public ResponseEntity<Object> addUser(@Valid @RequestBody User user){	...		}
		
	>> In Structure >>
		@Size(min=2)     // This annotation expects name with atleast two chars
		String name;
							
							
###Internationalisation	Example

In application.properties:
	spring.messages.basename=messages
	
Create Files:
	1.message_fr.proeprties               << Contains  >>>> Good.morning.message=Good Morning French
	2.message.proeprties                  << Contains  >>>> Good.morning.message=Good Morning 
	
At Sping Starting Java File (@SpringBootApplication)
	>> The Below Function says by default take US as Locale
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}		
	
###XML Support for REST Service

Just Add this Dependency. Then it will support XML negotiation

	<dependency>
	       	<groupId>com.fasterxml.jackson.dataformat</groupId>
	       	<artifactId>jackson-dataformat-xml</artifactId>
	</dependency>
	
###Open API 3.0 Documentation

The below dependency will take care of **/swagger-ui.html** and **/v3/api-docs**

	<dependency>
      		<groupId>org.springdoc</groupId>
      		<artifactId>springdoc-openapi-ui</artifactId>
      		<version>1.6.0</version>
   	</dependency>

###Actuator

The actuator service will be available on **/actuator** 

	<dependency>
        	<groupId>org.springframework.boot</groupId>
        	<artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>   	
    
###Hall Browser

	<dependency>
    		<groupId>org.springframework.data</groupId>
    		<artifactId>spring-data-rest-hal-browser</artifactId>		
    		<version>3.3.9.RELEASE</version>
	</dependency>

**/actuator** will give us a format that hal browser understands so using /actuator will be very easy using  **/browser** which is hal browser

###Dynamic Filtering

**In Mapping**

	@GetMapping("/usersagefiltered")  // UsersAgeFiltered
	public MappingJacksonValue getAllUsersFiltered(){		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "id","place");
		FilterProvider filters = new SimpleFilterProvider().addFilter("ageFilter", filter); 
				 // add @JsonFilter("ageFilter") in Class user(Object Structure)
		MappingJacksonValue mapping = new MappingJacksonValue(userResource.getAll());
		mapping.setFilters(filters);
		return mapping;
	} 
	
**In Object Sturcture**
	
	@JsonFilter("ageFilter")
	
	// Add this above class definition

###Spring Boot Security

Just add below dependency to enable security

	<dependency>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-security</artifactId>
	</dependency>
	
This genereates a random password that will be shown on startup of the application and **default user name is user**

To set custom username and password in **application.properties**

	spring.security.user.name=username
	spring.security.user.password=password




 

	
	

