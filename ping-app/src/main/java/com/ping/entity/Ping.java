package com.ping.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_Pings")
public class Ping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ping")
	private LocalDateTime ping;
	
	public Ping() {}
	
	public Ping(LocalDateTime ping) {
		this.ping = ping;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getPing() {
		return ping;
	}

	public void setPing(LocalDateTime ping) {
		this.ping = ping;
	}

}
