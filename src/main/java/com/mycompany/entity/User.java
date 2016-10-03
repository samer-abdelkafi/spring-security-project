/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User  {

    public User() {
        super();
    }

    
    

    public User(Long userId, String name, String login, String password,
		boolean enabled, List<Authority> authorities) {
	super();
	this.userId = userId;
	this.name = name;
	this.login = login;
	this.password = password;
	this.enabled = enabled;
	this.authorities = authorities;
}




	@Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;
  
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    
    @OneToMany
    private List<Authority> authorities = new ArrayList<Authority>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public List<Authority> getAuthorities() {
		return authorities;
	}



	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(name);
		sb.append("[");
		for (Authority a : authorities){
			sb.append(a.getName());
			sb.append(" ,");
		}
		sb.append("]");
		return sb.toString().replace(" ,]", "]");
	}
	
   
}
