package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}


	@GetMapping(value = "/summary")
	public ResponseEntity<Page<SalesSummaryDTO>> getSummary(
			@RequestParam(name = "minDate", defaultValue = "minDate")  String minDate,
			@RequestParam(name = "maxDate", defaultValue = "maxDate") String maxDate,
			Pageable pageable){

			Page<SalesSummaryDTO> dto = service.summary(minDate, maxDate, pageable);

			return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(
			@RequestParam(name = "minDate", defaultValue = "minDate")  String minDate,
			@RequestParam(name = "maxDate", defaultValue = "maxDate") String maxDate,
			@RequestParam(name= "name", defaultValue = " ") String namePart,
			Pageable pageable){

		Page<SalesReportDTO> dto = service.getReport(minDate, maxDate, namePart, pageable);

		return ResponseEntity.ok(dto);
	}
}
