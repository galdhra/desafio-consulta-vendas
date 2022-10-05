package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.LocalDate;
import java.util.List;

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
			@RequestParam(name = "minDate", defaultValue = "")  String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
			Pageable pageable){

			Page<SalesSummaryDTO> dto = service.getSummary(minDate, maxDate, pageable);

			return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SalesReportDTO>> getReport(
			@RequestParam(name = "minDate", defaultValue = "")  String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(name= "name", defaultValue = "") String namePart,
			Pageable pageable){

		Page<SalesReportDTO> dto = service.getReport(minDate, maxDate, namePart, pageable);

		return ResponseEntity.ok(dto);
	}


}
