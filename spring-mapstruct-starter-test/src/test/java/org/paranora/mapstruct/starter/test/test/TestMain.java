package org.paranora.mapstruct.starter.test.test;


import com.squareup.javapoet.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.starter.core.annotations.MPMapper;
import org.paranora.mapstruct.starter.core.annotations.MPMapping;
import org.paranora.mapstruct.starter.test.entity.Staff;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, QuartzAutoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestMain.class})
@ActiveProfiles({"development"})
@Import({})
public class TestMain {

    @Test
    public void test_main_method_a() throws Exception {
        print("test_main_method_a begin.");

        Arrays.stream(MPMapping.class.getDeclaredMethods()).forEach(m -> {
            TypeName typeName = TypeName.get(m.getReturnType());
            if (typeName.isPrimitive()) {
                print(String.format("%s, base type : %s , name : %s,%s", MPMapping.class.getSimpleName(),typeName.toString(), m.getName(),typeName.getClass()));
            } else {
                if (typeName instanceof ClassName) {
                    print(String.format("%s,class type : %s , name : %s,%s", MPMapping.class.getSimpleName(),typeName.toString(), m.getName(),typeName.getClass()));
                }else if (typeName instanceof ArrayTypeName) {
                    print(String.format("%s,array type : %s , name : %s,%s", MPMapping.class.getSimpleName(),typeName.toString(), m.getName(),typeName.getClass()));
                } else {
                    print(String.format("%s,unknow type : %s , name : %s,%s", MPMapping.class.getSimpleName(),typeName.toString(), m.getName(),typeName.getClass()));
                }
            }
        });

        String className = MPMapper.class.getName();
        print(className);

        List<String> a = new ArrayList<>();
//        Class<? extends List> classType = new ArrayList<String>().getClass();
//        Class<String> entityClass = (Class<String>) ((ParameterizedType) classType.getClass().getGenericSuperclass()).getActualTypeArguments()[0];


        TypeName typeNameA = ParameterizedTypeName.get(String.class);
        TypeName typeNameB = ParameterizedTypeName.get(a.getClass());

        //Map<String, Class<? extends Staff>>
        ParameterizedTypeName inputMapTypeOfRoot = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ParameterizedTypeName.get(
                        ClassName.get(Class.class),
                        WildcardTypeName.subtypeOf(ClassName.get(Staff.class))
                )
        );

        // Map<String, Staff>
        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                ClassName.get(Map.class),
                ClassName.get(String.class),
                ClassName.get(Staff.class)
        );

        FieldSpec itemName = FieldSpec.builder(inputMapTypeOfGroup, "itemName")
                .addModifiers(Modifier.PROTECTED)
                .initializer("$L", "new HashMap<String,Staff>()")
                .build();

        CodeBlock.builder().add("$S", "str").build();

        Field[] fields = MPMapping.class.getFields();

        TypeName nullValueCheckStrategyTypeName = TypeName.get(NullValueCheckStrategy.class);

        CodeBlock codeBlockA = CodeBlock.builder()
                .add("$T.$L", NullValueCheckStrategy.class, "ON_IMPLICIT_CONVERSION")
                .build();


        print("test_main_method_a  end");
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}

