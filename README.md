
# Spring Mapstruct Starter

Mapstruct 简单集成,为了更少的写代码

#### 技术依赖：
* `Spring Boot`
* `Mapstruct`
* `lombok `

#### 环境依赖：
* `JDK8`


#### 示例：

##### 1、导入依赖包：
```java
implementation group: 'io.github.paranara', name: 'spring-mapstruct', version: '1.0.1.1'
implementation group: 'io.github.paranara', name: 'spring-mapstruct-starter', version: '1.0.1.1'
annotationProcessor group: 'io.github.paranara', name: 'spring-mapstruct-processor', version: '1.0.1.1'
annotationProcessor group: 'org.mapstruct', name: 'mapstruct-processor', version: '1.5.2.Final'
annotationProcessor group: 'org.projectlombok', name: 'lombok-mapstruct-binding', version: '0.2.0'
```

##### 2、准备相关实体类：

```java
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
```

```java
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
```

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PMapper(target = CompanyRequestDTO.class)
public class Company {

    @PMapping(target = "companyName")
    private String name;

    @PMapping(target = "companyFax")
    private String fax;

    @PMapping
    private String address;
}
```

```java
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

    private String companyName;
    private String companyFax;
    private String address;
}
```

##### 3、注入对象：

```java
@Autowired
private MapstructConversionService conversionService;
```

##### 4、进行对象转换：

```java
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
```

## 后期计划

待定... ...

