package com.dodaso.codeGenerators;

import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.dodaso.codeGenerators.exception.CodeGeneratorException;
import com.dodaso.codegen.AuditFileldDefinition;
import com.dodaso.codegen.CodeGenProperties;
import com.dodaso.codegen.DataBaseProperties;
import com.dodaso.codegen.ObjectWriter;
import com.dodaso.codegen.TableObject;
import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.codegen.writers.BaseDAOObject;
import com.dodaso.codegen.writers.BaseDomainObject;
import com.dodaso.codegen.writers.BaseKeyObject;
import com.dodaso.codegen.writers.DAOInterfaceObject;
import com.dodaso.codegen.writers.DAOObject;
import com.dodaso.codegen.writers.DomainObject;
import com.dodaso.codegen.writers.KeyObject;
import com.dodaso.codegen.writers.MappingFileWriter;
import com.dodaso.util.DbUtil;
import com.dodaso.util.FileUtil;
import com.dodaso.util.StringUtil;

public abstract class CodeGenerator {
//	static final String ptcDevUrl = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=nysddbcl04-vip)(PORT=1521))"
//		+ "(ADDRESS=(PROTOCOL=TCP)(HOST=nysddbcl05-vip)(PORT=1521))"
//		+ "(ADDRESS=(PROTOCOL=TCP)(HOST=nysddbcl06-vip)(PORT=1521))"
//		+ "(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)"
//		+ "(SERVICE_NAME=nyptcdev.gfinet.com)))";
	
	private DataBaseProperties baseProperties;
	private CodeGenProperties codeGenProperties;
	
	private String sourceDir;
	private boolean useAnnotations = true;
	private boolean regenerate = false;
	
	private StringBuilder hibernateMappings = new StringBuilder();
	private StringBuilder daoConfig = new StringBuilder();
	private StringBuilder springDaoConfig = new StringBuilder();
	
	public CodeGenerator(String sourceDir, boolean useAnnotations,  boolean regenerate){
		this.sourceDir = sourceDir;
		this.useAnnotations = useAnnotations;
		this.regenerate = regenerate;
		intialize();
	}
	
	public CodeGenerator(String sourceDir, boolean regenerate){
		this.sourceDir = sourceDir;
		this.regenerate = regenerate;
		intialize();
	}
	
	/**
	 * @return the regenerate
	 */
	public boolean isRegenerate() {
		return regenerate;
	}

	/**
	 * @param regenerate the regenerate to set
	 */
	public void setRegenerate(boolean regenerate) {
		this.regenerate = regenerate;
	}

	public CodeGenerator(String sourceDir){
		this.sourceDir = sourceDir;
		intialize();
	}
	
	public CodeGenerator(){
		intialize();
	}
	
	private void intialize(){
		baseProperties = getDataBaseProperties();
		codeGenProperties = getCodeGenProperties();
	}
	
	public abstract TableObject getDbObjectInfo(String schemaName, String objectName, Connection connection); 
	
	private void generateCode(String schemaName, String objectName, Connection connection){
		try {
			codeGenProperties.setTableName(objectName);
			TableObject object = getDbObjectInfo(schemaName, objectName, connection);
			object.setSequence(codeGenProperties.getTableSequence());

			List<ObjectWriter> list = getWriters();

			for (ObjectWriter writer : list) {
				writer.setUsingAnnotations(codeGenProperties.isUsingAnnotations());
				writeObject(writer, codeGenProperties, object);
			}
			
			if (object.getKeyField().isCompositeKey()) {
				ObjectWriter writer = new BaseKeyObject();
				writer.setUsingAnnotations(codeGenProperties.isUsingAnnotations());
				writeObject(writer, codeGenProperties, object);
				
				if(!codeGenProperties.isRegenerating()){
					writer = new KeyObject();
					writer.setUsingAnnotations(codeGenProperties.isUsingAnnotations());
					writeObject(writer, codeGenProperties, object);
				}
			}

			addHibernateMapping(object.getMappingStr(codeGenProperties.getDataObjectPackage()));
			addDaoConfig(object.getDaoConfig(codeGenProperties.getDaoImplPackage()));
			addSpringBeanDefinition(object.getSpringBeanDefinition(codeGenProperties.getDaoImplPackage()));
		} finally {

		}
	}
	
	
	public void generateCode(String schemaName, String[] objectNames){
		
		List<String> errors = new ArrayList<String>();
		Connection connection = null;
		try {
			CodeGenUtil.checkForRequiredDirectories(codeGenProperties);
			connection = baseProperties.getConnection();
			for (String tableName : objectNames) {
				
				try{
					generateCode(schemaName, tableName, connection);
				}catch(Exception ex){
					errors.add(ex.getMessage());
				}
			}
			
			for(String err : errors){
				System.out.println("Error: " + err);
			}
			System.out.println("\n\n\n");
			if (!codeGenProperties.isRegenerating()) {
				System.out.println("\n\nPlease add the following to the Config files as mentioned:");
				System.out.println("----------------------------------------------------------\n");
				System.out.println("Add the following to hibernate.cfg.xml:");
				System.out.println("---------------------------------------");
				System.out.println(getHibernateMappings());
				System.out.println("\n-----------------------------------------------------------\n");
				System.out.println("Add the following to DaoConfig.xml:");
				System.out.println("-----------------------------------");
				System.out.println(getDaoConfig());
				System.out.println("Add the following to SpringDaoConfig.xml:");
				System.out.println("-----------------------------------");
				System.out.println(getSpringBeanDefinition());
				
			}
			
		} finally {
			DbUtil.closeConnection(connection);
		}
	}
	
