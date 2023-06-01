package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Buken;

/**
 * ユーザー情報 Repository
 */
@Repository
public interface BukenRepository extends JpaRepository<Buken, Long> {
	List<Buken> findByPropertyId(Long propertyId);
	List<Buken> findByStatusContainingOrderByPropertyId(String status);
	List<Buken> findByPeriodBetweenOrderByPeriod(Date fromdate, Date todate);
}
