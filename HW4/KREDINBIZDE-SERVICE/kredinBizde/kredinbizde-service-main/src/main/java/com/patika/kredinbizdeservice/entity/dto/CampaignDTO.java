package com.patika.kredinbizdeservice.entity.dto;

import com.patika.kredinbizdeservice.entity.CreditCard;
import com.patika.kredinbizdeservice.enums.SectorType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CampaignDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate createDate;
    private LocalDate updateDate;
    private SectorType sector;
    private Long creditCardId;
}
