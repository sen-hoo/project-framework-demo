package com.cc.framework.springboot.web.configurations;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cc.framework.springboot.web.interceptors.TraceIdInterceptor;
import com.cc.framework.springboot.web.version.VersionRequestMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * mvc配置
 *
 * @author sen.hu
 * @date 2019/3/18 16:39
 **/
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * 配置api版本访问
     * @return
     */
    @Bean
    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        VersionRequestMapping versionMapping = new VersionRequestMapping();
        versionMapping.setOrder(0);
        versionMapping.setInterceptors(getInterceptors());
        return versionMapping;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // 保留map空的字段
                SerializerFeature.WriteMapNullValue,
                // 将String类型的null转成""
                //SerializerFeature.WriteNullStringAsEmpty,
                // 将Number类型的null转成0
                //SerializerFeature.WriteNullNumberAsZero,
                // 将List类型的null转成[]
                SerializerFeature.WriteNullListAsEmpty,
                // 将Boolean类型的null转成false
                //SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        //是否设置content-length,  spring默认使用 chunked 分块传输
        config.setWriteContentLength(false);
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);
        converters.add(converter);
        //string 转换器
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        converters.add(stringHttpMessageConverter);
    }

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * mvc异步调用配置
     *
     * @param configurer
     */
    @Override
    protected void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        ThreadPoolTaskExecutor mvcThreadPool = new ThreadPoolTaskExecutor();
        //核心线程数
        mvcThreadPool.setCorePoolSize(2000);
        //线程总数
        mvcThreadPool.setMaxPoolSize(3000);
        //线程执行最长时间
        mvcThreadPool.setKeepAliveSeconds(15);
        //队列长度
        mvcThreadPool.setQueueCapacity(1000);
        //设置执行名称
        mvcThreadPool.setThreadNamePrefix("mvcAsync-");
        //设置拒绝策略
        mvcThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        mvcThreadPool.initialize();
        //执行线程池
        configurer.setTaskExecutor(mvcThreadPool);
        //退出时间
        configurer.setDefaultTimeout(15 * 1000);
    }

    /**
     * 全局设置跨域问题，还可以是用@CrossOrigin注解进行细粒度配置
     *
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }



}
