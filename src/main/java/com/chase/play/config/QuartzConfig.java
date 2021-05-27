package com.chase.play.config;

import com.chase.play.component.JobFactory;
import com.chase.play.quartz.SpringBootQuartzJob1;
import com.chase.play.quartz.SpringBootQuartzJob2;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @version 1.0
 * @Description Quartz配置类
 * @Author chase
 * @Date 2020/10/24 22:27
 */
@Configuration
@EnableScheduling
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    /**
     * 创建Job对象。在Spring环境中，创建一个类型的对象的时候，很多情况下，都是通过FactoryBean来间接创建的。
     * 如果有多个Job对象，定义多次方法。
     * 将Job放入JobDetailFactoryBean
     * @return
     */
    @Bean("JobDetailFactoryBean1")
    public JobDetailFactoryBean JobDetailFactoryBean1(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        // 提供job类型。
        factoryBean.setJobClass(SpringBootQuartzJob1.class);
        return factoryBean;
    }
    @Bean("JobDetailFactoryBean2")
    public JobDetailFactoryBean JobDetailFactoryBean2(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        // 提供job类型。
        factoryBean.setJobClass(SpringBootQuartzJob2.class);
        return factoryBean;
    }

    /**
     * 管理Trigger对象
     * CronTrigger - 就是Trigger的一个实现类型。其中用于定义周期时间的是CronSchedulerBuilder
     * 实际上，CronTrigger是用于管理一个Cron表达式的类型。
     *
     * @param jobDetailFactoryBean - 上一个方法初始化的JobDetailFactoryBean
     * @return
     */
    @Bean(name="cronTriggerFactoryBean1")
    public CronTriggerFactoryBean initCronTriggerFactoryBean(@Qualifier("JobDetailFactoryBean1") JobDetailFactoryBean jobDetailFactoryBean ){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0 10,14,16 * * ?");
        return factoryBean;
    }
    @Bean(name="cronTriggerFactoryBean2")
    public CronTriggerFactoryBean initCronTriggerFactoryBean2(@Qualifier("JobDetailFactoryBean2") JobDetailFactoryBean jobDetailFactoryBean ){
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0 10,18,23 * * ?");
        return factoryBean;
    }

    /**
     * 初始化Scheduler
     * @param cronTriggerFactoryBean - 上一个方法初始化的CronTriggerFactoryBean
     * @return
     */
    @Bean
    public SchedulerFactoryBean initSchedulerFactoryBean(CronTriggerFactoryBean[] cronTriggerFactoryBean){
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(jobFactory);

        CronTrigger[] triggers = new CronTrigger[cronTriggerFactoryBean.length];

        for(int i = 0; i < cronTriggerFactoryBean.length; i++){
            triggers[i] = cronTriggerFactoryBean[i].getObject();
        }
        // 注册触发器，一个Scheduler可以注册若干触发器。
        factoryBean.setTriggers(triggers);
        return factoryBean;
    }
}
