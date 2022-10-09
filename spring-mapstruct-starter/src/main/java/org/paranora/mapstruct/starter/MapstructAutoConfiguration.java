package org.paranora.mapstruct.starter;

import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.converter.DefaultMapstructConversionService;
import org.paranora.mapstruct.converter.DefaultMapstructMapperConversionService;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Configuration
@ComponentScan({PMapper.DefaultPackageName})
public class MapstructAutoConfiguration {

    @Autowired
    private List<Converter> converters;

    @Primary
    @Bean("MapstructConversionService")
    public MapstructConversionService mapperConversionService() {
        DefaultMapstructConversionService conversionService = new DefaultMapstructConversionService();

        if (null != converters && converters.size() > 0) {
            converters.stream()
                    .filter(c -> c.getClass().isAnnotationPresent(Primary.class))
                    .forEach(c -> {
                        conversionService.addConverter(c);
                    });
        }

        return conversionService;
    }

//    @Bean("MapstructMapperConversionService")
//    public MapstructMapperConversionService mapperMapperConversionService() {
//        DefaultMapstructMapperConversionService conversionService = new DefaultMapstructMapperConversionService();
//
//        if (null != converters && converters.size() > 0) {
//            converters.stream()
//                    .filter(c -> c.getClass().isAnnotationPresent(Qualifier.class) && !c.getClass().isAnnotationPresent(Primary.class))
//                    .forEach(c -> {
//                        conversionService.addConverter(c);
//                    });
//        }
//
//        return conversionService;
//    }

}
