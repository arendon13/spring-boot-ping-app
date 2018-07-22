package com.ping.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_Pings")
public class Ping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ping")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ping;
	
	public Ping() {}
	
	public Ping(Date ping) {
		this.ping = ping;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPing() {
		return ping;
	}

	public void setPing(Date ping) {
		this.ping = ping;
	}

}
