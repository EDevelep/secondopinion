package org.secondopinion.reports.dao.impl.hierarchy;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.secondopinion.common.dto.DAOResult;
import org.secondopinion.dao.exception.DataAccessException;
import org.secondopinion.reports.dao.TextTemplatesDAO;
import org.secondopinion.reports.dao.impl.hibernate.TextTemplatesDAOHibernateImpl;
//import org.secondopinion.reports.dao.impl.ignite.TextTemplateIgniteDAO;
import org.secondopinion.reports.dto.TextTemplates;

public class TextTemplatesHierarchyDAO implements TextTemplatesDAO{
	private static Logger LOG = LoggerFactory.getLogger(TextTemplatesHierarchyDAO.class);
	
	private TextTemplatesDAOHibernateImpl hibDao;
	//private TextTemplateIgniteDAO igniteDao;
	
	@Override
	public Collection<TextTemplates> findAll() { 
		/*Collection<TextTemplates> textTemplates =  igniteDao.findAll();
		
		if(textTemplates == null){
			textTemplates = hibDao.findAll();
			igniteDao.save(textTemplates);	
		}
		
		return textTemplates;*/
		return hibDao.findAll();
	}
	
	@Override
	public TextTemplates findById(Long key) {
		/*TextTemplates templates = igniteDao.findById(key);
		
		if(templates == null){
			templates = hibDao.findById(key);
			igniteDao.save(templates);
		}
		return templates;*/
		return hibDao.findById(key);
	}
	@Override
	public void save(TextTemplates type) {
		hibDao.save(type);
		//igniteDao.save(type);
		
	}
	@Override
	public void save(Collection<TextTemplates> types) {
		hibDao.save(types);
		//igniteDao.save(types);
	}
	
	@Override
	public DAOResult<TextTemplates, Long> saveWithRetry(Collection<TextTemplates> types) {
		DAOResult<TextTemplates, Long>  result = hibDao.saveWithRetry(types);
		
		//igniteDao.save(result.getSuccess());
		
		return result;
	}
	
	@Override
	public void delete(TextTemplates templates) throws DataAccessException {
		hibDao.delete(templates);
		//igniteDao.delete(templates);
		
	}
	@Override
	public void delete(Collection<TextTemplates> templates) throws DataAccessException {
		hibDao.delete(templates);
		//igniteDao.delete(templates);
		
	}
	@Override
	public TextTemplates getTemplate(String level, Long companyId, String templateName) {
		TextTemplates templates = null;
		
		try{
			//igniteDao.getTemplate(level, companyId, templateName);
		}catch(Exception ex){
			LOG.error("Error retrieving template: " + templateName);
		}
		
		
		
		if(templates == null){
			templates = hibDao.getTemplate(level, companyId, templateName);
			
			//igniteDao.save(templates);
			
		}
		return templates;
	}
	
	@Override
	public TextTemplates getDefaultTemplate(String level, String templateName) {
		TextTemplates templates = null;
		try{
			//templates = igniteDao.getDefaultTemplate(level,  templateName);
		}catch(Exception ex){
			LOG.error("Error retreiving template " + templateName + " level " + level, ex);
		}
		
		if(templates == null){
			templates = hibDao.getDefaultTemplate(level, templateName);
			
			//igniteDao.save(templates);
			
		}
		return templates;
	}

	@Override
	public List<TextTemplates> getTemplates(String level, Long refereceId) {
		/*List<TextTemplates> templates = igniteDao.getTemplates(level,  refereceId);
		
		if(templates == null){
			templates = hibDao.getTemplates(level, refereceId);
			
			igniteDao.save(templates);
		}
		
		return templates;*/
		return hibDao.getTemplates(level, refereceId);
	}

	public TextTemplatesDAOHibernateImpl getHibDao() {
		return hibDao;
	}

	public void setHibDao(TextTemplatesDAOHibernateImpl hibDao) {
		this.hibDao = hibDao;
	}

	@Override
	public List<TextTemplates> findByProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TextTemplates findOneByProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TextTemplates> findByPropertyValues(String propertyName, List propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/*public TextTemplateIgniteDAO getIgniteDao() {
		return igniteDao;
	}

	public void setIgniteDao(TextTemplateIgniteDAO igniteDao) {
		this.igniteDao = igniteDao;
	}*/
}
