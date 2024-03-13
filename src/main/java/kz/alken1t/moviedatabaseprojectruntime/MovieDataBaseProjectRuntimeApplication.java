package kz.alken1t.moviedatabaseprojectruntime;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieDataBaseProjectRuntimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieDataBaseProjectRuntimeApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}