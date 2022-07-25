package org.secondopinions.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Setting(settingPath = "es-config/elastic-analyzer.json")
@Document(indexName="diagnosticcentertest",type="diagnosticcentertest",shards=2)
public class DiagnosticcenterTest {
	
	@Id
	private String id;
	@Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
	private String diagnosticcentertestname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiagnosticcentertestname() {
		return diagnosticcentertestname;
	}
	public void setDiagnosticcentertestname(String diagnosticcentertestname) {
		this.diagnosticcentertestname = diagnosticcentertestname;
	}

}
