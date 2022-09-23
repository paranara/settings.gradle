package org.paranora.mapstruct.starter.test.entity.dto;


import lombok.*;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.starter.test.entity.Staff;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PMapper(target = Staff.class)
public class StaffRequestDTO {
    private String staffName;
    private Integer age;

    @PMapping(target = "sex")
    private Integer staffSex;
    private String birth;
    private CompanyRequestDTO staffCompany;
}
