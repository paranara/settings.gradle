package org.paranora.mapstruct.starter.test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.starter.core.annotations.MPMapper;
import org.paranora.mapstruct.starter.core.annotations.MPMapping;
import org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO;

import java.util.Date;

@MPMapper(target = StaffRequestDTO.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @MPMapping(target = "staffName", source = "abc", targetClass = StaffRequestDTO.class, nullValueCheckStrategy = NullValueCheckStrategy.ON_IMPLICIT_CONVERSION)
    private String name;
    private Integer age;
    private Integer sex;
    private Company company;
    private Date birth;

}
