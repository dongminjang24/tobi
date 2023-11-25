package hllotobi.config.autoConfig;


import hllotobi.config.ConditionalMyOnClass;
import hllotobi.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//@Conditional(JettyWebServerConfig.JettyCondition.class)
@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {

    @Bean("jettyWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(){

        return new JettyServletWebServerFactory();
    };

//    static class JettyCondition implements Condition {
//
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//
//            return ClassUtils.isPresent(   "org.eclipse.jetty.server.Server",
//                    context.getClassLoader());
//        }
//    }

}
