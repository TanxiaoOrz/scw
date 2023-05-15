package com.example.scw.config;

import com.example.scw.pojo.entity.User;
import com.example.scw.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebMVCConfig extends WebMvcConfigurationSupport {

    @Autowired
    UserService userService;

    @Override
    protected void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginCheck())
                .addPathPatterns("/user/information")
                .addPathPatterns("/team/configGet")
                .addPathPatterns("/work/study/all")
                .addPathPatterns("/notification/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("file")
                .excludePathPatterns("/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/swagger-config",
                        "/webjars/**",
                        "/doc.html");
        registry.addInterceptor(new TeacherCheck())
                .addPathPatterns("/team/configUpdate")
                .addPathPatterns("/work/study/create")
                .addPathPatterns("/work/study/modify")
                .addPathPatterns("/work/study/concrete/*")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("file")
                .excludePathPatterns("/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/swagger-config",
                        "/webjars/**",
                        "/doc.html");
        registry.addInterceptor(new StudentCheck())
                .addPathPatterns("/work/team/**")
                .addPathPatterns("/work/single/**")
                .addPathPatterns("/team/student")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("file")
                .excludePathPatterns("/swagger-ui.html/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/swagger-config",
                        "/webjars/**",
                        "/doc.html");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");


        //appConfig.getResPhysicalPath();    这表示项目所在的文件夹，下面会有介绍

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/","file:static/");

        super.addResourceHandlers(registry);
    }


    //定义时间格式转换器
    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converter.setObjectMapper(mapper);
        return converter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //将我们定义的时间格式转换器添加到转换器列表中,
        //这样jackson格式化时候但凡遇到Date类型就会转换成我们定义的格式
        converters.add(jackson2HttpMessageConverter());
    }

    class StudentCheck implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (request.getMethod().equals("OPTIONS"))
                return true;
            String token = request.getHeader("token");
            User userByToken = userService.getUserByToken(token, User.TYPE_STUDENT);
            request.setAttribute("User",userByToken);
            return userByToken!=null;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
    }

    class TeacherCheck implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (request.getMethod().equals("OPTIONS"))
                return true;
            String token = request.getHeader("token");
            User userByToken = userService.getUserByToken(token, User.TYPE_TEACHER);
            request.setAttribute("User",userByToken);
            return userByToken!=null;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
    }

    class LoginCheck implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (request.getMethod().equals("OPTIONS"))
                return true;
            String token = request.getHeader("token");
            User userByToken = userService.getUserByToken(token, null);
            request.setAttribute("User",userByToken);
            return userByToken!=null;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
    }
}

