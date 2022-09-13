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

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(nativeQuery = true, value= "SELECT tb_seller.name, SUM(tb_sales.amount) "
            + "FROM tb_seller "
            + "INNER JOIN tb_sales ON tb_seller.id = tb_sales.seller_id "
            + "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "
            + "GROUP BY tb_seller.name"
    )
    Page<SalesSummaryProjection> summary(String minDate, String maxDate, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date , tb_sales.amount, tb_seller.name " +
            "FROM tb_seller " +
            "INNER JOIN tb_sales ON tb_seller.id = tb_sales.seller_id " +
            "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :namePart, '%'))"
    )
    Page<SalesReportProjection> report(String minDate, String maxDate, String namePart, Pageable pageable);

}
