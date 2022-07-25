package org.secondopinion.reports.graph.data;

public class Col {
	private String id;
	private String label;
	private String type;
	private String pattern;

	public Col() {
		// TODO Auto-generated method stub

	}
	
	public Col(String id, String type, String lable) {
		this.id = id;
		this.type = type;
		this.label = lable;
		this.pattern = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public String toString() {
		return "Col [id=" + id + ", label=" + label + ", type=" + type + ", pattern=" + pattern + "]";
	}

}
