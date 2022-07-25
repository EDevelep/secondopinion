package com.dodaso.codegen;

import java.io.File;

import com.dodaso.util.StringUtil;

/**
 * @author rswarna
 *
 */
public class CodeGenProperties {
	public static final String DEFAULT_BASE_PACKAGE = "org.secondopinion.diagnosticcenter";
	public static final String DEFAULT_BASE_DAO_PACKAGE = "dao.impl.hibernate";
	public static final String DEFAULT_BASE_DOMAINOBJECT_PACKAGE = "domain";
	public static final String DEFAULT_DOMAINOBJECT_PACKAGE = "dto";
	public static final String DEFAULT_DAO_IMPL_PACKAGE = "dao.impl.hibernate";
	public static final String DEFAULT_DAO_INTERFACE_PACKAGE = "dao"; 
	public static final String DEFAULT_HIBERNATE_MAPPING_DIR = "mapping";
	public static final String DEFAULT_ACTIONS_PACKAGE = "action";

	private String codeGenDirectory;
	private String basePacakge;
	private String baseDaoPackage;
	private String baseDataObjectPackage;
	private String dataObjectPackage;
	private String daoImplPackage;
	private String actionsPackage;
	
	private String schemaName;
	private String tableName;
	private String tableSequence;
	private boolean regenerating;
	private boolean usingAnnotations = true;
	private String hibernateCfgFile = null;
	
	/**
	 * @return the hibernateCfgFile
	 */
	public String getHibernateCfgFile() {
		return hibernateCfgFile;
	}

	/**
	 * @param hibernateCfgFile the hibernateCfgFile to set
	 */
	public void setHibernateCfgFile(String hibernateCfgFile) {
		this.hibernateCfgFile = hibernateCfgFile;
	}

	/**
	 * @return the usingAnnotations
	 */
	public boolean isUsingAnnotations() {
		return usingAnnotations;
	}

	/**
	 * @param usingAnnotations the usingAnnotations to set
	 */
	public void setUsingAnnotations(boolean useAnnotations) {
		this.usingAnnotations = useAnnotations;
	}

	/**
	 * @return the tableSequence
	 */
	public String getTableSequence() {
		return tableSequence;
	}

	/**
	 * @param tableSequence the tableSequence to set
	 */
	public void setTableSequence(String tableSequence) {
		this.tableSequence = tableSequence;
	}
	
	/**
	 * @return the regenerating
	 */
	public boolean isRegenerating() {
		return regenerating;
	}

	/**
	 * @param regenerating the regenerating to set
	 */
	public void setRegenerating(boolean regenerating) {
		this.regenerating = regenerating;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public CodeGenProperties(String codeGenDirectory) {
		this.codeGenDirectory = codeGenDirectory;
		this.basePacakge = DEFAULT_BASE_PACKAGE;
	}
	
	public CodeGenProperties(String codeGenDirectory, String basePackage) {
		this.codeGenDirectory = codeGenDirectory;
		this.basePacakge = basePackage;
	}
	
	/**
	 * @return the codeGenDirectory
	 */
	public String getCodeGenDirectory() {
		return codeGenDirectory;
	}

	/**
	 * @return the basePacakge
	 */
	public String getBasePackage() {
		return (StringUtil.isNullOrEmpty(basePacakge)?DEFAULT_BASE_PACKAGE:basePacakge);
		//return basePacakge;
	}
	/**
	 * @param basePacakge the basePacakge to set
	 */
	public void setBasePackage(String basePacakge) {
		this.basePacakge = basePacakge;
	}
	/**
	 * @return the baseDaoPackage
	 */
	public String getBaseDaoPackage() {
		return basePacakge + "." + (StringUtil.isNullOrEmpty(baseDaoPackage)?DEFAULT_BASE_DAO_PACKAGE:baseDaoPackage);
	}
	/**
	 * @param baseDaoPackage the baseDaoPackage to set
	 */
	public void setBaseDaoPackage(String baseDaoPackage) {
		this.baseDaoPackage = baseDaoPackage;
	}
	/**
	 * @return the baseDataObjectPackage
	 */
	public String getBaseDataObjectPackage() {
		return basePacakge + "." + (StringUtil.isNullOrEmpty(baseDataObjectPackage)?DEFAULT_BASE_DOMAINOBJECT_PACKAGE:baseDataObjectPackage);
		/*return baseDataObjectPackage;*/
	}
	/**
	 * @param baseDataObjectPackage the baseDataObjectPackage to set
	 */
	public void setBaseDataObjectPackage(String baseDataObjectPackage) {
		this.baseDataObjectPackage = baseDataObjectPackage;
	}
	/**
	 * @return the dataObjectPackage
	 */
	public String getDataObjectPackage() {
		return basePacakge + "." + (StringUtil.isNullOrEmpty(dataObjectPackage)?DEFAULT_DOMAINOBJECT_PACKAGE:dataObjectPackage);
	}
	/**
	 * @param dataObjectPackage the dataObjectPackage to set
	 */
	public void setDataObjectPackage(String dataObjectPackage) {
		this.dataObjectPackage = dataObjectPackage;
	}
	/**
	 * @return the daoImplPackage
	 */
	public String getDaoImplPackage() {
		return basePacakge + "." + (StringUtil.isNullOrEmpty(daoImplPackage)?DEFAULT_DAO_IMPL_PACKAGE:daoImplPackage);
	}
	/**
	 * @param daoImplPackage the daoImplPackage to set
	 */
	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}
	
	/**
	 * @return the actionsPackage
	 */
	public String getActionsPackage() {
		return basePacakge + "." + (StringUtil.isNullOrEmpty(actionsPackage)?DEFAULT_ACTIONS_PACKAGE:actionsPackage);
		//return actionsPackage;
	}

	/**
	 * @param actionsPackage the actionsPackage to set
	 */
	public void setActionsPackage(String actionsPackage) {
		this.actionsPackage = actionsPackage;
	}
	
	/**
	 * @return the daoInterfacePackage
	 */
	public String getDaoInterfacePackage() {
		return basePacakge + "." + DEFAULT_DAO_INTERFACE_PACKAGE;
	}
	
	public String getBaseDirectory() {
		return getCodeGenDirectory() + File.separator + getBasePackage().replace('.', File.separatorChar);
	}
	
	public String getBaseDaoDirectory() {
		return getCodeGenDirectory() + File.separator + getBaseDaoPackage().replace('.', File.separatorChar);
	}
	
	public String getBaseDomainObjectDirectory() {
		return getCodeGenDirectory() + File.separator + getBaseDataObjectPackage().replace('.', File.separatorChar);
	}
	
	public String getDomainObjectDirectory() {
		return getCodeGenDirectory() + File.separator + getDataObjectPackage().replace('.', File.separatorChar);
	}
	
	public String getDaoIntefaceDirectory() {
		return getCodeGenDirectory() + File.separator + getDaoInterfacePackage().replace('.', File.separatorChar);
	}
	
	public String getDaoImplDirectory() {
		return getCodeGenDirectory() + File.separator + getDaoImplPackage().replace('.', File.separatorChar);
	}
	
	public String getMappingDirectory() {
		return getCodeGenDirectory() + File.separator + DEFAULT_HIBERNATE_MAPPING_DIR;
	}
	public String getActionsDirectory(){
		return getCodeGenDirectory() + File.separator + getActionsPackage().replace('.', File.separatorChar);
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}