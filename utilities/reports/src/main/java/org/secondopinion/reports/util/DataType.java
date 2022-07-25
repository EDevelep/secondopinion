package org.secondopinion.reports.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.secondopinion.reports.graph.data.Col;


public enum DataType {
	STRING(String.class, "String", GraphDataType.STRING), INT(int.class, "int", GraphDataType.NUMBER), 
	INTEGER(Integer.class, "Integer", GraphDataType.NUMBER), BIGINTEGER(BigInteger.class, "Integer", GraphDataType.NUMBER),
	FLOAT(Float.class, "float", GraphDataType.NUMBER),
	BOOLEAN(Boolean.class, "Boolean", GraphDataType.BOOLEAN), Double(Double.class, "double", GraphDataType.NUMBER), 
	Date(Date.class, "Date", GraphDataType.DATE), SQL_Date(java.sql.Date.class, "Date", GraphDataType.DATE), 
	TIMESTAMP(Timestamp.class, "Timestamp", GraphDataType.TIMESTAMP),
	LONG(long.class, "long", GraphDataType.NUMBER), BIG_DECIMAL(BigDecimal.class, "BigDecimal", GraphDataType.NUMBER), 
	CHARACTER(Character.class, "Character", GraphDataType.STRING), LIST(List.class, "List"),
	ARRAYLIST(ArrayList.class, "ArrayList"), SET(Set.class, "Set"), HASHSET(HashSet.class, "HashSet"),
	MAP(Map.class, "Map"), HASHMAP(HashMap.class, "HashMap"), COMPLEX(null, "ComplexType"), ENUM(null, "ENUM");
	
	private final Class<?> type;
	private String javaType;
	private final GraphDataType googleGraphType;
	
	private DataType(Class<?> _type, String _javaType){
		this.type = _type;
		this.javaType = _javaType;
		this.googleGraphType = null;
	}
	
	private DataType(Class<?> _type, String _javaType, GraphDataType _googleGraphType){
		this.type = _type;
		this.javaType = _javaType;
		this.googleGraphType = _googleGraphType;
	
	}
	
	private DataType(Class<?> _type){
		this.type = _type;
		this.javaType = _type.getSimpleName();
		this.googleGraphType = null;
	}
	
	public static DataType getType(Class<?> clazz){
		if(clazz.isEnum()){
			return ENUM;
		}
		
		for(DataType type : DataType.values()){
			if(type.type != null){
				if(type.isTypeOf(clazz.getCanonicalName())){
					return type;
				}
			}
		}
		
		return COMPLEX;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public Class<?> getType() {
		return type;
	}

	private boolean isTypeOf(String canonicalName) {
		return StringUtil.equalsIgnoreCase(canonicalName, type.getCanonicalName()) 
				|| StringUtil.equalsIgnoreCase(canonicalName, type.getSimpleName());
	}
	
	public boolean isACollection(){
		switch(this){
		case LIST:
		case ARRAYLIST:
		case SET:
		case HASHSET:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isCollection(Class<?> clazz){
		return getType(clazz).isACollection();
	}

	public boolean isAMap() {
		switch(this){
		case MAP:
			return true;
		default:
			return false;
		}
	}

	public boolean isAComplexType() {
		return (this == COMPLEX || isACollection() || isAMap());
	}

	public boolean isEnum() {
		return (this == ENUM);
	}
	
	public boolean isNumeric(){
		switch(this){
		case INT:
		case INTEGER:
		case LONG:
		case FLOAT:
		case Double:
		case BIG_DECIMAL:
			return true;
		default:
			return false;
		}
	}
	
	public Class<?> getCollectionsImpl(){
		if(isList()){
			switch (this) {
			case LIST:
				return ArrayList.class;
			default:
				return this.type;
			}
		}
		if(isSet()){
			switch (this) {
			case LIST:
				return HashSet.class;
			default:
				return this.type;
			}
		}
		
		if(isMap()){
			switch (this) {
			case MAP:
				return HashMap.class;
			default:
				return this.type;
			}
		}
		
		throw new RuntimeException(this + " - is not a collection..");
	}

	private boolean isSet() {
		return (this == SET || this == DataType.HASHSET);
	}

	private boolean isMap() {
		return (this == MAP || this == DataType.HASHMAP);
	}
	
	private boolean isList() {
		return (this == LIST || this == ARRAYLIST);
	}
	
	public GraphDataType getGoogleGraphType() {
		return googleGraphType;
	}
	
	public enum GraphDataType{
		STRING("string"), DATE("string"), NUMBER("number"), BOOLEAN("boolean"), TIMESTAMP("string");
		
		private final String name;
		
		GraphDataType(String _name){
			this.name = _name;
		}
		
		public String getName(){
			return name;
		}
		
		public Col getColName(String colName){
			return new Col(colName, this.name, colName);
		}
		
		public Col getColName(String colName, String label){
			return new Col(label, this.name, colName);
		}
	}
}
