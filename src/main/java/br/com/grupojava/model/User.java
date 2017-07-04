package br.com.grupojava.model;


import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class User{
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Conta> contas;
	
	@Id
	@Column(name = "idUser")
	@GeneratedValue
	private Long id;
	
	@Column(name="Nome")
	private String name;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name="telefone")
	private String mobile; 
	
	public List<Conta> getContas() {
		return contas;
	}
	public void setContas(List<Conta> conta) {
		this.contas = conta;
	}
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

