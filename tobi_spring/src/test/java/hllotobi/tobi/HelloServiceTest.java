package hllotobi.tobi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();


        String res = helloService.sayHello("Test");

        assertThat(res).isEqualTo("HelloTest");
    }
}
