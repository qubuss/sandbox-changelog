package pl.bazus.changelog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}
