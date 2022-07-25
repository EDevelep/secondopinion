package com.dodaso.codegen;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dodaso.codegen.util.CodeGenUtil;
import com.dodaso.codegen.util.DbUtil;
import com.dodaso.codegen.writers.BaseDAOObject;
import com.dodaso.codegen.writers.BaseDomainObject;
import com.dodaso.codegen.writers.BaseKeyObject;
import com.dodaso.codegen.writers.DAOInterfaceObject;
import com.dodaso.codegen.writers.DAOObject;
import com.dodaso.codegen.writers.DomainObject;
import com.dodaso.codegen.writers.KeyObject;
import com.dodaso.codegen.writers.MappingFileWriter;
import com.dodaso.util.FileUtil;
import com.dodaso.util.StringUtil;

public class CodeGen {
	
	//static final String DB_URL = "jdbc:mysql://localhost:3306/dodaso";
	
	private static boolean view = false;
	private static String[] viewKeyFields;
	
	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.load(CodeGen.class.getClassLoader().getResourceAsStream("app.properties"));		
		
		DataBaseProperties properties = getDataBaseProperties(props);
		String[] tableNames = getTables(props);
		CodeGenProperties codeGenProperties = getCodeGenProperties(props);
		codeGenProperties.setSchemaName(getSchemaName(props));
		AuditFileldDefinition auditFileldDefinition =  getAuditFieldDefinitions();
		
		List<String> errors = new ArrayList<String>();
		CodeGen codeGen = new CodeGen();
		Connection connection = null;
		try {
			CodeGenUtil.checkForRequiredDirectories(codeGenProperties);
			connection = properties.getConnection();
			for (String tableName : tableNames) {
				
				try{
					codeGenProperties.setTableName(tableName);
					codeGen.generateCode(connection, codeGenProperties, auditFileldDefinition);
				}catch(Exception ex){
					ex.printStackTrace();
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
				System.out.println(codeGen.getHibernateMappings());
				System.out.println("\n-----------------------------------------------------------\n");
				System.out.println("Add the following to DaoConfig.xml:");
				System.out.println("-----------------------------------");
				System.out.println(codeGen.getDaoConfig());
			}
		} finally {
			DbUtil.closeConnection(connection);
		}
	}
	
	private static AuditFileldDefinition getAuditFieldDefinitions(){
		AuditFileldDefinition definition = new AuditFileldDefinition();
		
		definition.setCreatedBy("CreatedBy");
		definition.setCreatedDate("CreatedTs");
		definition.setUpdatedBy("UpdatedBy");
		definition.setUpdatedDate("UpdatedTs");
		
		return definition;
	}
	
	private static DataBaseProperties getDataBaseProperties(Properties props){
		DataBaseProperties properties = new DataBaseProperties(props.getProperty("com.dodaso.codegen.DB_URL"));
		properties.setUserName(props.getProperty("com.dodaso.codegen.db.user"));
		properties.setPassword(props.getProperty("com.dodaso.codegen.db.password"));
		
//		properties.setUserName("root");
//		properties.setPassword("password");
		
	
		return properties;
	}
	
	private static CodeGenProperties getCodeGenProperties(Properties props){
		String tempDir = props.getProperty("com.dodaso.codegen.dir"); //"d:\\ram\\temp\\dodaso";
		CodeGenProperties codeGenProperties = new CodeGenProperties(tempDir);
		codeGenProperties.setUsingAnnotations(true);
		//codeGenProperties.setRegenerating(true);
		
		return codeGenProperties;
	}
	
	private static String[] getTables(Properties props){
		view = new Boolean(props.getProperty("com.dodaso.codegen.view"));
		String viewKeyFieldsTemp = props.getProperty("com.dodaso.codegen.viewKeyFields");
		
		if(!StringUtil.isNullOrEmpty(viewKeyFieldsTemp)){
			viewKeyFields = viewKeyFieldsTemp.split(","); 
		}
		
		return props.getProperty("com.dodaso.codegen.objectNames").split(",");
	}
	
	private static String getSchemaName(Properties props){
		
		return props.getProperty("com.dodaso.codegen.schemaName");
	}
	

	private StringBuilder hibernateMappings = new StringBuilder();
	private StringBuilder daoConfig = new StringBuilder();

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

	public void generateCode(Connection connection, CodeGenProperties codeGenProperties, AuditFileldDefinition auditFileldDefinition) {
		try {
			TableObject object =  DbUtil.getMetaDataInfo(codeGenProperties.getSchemaName(), codeGenProperties.getTableName(), connection, auditFileldDefinition, view, viewKeyFields);
			object.setSequence(codeGenProperties.getTableSequence());

			List<ObjectWriter> list = getWriters(codeGenProperties.isRegenerating(), codeGenProperties.isUsingAnnotations());

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

			hibernateMappings.append(object.getMappingStr(codeGenProperties.getDataObjectPackage()));
			daoConfig.append(object.getDaoConfig(codeGenProperties.getDaoImplPackage()));
		} finally {

		}
	}

	private static void writeObject(ObjectWriter objectWriter, CodeGenProperties codeGenProperties, TableObject object) {
		String fileName = objectWriter.getFileName(codeGenProperties, object);
		
		Writer writer = FileUtil.getPrintWriter(fileName);
		objectWriter.writePackageName(codeGenProperties, object, writer);
		objectWriter.writeImports(codeGenProperties, object, writer);
		objectWriter.openClassNameDef(object, writer);
		objectWriter.writeBody(object, writer);
		objectWriter.closeClassNameDef(object, writer);
		FileUtil.closeWriter(writer);
	}

	private static List<ObjectWriter> getWriters(boolean isRegenrated,
			boolean usingAnnotations) {
		List<ObjectWriter> list = new ArrayList<ObjectWriter>();
		if (!isRegenrated) {
			list.add(new DAOInterfaceObject());
			list.add(new DAOObject());
			list.add(new DomainObject());
		}
		list.add(new BaseDAOObject());
		
		list.add(new BaseDomainObject());

		if (!usingAnnotations)
			list.add(new MappingFileWriter());

		return list;
	}

}