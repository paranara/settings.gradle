package org.paranora.mapstruct.starter;

import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.converter.CustomConversionService;
import org.paranora.mapstruct.converter.MapstructConversionService;
import org.paranora.mapstruct.converter.MapstructMapperConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Configuration
@ComponentScan({PMapper.DefaultPackageName})
public class MapstructAutoConfiguration {

    @Autowired
    private List<Converter> converters;

    @Bean("MapstructConversionService")
    public MapstructConversionService conversionService() {
        MapstructConversionService conversionService = new MapstructConversionService();

        if (null != converters && converters.size() > 0) {
            converters.stream()
                    .filter(c -> !c.getClass().isAnnotationPresent(Qualifier.class))
                    .forEach(c -> {
                        conversionService.addConverter(c);
                    });
        }

        return conversionService;
    }

    @Bean("MapstructMapperConversionService")
    public MapstructMapperConversionService mapperConversionService() {
        MapstructMapperConversionService conversionService = new MapstructMapperConversionService();

        if (null != converters && converters.size() > 0) {
            converters.stream()
                    .filter(c -> c.getClass().isAnnotationPresent(Qualifier.class) && !c.getClass().isAnnotationPresent(Primary.class))
                    .forEach(c -> {
                        conversionService.addConverter(c);
                    });
        }

        return conversionService;
    }

}
