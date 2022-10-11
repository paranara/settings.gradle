package org.paranora.mapstruct.starter.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.starter.test.entity.dto.CompanyRequestDTO;
import org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO;

import java.util.Date;
import java.util.List;

@PMapper(target = StaffRequestDTO.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @PMapping(target = "staffName", nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    private String name;

    @PMapping
    private Integer age;

    @PMapping(target = "staffSex", expression = "java(1)")
    private Integer sex;

    @PMapping(nest = true, target = "staffCompany", targetType = CompanyRequestDTO.class)
    private Company company;

    private Date birth;

}
