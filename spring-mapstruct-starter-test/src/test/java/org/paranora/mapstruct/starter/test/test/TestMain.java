package org.paranora.mapstruct.starter.test.test;


import com.squareup.javapoet.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.annotations.PMapping;
import org.paranora.mapstruct.java.metadata.entity.*;
import org.paranora.mapstruct.java.code.poet.DefaultInterfaceGenerator;
import org.paranora.mapstruct.java.code.poet.InterfaceJavapoetGenerator;
import org.paranora.mapstruct.starter.test.entity.Company;
import org.paranora.mapstruct.starter.test.entity.Staff;
import org.paranora.mapstruct.starter.test.entity.dto.StaffRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.lang.model.element.Modifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, QuartzAutoConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestMain.class})
@ActiveProfiles({"development"})
@Import({})
public class TestMain {

    @Autowired
    ConversionService conversionService;

    @Test
    public void test_main_method_a() throws Exception {
        print("test_main_method_a begin.");

//        Class<?> c=Staff.class;
//        if(c.getClass().equals(Class.class)){
//            print("test_main_method_a  end");
//        }

//        testC();
        testF();

        print("test_main_method_a  end");
    }

    public void testF(){
        Staff staff=Staff.builder()
                .name("paranora")
                .age(18)
                .build();

        StaffRequestDTO staffRequestDTO=conversionService.convert(staff,StaffRequestDTO.class);

        print("end");
    }

    public void testE() {

        Map<String, String> mymap = new IdentityHashMap<String, String>() {
            {
                put("1", "one");
                put("1", "two");
            }
        };

        mymap.entrySet().stream().forEach(v -> {
            print(v.getValue());
        });


        print("end");
    }

    public void testD() {
        String str = "paranora";

        Map<Integer, String> mymap = new HashMap<Integer, String>() {
            {
                put(1, "one");
                put(2, "two");
            }
        };

        mymap.put(1, "paranora");

        mymap.entrySet().stream().forEach(v -> {
            print(v.getValue());
        });


        Map<String, FieldMeta> map = Arrays.asList(
                        FieldMeta.builder()
                                .name("aaa")
                                .value("paranora")
                                .accessLevels(Arrays.asList(Modifier.PUBLIC))
                                .build()
                )
                .stream()
                .collect(Collectors.toMap(FieldMeta::getName
                        , fm -> fm
                        , (key1, key2) -> key2
                ));

        print("end");
    }


    public void testC() throws ClassNotFoundException {
        InterfaceJavapoetGenerator interfaceJavapoetGenerator = new DefaultInterfaceGenerator();
        InterfaceMeta meta = new InterfaceMeta();
        meta.setName("InterfaceGenerateTestA");
        meta.setAccessLevels(Arrays.asList(Modifier.PUBLIC));
        meta.setSuperInterfaces(Arrays.asList(InterfaceMeta.builder().packageName("org.springframework.core.convert.converter").name("Converter").genericTypes(Arrays.asList(TypeName.get(Company.class), TypeName.get(Staff.class))).build()));
        meta.setAnnotations(Arrays.asList(AnnotationMeta.builder().name(PMapper.class.getSimpleName()).packageName(PMapper.class.getPackage().getName()).fields(new HashMap<String, AnnotationFieldMeta>() {
                    {
                        put("name", AnnotationFieldMeta.builder().name("name").typeName(TypeName.get(String.class)).value("abc").build());
                        put("target", AnnotationFieldMeta.builder().name("target").typeName(TypeName.get(Class.class)).value("org.paranora.mapstruct.starter.test.entity.Staff.class").build());
                    }
                }).build())
            );

        meta.setMethods(Arrays.asList(MethodMeta.builder().annotations(Arrays.asList(AnnotationMeta.builder().name(Mapping.class.getSimpleName()).packageName(Mapping.class.getPackage().getName()).fields(new HashMap<String, AnnotationFieldMeta>() {
                            {
                                put("target", AnnotationFieldMeta.builder().name("target").typeName(TypeName.get(String.class)).value("abc").build());
                                put("source", AnnotationFieldMeta.builder().name("source").typeName(TypeName.get(String.class)).value("abc").build());
                                put("nullValueCheckStrategy", AnnotationFieldMeta.builder().name("nullValueCheckStrategy").typeName(TypeName.get(NullValueCheckStrategy.class)).value(NullValueCheckStrategy.ON_IMPLICIT_CONVERSION).build());
                            }
                        }).build())
                )
                .parameters(Arrays.asList(ParameterMeta.builder().name("source").typeName(TypeName.get(Staff.class)).build()))
                .accessLevels(Arrays.asList(Modifier.PUBLIC, Modifier.ABSTRACT)).returnType(TypeName.get(Staff.class)).name("convert")
//                        .annotationClazs(Arrays.asList(Override.class))
                .build()));

        TypeSpec interfaceSpec = interfaceJavapoetGenerator.create(meta);

        String str = interfaceSpec.toString();

        print(str);

        print("The End.");
    }


    public void testB() {
        CodeBlock codeBlockA = CodeBlock.of("$T.$L", String.class, "a");

        String[] dependsOn = new String[]{"a", "b"};
        CodeBlock codeBlockB = CodeBlock.builder().add("$L", Arrays.stream(dependsOn).map(type -> CodeBlock.of("$T.$L", String.class, type)).collect(CodeBlock.joining(",", "{", "}"))).build();

        String str = codeBlockB.toString();
        print(str);
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

            print(String.format(messageFormat, PMapping.class.getSimpleName(), methodReturnTypeLevel, typeName, m.getName(), typeName.getClass(), v));
        });

        String className = PMapper.class.getName();
        print(className);

        List<String> a = new ArrayList<>();
//        Class<? extends List> classType = new ArrayList<String>().getClass();
//        Class<String> entityClass = (Class<String>) ((ParameterizedType) classType.getClass().getGenericSuperclass()).getActualTypeArguments()[0];


        TypeName typeNameA = ParameterizedTypeName.get(String.class);
        TypeName typeNameB = ParameterizedTypeName.get(a.getClass());

        //Map<String, Class<? extends Staff>>
        ParameterizedTypeName inputMapTypeOfRoot = ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class), ParameterizedTypeName.get(ClassName.get(Class.class), WildcardTypeName.subtypeOf(ClassName.get(Staff.class))));

        // Map<String, Staff>
        ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class), ClassName.get(Staff.class));

        FieldSpec itemName = FieldSpec.builder(inputMapTypeOfGroup, "itemName").addModifiers(Modifier.PROTECTED).initializer("$L", "new HashMap<String,Staff>()").build();

        CodeBlock.builder().add("$S", "str").build();

        Field[] fields = PMapping.class.getFields();

        TypeName nullValueCheckStrategyTypeName = TypeName.get(NullValueCheckStrategy.class);

        CodeBlock codeBlockA = CodeBlock.builder().add("$T.$L", NullValueCheckStrategy.class, "ON_IMPLICIT_CONVERSION").build();

        MethodSpec setItemNameMethod = MethodSpec.methodBuilder("setTest").addModifiers(Modifier.PUBLIC).returns(void.class).addParameter(String.class, "itemName").addStatement("this.$L=$L", itemName.name, itemName.name).build();

        AnnotationSpec annotationSpecA = AnnotationSpec.builder(ClassName.get(Override.class)).build();
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}

