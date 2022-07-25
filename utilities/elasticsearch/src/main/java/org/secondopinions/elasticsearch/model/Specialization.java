package org.secondopinions.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName = "specialization", type = "specialization", shards = 2)
public class Specialization {
	
	@Id
	private String id;
	@Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
	private String doctorspecialization;
	private Integer count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoctorspecialization() {
		return doctorspecialization;
	}
	public void setDoctorspecialization(String doctorspecialization) {
		this.doctorspecialization = doctorspecialization;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
