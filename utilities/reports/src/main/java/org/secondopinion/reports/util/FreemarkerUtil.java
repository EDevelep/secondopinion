/*package org.secondopinion.reports.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.secondopinion.utils.file.FileUtil;
import org.springframework.core.io.ClassPathResource;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {
	
	public static Configuration getFreemarkerConfig(){
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
	
	//	cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
	
		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");
	
		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	
		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);
		
		return cfg;
	}
	
	public static String applyTemplate(String template, Map<String, Object> map){
		  Get the template (uses cache internally) 
        Template temp;
		try {
			temp = new Template("name", new StringReader(template), new Configuration(Configuration.VERSION_2_3_25));
			
			   Merge data-model with template 
			StringWriter out = new StringWriter();;
	        try {
				temp.process(map, out);
				
				return out.toString();
			} catch (TemplateException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Error processing template", e);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error processing template", e);
		}
	}
	
	public static String fillTemplate(String emailTemplateLocation, Map<String, Object> root) {
		String reqTemplate;
		try {	
			ClassPathResource cpr = new ClassPathResource(emailTemplateLocation);
			reqTemplate = FileUtil.getStreamContentsAsString(cpr.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error loading file: " + emailTemplateLocation);
		}
		
		return applyTemplate(reqTemplate, root);
	}
}
*/