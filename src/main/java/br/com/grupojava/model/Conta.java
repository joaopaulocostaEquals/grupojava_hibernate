package br.com.grupojava.model;

import javax.persistence.*;

@Entity
public class Conta {
	
	@ManyToOne()
	private User usuario;
	
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	@Id
	@GeneratedValue
	private Long id;
	
	private Double saldo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}
