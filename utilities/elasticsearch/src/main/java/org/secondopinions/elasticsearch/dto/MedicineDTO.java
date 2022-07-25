package org.secondopinions.elasticsearch.dto;


public class MedicineDTO {

	private String id;

	private String name;
	private String manufacturers;

	private double mrp;
	private String prescription_required;
	private String primary_use;
	private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

}
