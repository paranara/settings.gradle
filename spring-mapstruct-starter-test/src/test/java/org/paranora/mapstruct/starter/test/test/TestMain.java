package org.paranora.mapstruct.starter.test.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.paranora.mapstruct.starter.core.annotations.MPMapper;
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

    @Test
    public void test_main_method_a() throws Exception {
        print("test_main_method_a begin.");

        String className= MPMapper.class.getName();
        print(className);

        print("test_main_method_a  end");
    }

    public void print(String msg){
        System.out.println(msg);
    }
}

