package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
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


	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	public Page<SalesSummaryDTO> getSummary (String minDate, String maxDate, Pageable pageable){
			LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate lastYear = today.minusYears(1L);

			LocalDate min = minDate.equals("") ? lastYear : LocalDate.parse(minDate);
			LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

			Page<SalesSummaryProjection> result = repository.summary(min, max, pageable);

		return result.map(x-> new SalesSummaryDTO(x));
	}

	public Page<SalesReportDTO> getReport (String minDate, String maxDate, String namePart, Pageable pageable){

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate lastYear = today.minusYears(1L);

		LocalDate min = minDate.equals("") ? lastYear : LocalDate.parse(minDate);
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

		Page<SalesReportProjection> result = repository.report(min, max, namePart, pageable);

		return result.map(x-> new SalesReportDTO(x));
	}


	}

