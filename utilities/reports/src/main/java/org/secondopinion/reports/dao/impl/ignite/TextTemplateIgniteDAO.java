/*package org.secondopinion.reports.dao.impl.ignite;

import java.util.List;

import org.secondopinion.reports.dao.TextTemplatesDAO;
import org.secondopinion.reports.dto.TextTemplates;

import com.vcube.cache.ignite.IgniteCacheManager;

public class TextTemplateIgniteDAO extends AbstractIgniteCacheDAO<Long, TextTemplates> implements TextTemplatesDAO{

	public TextTemplateIgniteDAO(String className, String cacheName, IgniteCacheManager cacheManager) {
		super(className, cacheName, cacheManager);
	}
	
	@Override
	public void save(TextTemplates type) {
		super.save(type);
	}

	private static final String templateSql = TextTemplates.FIELD_level + " = ? and " + TextTemplates.FIELD_referenceId + " = ? and "
			+ TextTemplates.FIELD_templateName + " = ?";
	@Override
	public TextTemplates getTemplate(String level, Long companyId, String templateName) {
		
		List<TextTemplates> textTemplates = query(templateSql ,  level, companyId, templateName);
		if(textTemplates != null && textTemplates.size()>0){
			return textTemplates.get(0);
		}
		
		return null;
	}

	private static final String defaultTemplateSql = TextTemplates.FIELD_level + " = ? and " + TextTemplates.FIELD_defaultTemplate + " = 'Y' and "
			+ TextTemplates.FIELD_templateName + " = ?";
	@Override
	public TextTemplates getDefaultTemplate(String level, String templateName) {
		List<TextTemplates> textTemplates = query(defaultTemplateSql ,  level,  templateName);
		
		if(textTemplates != null && textTemplates.size()>0){
			return textTemplates.get(0);
		}
		return null;
	}

	private static final String templateForCompanySql = TextTemplates.FIELD_level + " = ? and " + TextTemplates.FIELD_referenceId + " = ?";
	@Override
	public List<TextTemplates> getTemplates(String level, Long refereceId) {
		return query(templateForCompanySql ,  level, refereceId);
	}
	
	@Override
	public Class<Long> getKeyClass() {
		return Long.class;
	}
}

*/