package cn.aiyls.fly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@SpringBootApplication
public class FlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlyApplication.class, args);
    }

}
