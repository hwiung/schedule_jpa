package com.example.schedule_jpa.config;

import com.example.schedule_jpa.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean                        // 모든 요청에 필터를 적용하되, 필터 내부에서 화이트 리스트를 확인하여 예외 처리를 함.
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(1); // setOrder(1)을 통해 LoginFilter를 다른 필터보다 우선적으로 실행하도록 함.
        filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청이 필터를 거치도록 설정함.

        return filterRegistrationBean;  // 설정된 FilterRegistrationBean 객체를 반환하여 스프링 컨테이너에 등록함.
    }
}
