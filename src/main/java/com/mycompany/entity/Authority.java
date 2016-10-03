package com.mycompany.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "authority")
public class Authority  {
	
	public Authority(Long authorityId, String name, User user) {
		super();
		this.authorityId = authorityId;
		this.name = name;
		this.user = user;
	}

	public Authority() {
        super();
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "authority_id", nullable = false)
    private Long authorityId;

    @Column(name = "authority_name", nullable = false)
    private String name;
  
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
 
}

