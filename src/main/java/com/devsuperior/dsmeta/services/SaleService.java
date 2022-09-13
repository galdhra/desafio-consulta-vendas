package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.projections.SalesReportProjection;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	private LocalDate date1 = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SalesReportDTO> getReport (String minDate, String maxDate, String namePart, Pageable pageable){
		Page<SalesReportProjection> result = repository.report(initialDate(), finalDate(), namePart, pageable);
		return result.map(x-> new SalesReportDTO(x));
	}
	public Page<SalesSummaryDTO> summary (String minDate, String maxDate, Pageable pageable){
			Page<SalesSummaryProjection> result = repository.summary(initialDate(), finalDate(), pageable);
		return result.map(x-> new SalesSummaryDTO(x));
	}

	public String initialDate (){
		return date1.toString();
	}

	public String finalDate (){
		LocalDate date2 = date1.minusYears(1L);
		return date2.toString();
	}
}
