/*
 * package org.secondopinions.elasticsearch.model;
 * 
 * import org.springframework.data.annotation.Id; import
 * org.springframework.data.elasticsearch.annotations.Document; import
 * org.springframework.data.elasticsearch.annotations.Field; import
 * org.springframework.data.elasticsearch.annotations.FieldType; import
 * org.springframework.data.elasticsearch.annotations.Setting;
 * 
 * @Setting(settingPath = "es-config/elastic-analyzer.json")
 * 
 * @Document(indexName = "caretaker", type = "caretaker", shards = 2) public
 * class Caretaker {
 * 
 * @Id private String id; private Long caretakerId;
 * 
 * @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer
 * = "autocomplete_search") private String firstName; private String lastName;
 * 
 * private String cellNumber; private String officeNumber; private String
 * userName;
 * 
 * public String getId() { return id; }
 * 
 * public void setId(String id) { this.id = id; }
 * 
 * public String getUserName() { return userName; }
 * 
 * public void setUserName(String userName) { this.userName = userName; }
 * 
 * public Long getCaretakerId() { return caretakerId; }
 * 
 * public void setCaretakerId(Long caretakerId) { this.caretakerId =
 * caretakerId; }
 * 
 * public String getFirstName() { return firstName; }
 * 
 * public void setFirstName(String firstName) { this.firstName = firstName; }
 * 
 * public String getLastName() { return lastName; }
 * 
 * public void setLastName(String lastName) { this.lastName = lastName; }
 * 
 * public String getCellNumber() { return cellNumber; }
 * 
 * public void setCellNumber(String cellNumber) { this.cellNumber = cellNumber;
 * }
 * 
 * public String getOfficeNumber() { return officeNumber; }
 * 
 * public void setOfficeNumber(String officeNumber) { this.officeNumber =
 * officeNumber; }
 * 
 * }
 */