package org.paranora.mapstruct.starter.test.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.starter.test.entity.Company;
import org.paranora.mapstruct.starter.test.entity.Staff;
import org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, QuartzAutoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestMain.class})
@ActiveProfiles({"development"})
@Import({})
public class TestMain {

    @Autowired
    MapstructConversionService conversionService;

    @Test
    public void test_main_method_a() throws Exception {
        print("test_main_method_a begin.");

        testF();

        print("test_main_method_a  end");
    }

    public void testF() {
        Staff staff = Staff.builder()
                .name("paranora")
                .age(18)
                .company(
                        Company.builder()
                                .name("www")
                                .address("sh-pd")
                                .fax("123456")
                                .build())
                .build();

        StaffRequestDTO staffRequestDTO = conversionService.convert(staff, StaffRequestDTO.class);
        print("end");
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}

