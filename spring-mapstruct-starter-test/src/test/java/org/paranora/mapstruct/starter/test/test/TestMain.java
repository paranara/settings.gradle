package org.paranora.mapstruct.starter.test.test;


import com.squareup.javapoet.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.starter.core.annotations.PMapper;
import org.paranora.mapstruct.starter.core.annotations.PMapping;
import org.paranora.mapstruct.starter.test.entity.Staff;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.lang.model.element.Modifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

        testB();

        print("test_main_method_a  end");
    }

    public void testB() {
        CodeBlock codeBlockA = CodeBlock.of("$T.$L", String.class, "a");

        String[] dependsOn=new String[]{"a","b"};
        CodeBlock codeBlockB =CodeBlock.builder().add("$L",
                Arrays.stream(dependsOn)
                        .map(type -> CodeBlock.of("$T.$L", String.class,type))
                        .collect(CodeBlock.joining(",", "{", "}"))).build();

        print(codeBlockB.toString());
    }

    public void testA() throws NoSuchFieldException {
        AccessibleObject field = Staff.class.getDeclaredField("name");
        field.setAccessible(true);
        Annotation annotation = field.getDeclaredAnnotation(PMapping.class);

        Arrays.stream(annotation.annotationType().getDeclaredMethods()).forEach(m -> {
            TypeName typeName = TypeName.get(m.getReturnType());
            String messageFormat = "%s, %s type : %s , name : %s, class : %s , value : %s";
            String methodReturnTypeLevel = "base";
            if (typeName.isPrimitive()) {
                methodReturnTypeLevel = "base";
            } else {
                if (typeName instanceof ClassName) {
                    methodReturnTypeLevel = "class";
                } else if (typeName instanceof ArrayTypeName) {
                    methodReturnTypeLevel = "array";
                } else {
                }
            }


            Class<?> claz = annotation.getClass();

            Object v = null;
            try {
                v = m.invoke(annotation, new Object[]{});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            print(String.format(messageFormat
                    , PMapping.class.getSimpleName()
                    , methodReturnTypeLevel
                    , typeName
                    , m.getName()
                    , typeName.getClass()
                    , v));
        });

        String className = PMapper.class.getName();
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

        Field[] fields = PMapping.class.getFields();

        TypeName nullValueCheckStrategyTypeName = TypeName.get(NullValueCheckStrategy.class);

        CodeBlock codeBlockA = CodeBlock.builder()
                .add("$T.$L", NullValueCheckStrategy.class, "ON_IMPLICIT_CONVERSION")
                .build();

        MethodSpec setItemNameMethod = MethodSpec.methodBuilder("setTest")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class)
                .addParameter(String.class, "itemName")
                .addStatement("this.$L=$L", itemName.name, itemName.name)
                .build();

        AnnotationSpec annotationSpecA = AnnotationSpec.builder(ClassName.get(Override.class)).build();
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}

