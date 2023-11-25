package hllotobi.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {

    @Test
    void conditional() {

        ApplicationContextRunner contextRunner1 = new ApplicationContextRunner();
        contextRunner1.withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
            Assertions.assertThat(context).hasSingleBean(Config1.class);

        } );

        ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
        contextRunner2.withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
            Assertions.assertThat(context).doesNotHaveBean(Config2.class);

        } );

        //true
//        AnnotationConfigApplicationContext ac1 = new AnnotationConfigApplicationContext();
//        ac1.register(Config1.class);
//        ac1.refresh();



        //false
//        AnnotationConfigApplicationContext ac2 = new AnnotationConfigApplicationContext();
//        ac2.register(Config2.class);
//        ac2.refresh();

//        MyBean bean1 = ac1.getBean(MyBean.class);
//        MyBean bean2 = ac2.getBean(MyBean.class);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional{}


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    //    @Conditional(TrueCondition.class)
    @TrueConditional
    @Configuration
    @BooleanConditional(true)
    static class Config1 {


        @Bean
        MyBean myBean() {
            return new MyBean();
        }

    }


//    @Conditional(FalseCondition.class)
//@FalseConditional
@Configuration
@BooleanConditional(false)
static class Config2 {


        @Bean
        MyBean myBean() {
            return new MyBean();
        }

    }

    static class MyBean {
    }

    static class TrueCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }


    static class FalseCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

            Map<String, Object> attributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean)attributes.get("value");
            return value;
        }
    }



}
