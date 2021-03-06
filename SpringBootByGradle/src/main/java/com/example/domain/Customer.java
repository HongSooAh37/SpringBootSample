package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idx;
	
	@Column(length=50)
	private String name;
	
	@Column(length=12)
	private String tel;
	
	private String bigo;
}
