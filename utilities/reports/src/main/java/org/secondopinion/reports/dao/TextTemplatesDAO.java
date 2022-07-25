package org.secondopinion.reports.dao; 

import java.util.List;

import org.secondopinion.dao.IDAO;
import org.secondopinion.reports.dto.TextTemplates;

public interface TextTemplatesDAO extends IDAO<TextTemplates,Long >{
	
	TextTemplates getTemplate(String level, Long companyId, String templateName);
	
	TextTemplates getDefaultTemplate(String level, String templateName);

	List<TextTemplates> getTemplates(String level, Long refereceId);
}