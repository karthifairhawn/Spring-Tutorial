package com.rest.all_apis.user_api;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserResource {
	static List<User> allUsers = new LinkedList<>();
	static Integer usersCount = 3;
	
	static {
		allUsers.add(new User(1,"Karthi",20,"Kadayanallur"));
		allUsers.add(new User(2,"Kumar",29,"Tenkasi"));
		allUsers.add(new User(3,"Joet",18,"Chennai"));		
	}
	
	
	public List<User> getAll(){
		return allUsers;
	}
	
	public User addUser(User user) {
		user.setId(++usersCount);
		allUsers.add(user);
		return user;
	}
	
	public User deleteUser(int id){
		Iterator<User> itr = allUsers.iterator();
		
		while(itr.hasNext()) {
			User x = itr.next();
			if(x.getId()==id) {
				itr.remove();
				return x;				
			}
		}
		
		
		throw new UserNotfoundException("User Not Found id:"+id);
	}
	
	public User findUser(int id) {
		Iterator<User> itr = allUsers.iterator();
		
		while(itr.hasNext()) {
			User x = itr.next();
			if(x.getId()==id) return x;					
		}
		
		throw new UserNotfoundException("User Not Found id:"+id);
	}
}
