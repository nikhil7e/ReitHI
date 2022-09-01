package is.hi.hbv501g.reithi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ReitHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReitHiApplication.class, args);
    }

}
