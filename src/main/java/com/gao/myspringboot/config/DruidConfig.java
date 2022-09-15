package com.gao.myspringboot.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DruidConfig {

    /**
     * 配置druid 慢SQL 日志
     * @return
     */
    @Bean
    public Filter statFilter1(){
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(100);
        filter.setLogSlowSql(true);
        return filter;
    }
    /**
     * 配置服务过滤器
     *
     * @return 返回过滤器配置对象
     */
    @Bean
    public FilterRegistrationBean statFilter2() {
        FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        frb.addUrlPatterns("/*");
        // 忽略过滤格式
//        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
        return frb;
    }
    @Bean
    DruidStatInterceptor druid_stat_interceptor(){
        return new DruidStatInterceptor();
    }

    @Bean
    BeanTypeAutoProxyCreator druid_type_proxyCreator(){
        BeanTypeAutoProxyCreator creator = new BeanTypeAutoProxyCreator();
        creator.setTargetBeanType(BaseMapper.class);
        creator.setInterceptorNames("druid_stat_interceptor");
        return creator;
    }
}
