package com.DBproject.DBproject.controller.dto;

import lombok.Getter;
import lombok.Setter;
    @Setter
    @Getter
    public class SumCostDto {
        private Long project_cost;

        public SumCostDto(Long id){
            this.project_cost=id;
        }
    }


