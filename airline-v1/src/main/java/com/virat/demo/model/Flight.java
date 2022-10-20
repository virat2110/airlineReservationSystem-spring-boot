package com.virat.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="flight")
public class Flight {
	
	@Id
	@Column(name="flightid")
	private int flightId;
	@Column(name="source")
	private String source;
	@Column(name="dest")
	private String dest;
	@Column(name="price")
	private int price;
	@Column(name="arrival")
	private String arrival;
	@Column(name="departure")
	private String departure;
	@Column(name="delay")
	private String delay;
	@Column(name="status")
	private String status;
	

}
