package org.secondopinion.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.request.RequestContextListener;
import org.secondopinion.configurations.AppProperties;
import org.secondopinion.utilities.feedbackreport.service.ILookupstaticService;
import org.secondopinion.utilities.jobs.DBJob;
import org.secondopinion.utilities.jobs.task.DailyReportDataTask;
import org.secondopinion.utilities.jobs.task.EmailReportsTask;
import org.secondopinion.utilities.jobs.task.VCBaseTask;
import org.secondopinion.utils.MailProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages={"org.secondopinion"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UtilitiesApplication {

	@Autowired
	private AppProperties appProperties;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(UtilitiesApplication.class, args);
		ILookupstaticService lookupstaticservice = app.getBean(ILookupstaticService.class);
		lookupstaticservice.getAllLookupstaticsMap();
	}
	
	@Bean
    public HttpMessageConverters customConverters() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        return new HttpMessageConverters(arrayHttpMessageConverter);
    }
	
	@Bean
    public MailProperties mailProperties() {
        return new MailProperties(appProperties.getSmtp().getPort());
    }
	
	@Bean
	public SchedulerFactoryBean scheduler() {
		return new SchedulerFactoryBean();
	}
	
	@Bean 
	public RequestContextListener requestContextListener(){
	    return new RequestContextListener();
	} 
	
	@Bean
	public DBJob<Object> dailySubmissionReportJob() {
		DBJob<Object> job = new DBJob<Object>();
		job.setJobBean("dailySubmissionReportJob");
		
		List<VCBaseTask<?, ?>> tasksList = new ArrayList<VCBaseTask<?,?>>();
		tasksList.add(dailyReportDataTask());
		tasksList.add(emailReportsTask());
		
		job.setTasksList(tasksList);
		return job;
	}
	
	@Bean
	public DailyReportDataTask dailyReportDataTask() {
		DailyReportDataTask dailyReportDataTask = new DailyReportDataTask();
		dailyReportDataTask.setSubmissionsCountReport("Submission Count Today");
		dailyReportDataTask.setRequirementsCountReport("Requirement Count Today");
		dailyReportDataTask.setSubmissionsReport("Submissions Counts Today");
		dailyReportDataTask.setRequirementsReport("Requirement Counts Today");
		dailyReportDataTask.setTaskInput("INPUT");
		dailyReportDataTask.setTaskOutput("OUTPUT");
		
		return dailyReportDataTask;
	}
	
	@Bean
	public EmailReportsTask emailReportsTask() {
		EmailReportsTask emailReportsTask = new EmailReportsTask();
		emailReportsTask.setTaskInput("INPUT");
		emailReportsTask.setTaskOutput("OUTPUT");
		return emailReportsTask;
	}
	
}
	