package com.patika.kredinbizdeservice.entity;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.VehicleStatuType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_loans")
public class VehicleLoan extends Loan {

    @Column(name = "loanType", nullable = false)
    private LoanType loanType = LoanType.ARAC_KREDISI;
    @Column(name = "vehicle_statu_type", nullable = false)
    private VehicleStatuType vehicleStatuType;
    @Column(name = "vehicle_information", nullable = false)
    private String vehicleInformation;

}
