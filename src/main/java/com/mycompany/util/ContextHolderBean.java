package com.mycompany.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextHolderBean {

	private Authentication currentUser;

	public ContextHolderBean() {
		SecurityContext secCTX = SecurityContextHolder.getContext();
		currentUser = secCTX.getAuthentication();
	}

	public Authentication getCurrentUser() {
		return currentUser;
	}

}