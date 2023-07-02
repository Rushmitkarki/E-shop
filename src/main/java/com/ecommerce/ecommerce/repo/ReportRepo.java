package com.ecommerce.ecommerce.repo;


import com.ecommerce.ecommerce.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report,Integer> {
}
