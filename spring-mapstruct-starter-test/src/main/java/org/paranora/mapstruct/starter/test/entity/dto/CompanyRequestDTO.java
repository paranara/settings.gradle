package org.paranora.mapstruct.starter.test.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {

    private String companyName;
    private String companyFax;
    private String companyAddress;
}
