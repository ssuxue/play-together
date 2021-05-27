package com.chase.play.quartz;

import com.chase.play.service.ExtensionService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;
import java.util.HashSet;

/**
 * @version 1.0
 * @Description 定时任务类
 * @Author chase
 * @Date 2020/10/24 22:30
 */
public class SpringBootQuartzJob1 implements Job {

    private static Logger logger = LoggerFactory.getLogger(SpringBootQuartzJob1.class);

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private ExtensionService extensionService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            HashSet<Integer> ids = extensionService.getIds();
            extensionService.stopExtension(ids);
        } catch (ParseException ex) {
            logger.error("出错信息:" + ex);
        }
    }
}
