package org.paranora.mapstruct.starter.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO;

import java.util.Date;

@PMapper(target = StaffRequestDTO.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @PMapping(target = "staffName"
            , nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION
            , dependsOn = {"a", "b"}
            , qualifiedBy = {PMapping.class, PMapper.class})
    private String name;
    private Integer age;
    private Integer sex;
    private Company company;
    private Date birth;

}
