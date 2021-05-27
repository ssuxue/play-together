package com.chase.play.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Description MyBatis配置类
 * @Author chase
 * @Date 2020/9/21 20:27
 */
@Configuration
@MapperScan("com.chase.play.dao")
public class MyBatisConfig {
}
