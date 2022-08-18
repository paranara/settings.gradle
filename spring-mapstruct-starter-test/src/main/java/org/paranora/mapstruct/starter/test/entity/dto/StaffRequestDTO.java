package org.paranora.mapstruct.starter.test.entity.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class StaffRequestDTO {
    private String staffName;
    private Integer staffAge;
    private Integer staffSex;
    private String birth;
    private CompanyRequestDTO staffCompany;
}
