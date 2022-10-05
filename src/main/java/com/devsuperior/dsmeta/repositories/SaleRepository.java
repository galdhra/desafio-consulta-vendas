package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT obj.seller.name, SUM(obj.amount) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "GROUP BY obj.seller.name"
    )
    Page<SalesSummaryProjection> summary(LocalDate minDate, LocalDate maxDate, Pageable pageable);

    @Query("SELECT obj.id, obj.date, obj.amount, obj.seller.name "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :namePart, '%'))"
    )
    Page<SalesReportProjection> report(LocalDate minDate, LocalDate maxDate, String namePart, Pageable pageable);




}
