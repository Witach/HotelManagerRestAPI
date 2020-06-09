package com.example.demo.repository;

import com.example.demo.entity.Bill;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByTenantId(long id);

    @Query("SELECT date, COUNT(date) FROM Bill WHERE date > :fromDate GROUP BY date")
    List<List<Object>> getStatistics(LocalDate fromDate);

}
