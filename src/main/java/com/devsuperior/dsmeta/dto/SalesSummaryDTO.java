package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

public class SalesSummaryDTO {

       private String name;
       private Double total;


    public SalesSummaryDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public SalesSummaryDTO(SalesSummaryProjection projection){
        name = projection.getName();
        total = projection.getTotal();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "SalesSummaryDTO{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
