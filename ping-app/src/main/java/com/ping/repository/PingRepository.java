package com.ping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ping.entity.Ping;

@Repository
public interface PingRepository extends JpaRepository<Ping, Integer> {
	
	/**
	 * This was created in order to avoid creating a custom method in PingServiceImpl.java to do what this Query can
	 * @return List of Pings within the last hour
	 */
	@Query(value = "SELECT * FROM tbl_Pings WHERE ping >= DATE_SUB(NOW(),INTERVAL 1 HOUR)", nativeQuery = true)
	List<Ping> findAllWithinHour();

}
