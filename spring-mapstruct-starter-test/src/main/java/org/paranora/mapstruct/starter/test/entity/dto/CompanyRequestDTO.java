package org.paranora.mapstruct.starter.test.entity.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

    private String companyName;
    private String companyFax;
    private String address;
}
