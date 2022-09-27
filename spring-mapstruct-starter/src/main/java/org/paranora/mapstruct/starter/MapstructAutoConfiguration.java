package org.paranora.mapstruct.starter;

import org.paranora.mapstruct.annotations.PMapper;
import org.paranora.mapstruct.converter.CustomConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Configuration
@ComponentScan({PMapper.DefaultPackageName})
public class MapstructAutoConfiguration {

    @Autowired
    private List<Converter> converters;

    @Bean("CustomConversionService")
    public ConversionService conversionService() {
        CustomConversionService conversionService = new CustomConversionService();

        if (null != converters && converters.size() > 0) {
            converters.forEach(c -> {
                conversionService.addConverter(c);
            });
        }

        return conversionService;
    }

}