	protected DataBaseProperties getDataBaseProperties() {
		if(baseProperties == null){
			throw new CodeGeneratorException("DB Configuration not provided..");
		}
		return baseProperties;
	}

	protected  AuditFileldDefinition getAuditFieldDefinitions(){
		AuditFileldDefinition definition = new AuditFileldDefinition();
		
		definition.setCreatedBy("CreatedBy");
		definition.setCreatedDate("CreatedTs");
		definition.setUpdatedBy("UpdatedBy");
		definition.setUpdatedDate("UpdatedTs");
		
		return definition;
	}
	
//	protected  DataBaseProperties getDefaultDataBaseProperties(){
//		DataBaseProperties properties = new DataBaseProperties(ptcDevUrl);
//		properties.setUserName("nyptcconfhub");
//		properties.setPassword("c0nf1rmhub4nyp1c");
//		
//		properties.setPort(1521);
//		properties.setServerName("nysddbcl04-vip");
//		properties.setServiceName("nyptcdev.gfinet.com");
//		
//		return properties;
//	}
	
	protected  CodeGenProperties getCodeGenProperties(){
		CodeGenProperties codeGenProperties = new CodeGenProperties(getSourceDir());
		codeGenProperties.setUsingAnnotations(useAnnotations);
		codeGenProperties.setRegenerating(regenerate);
		
		return codeGenProperties;
	}
	
	protected String getSourceDir(){
		if(StringUtil.isNullOrEmpty(sourceDir))
			return "c:\\temp\\test";
		else
			return sourceDir;
	}
	
	protected  void writeObject(ObjectWriter objectWriter, CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = objectWriter.getFileName(codeGenProperties, object);
		Writer writer = FileUtil.getPrintWriter(fileName);
		objectWriter.writePackageName(codeGenProperties, object, writer);
		objectWriter.writeImports(codeGenProperties, object, writer);
		objectWriter.openClassNameDef(object, writer);
		objectWriter.writeBody(object, writer);
		objectWriter.closeClassNameDef(object, writer);
		FileUtil.closeWriter(writer);
	}

	protected List<ObjectWriter> getWriters() {
		List<ObjectWriter> list = new ArrayList<ObjectWriter>();
		
		if (!regenerate) {
			list.add(new DAOInterfaceObject());
			list.add(new DAOObject());
			list.add(new DomainObject());
		}
		
		list.add(new BaseDAOObject());
		list.add(new BaseDomainObject());

		if (!useAnnotations)
			list.add(new MappingFileWriter());

		return list;
	}
	
	/**
	 * @return the hibernateMappings
	 */
	public String getHibernateMappings() {
		return hibernateMappings.toString();
	}

	/**
	 * @param daoConfig
	 *            the daoConfig to set
	 */
	public String getDaoConfig() {
		return daoConfig.toString();
	}
	
	/**
	 * @param springDaoConfig
	 *            the springDaoConfig to set
	 */
	public String getSpringBeanDefinition() {
		return springDaoConfig.toString();
	}
	
	protected void addHibernateMapping(String mapping){
		hibernateMappings.append(mapping);
	}
	
	protected void addDaoConfig(String daoConfigStr){
		daoConfig.append(daoConfigStr);
	}
	
	protected void addSpringBeanDefinition(String springDaoConfigStr){
		springDaoConfig.append(springDaoConfigStr);
	}
}
