package com.ping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ping.entity.Ping;

@Repository
public interface PingRepository extends JpaRepository<Ping, Integer> {

}
