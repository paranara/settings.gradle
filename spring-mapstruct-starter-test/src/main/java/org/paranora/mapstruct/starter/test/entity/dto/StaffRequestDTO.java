package org.paranora.mapstruct.starter.test.entity.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequestDTO {
    private String staffName;
    private Integer staffAge;
    private Integer staffSex;
    private String birth;
    private CompanyRequestDTO staffCompany;
}
