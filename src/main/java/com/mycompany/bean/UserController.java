package com.mycompany.bean;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mycompany.entity.Authority;
import com.mycompany.entity.User;

public class UserController {
	
   private User currentUser = null;

    public User getCurrentUser(){
    	if (currentUser == null) {
    		currentUser = new User();
	    	currentUser.setName(SecurityContextHolder.getContext().getAuthentication().getName());
	        List<GrantedAuthority> gaList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	        for (GrantedAuthority ga : gaList) {
	        	currentUser.getAuthorities().add(new Authority(null, ga.getAuthority(), currentUser));
	        }
    	}
        return currentUser;
    }
    
    

}
