package cn.wangjie.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReadWriteApplication {

    public static void main(String[] args) {

/*        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ReadWriteApplication.class);
        configApplicationContext.getBean(RedisConfig.class);*/
        SpringApplication springApplication = new SpringApplication(ReadWriteApplication.class);
        ConfigurableApplicationContext context = springApplication.run(args);
        context.close();
    }

}

