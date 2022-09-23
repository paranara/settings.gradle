package org.paranora.mapstruct.starter.test.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.starter.test.entity.dto.CompanyRequestDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PMapper(target = CompanyRequestDTO.class)
public class Company {

    private String name;
    private String fax;

    @PMapping
    private String address;
}
