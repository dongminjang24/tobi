package hllotobi.tobi;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class HelloController {


    private ApplicationContext applicationContext;
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
//        this.applicationContext = applicationContext;
//        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        System.out.println(applicationContext);
//        this.applicationContext = applicationContext;
//    }
}
