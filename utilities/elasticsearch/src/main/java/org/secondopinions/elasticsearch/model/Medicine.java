package org.secondopinions.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "medicine", type = "medicine", shards = 2)
public class Medicine {

	@Id
	private String id;
	private String bread_crumb;
	@Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
	private String name;
	private String manufacturers;
	private String salt_composition;
	
	private String packaging;
	private double mrp;
	private String prescription_required;
	private String primary_use;
	private String description;
	private String introduction;
	private String use_of;
	public Medicine() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBread_crumb() {
		return bread_crumb;
	}
	public void setBread_crumb(String bread_crumb) {
		this.bread_crumb = bread_crumb;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers;
	}
	public String getSalt_composition() {
		return salt_composition;
	}
	public void setSalt_composition(String salt_composition) {
		this.salt_composition = salt_composition;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public double getMrp() {
		return mrp;
	}
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	public String getPrescription_required() {
		return prescription_required;
	}
	public void setPrescription_required(String prescription_required) {
		this.prescription_required = prescription_required;
	}
	public Medicine(String id, String bread_crumb, String name, String manufacturers, String salt_composition,
			String packaging, double mrp, String prescription_required, String primary_use, String description,
			String introduction, String use_of) {
		super();
		this.id = id;
		this.bread_crumb = bread_crumb;
		this.name = name;
		this.manufacturers = manufacturers;
		this.salt_composition = salt_composition;
		this.packaging = packaging;
		this.mrp = mrp;
		this.prescription_required = prescription_required;
		this.primary_use = primary_use;
		this.description = description;
		this.introduction = introduction;
		this.use_of = use_of;
	}
	public String getPrimary_use() {
		return primary_use;
	}
	public void setPrimary_use(String primary_use) {
		this.primary_use = primary_use;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getUse_of() {
		return use_of;
	}
	public void setUse_of(String use_of) {
		this.use_of = use_of;
	}

}
