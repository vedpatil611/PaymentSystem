package com.barclays.paymentsystem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="users")
public class User {
	@Id
	private Integer id;
	String username;
	String password;
	private String name;
	@OneToOne
	@JoinColumn(name = "sequence_id", unique = true)
	Account account;
	@ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="user_role",
       joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
       inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	   public List<Role> getRoles()
	    {
	        return roles;
	    }
	    public void setRoles(List<Role> roles)
	    {
	        this.roles = roles;
	    }
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}

}
