package io.nology.to_dos.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.Converter;

import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//1.@Configuration - this class declare methods called @Bean - process by Spring to generate these bean definitions
// a bean is like a thing u used in the app - service / controllers / repo are beans

@Configuration
public class ModelMapperConfig {

    //making sure my model mapper behaves the same way everywhere
    @Bean // because i put it tghis way - i can inject my mapper into my book service
    public ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true); //if turning one thing to another thing, if its null - skip it
      //this will add a new instance of STringTrimConverter Private Class here and call this method for converting string into another string by trimming the strings
        mapper.typeMap(String.class, String.class).setConverter(new StringTrimConverter()); //do something everytime i turn a string into a nother string - I.E TRIMMING STRINGS
        return mapper;
    }

    private class StringTrimConverter implements Converter<String , String>{ 

        @Override
        public String convert(MappingContext<String, String> context) {
            if(context.getSource() == null){   // even if skip null is enabled - i still want to be extra careful hence this condition 
                return null;
            }

            return context.getSource().trim(); 
        }

      
    }

}
