package hllotobi.tobi;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void helloController(){
        HelloController helloController = new HelloController(name -> name);
        String result = helloController.hello("TEST");

        Assertions.assertThat(result).isEqualTo("TEST");

    }

    @Test
    void helloFailController(){
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
//        Assertions.assertThat(result).isEqualTo();

        Assertions.assertThatThrownBy(() -> {
            String result = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);

    }
}
