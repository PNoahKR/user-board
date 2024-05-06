package demo.userboard.global.core.config;

import demo.userboard.global.core.resolver.SessionLoginArgumentResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SessionLoginArgumentResolver sessionLoginArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionLoginArgumentResolver);
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }
}
