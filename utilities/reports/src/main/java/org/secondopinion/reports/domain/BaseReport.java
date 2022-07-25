package org.secondopinion.reports.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.reports.dto.CompanyTemplate;
import org.secondopinion.utils.ObjectUtil;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.reports.dto.Report;

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseReport extends BaseDomainObject<Long> {

	public static final String FIELD_reportId = "reportId";
	public static final String FIELD_companyId = "companyId";
	public static final String FIELD_name = "name";
	public static final String FIELD_header = "header";
	public static final String FIELD_type = "type";
	public static final String FIELD_sourceName = "sourceName";
	public static final String FIELD_columns = "columns";
	public static final String FIELD_columnAliases = "columnAliases";
	public static final String FIELD_conditions = "conditions";
	public static final String FIELD_groupColumns = "groupColumns";
	public static final String FIELD_orderByColumns = "orderByColumns";
	public static final String FIELD_transposeData = "transposeData";
	public static final String FIELD_transposeWithTotals = "transposeWithTotals";
	public static final String FIELD_keyColumns = "keyColumns";
	public static final String FIELD_sumOnColumn = "sumOnColumn";
	public static final String FIELD_count = "count";
	public static final String FIELD_graphType = "graphType";
	public static final String FIELD_email = "email";
	public static final String FIELD_displayColumns = "displayColumns";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long reportId;
	private Long companyId;
	private String name;
	private String header;
	private String type;
	private String sourceName;
	private String columns;
	private String columnAliases;
	private String conditions;
	private String groupColumns;
	private String orderByColumns;
	private String transposeData;
	private Character transposeWithTotals;
	private String keyColumns;
	private String sumOnColumn;
	private Character count;
	private String graphType;
	private Character email;
	private Integer displayColumns;

	public void setReportId(Long _reportId) {
		this.reportId = _reportId;
	}

	@Id
//	@NotNull
	@Column(name = "reportId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getReportId() {
		return this.reportId;
	}

	public void setCompanyId(Long _companyId) {
		this.companyId = _companyId;
	}

	@NotNull
	@Column(name = "companyId")
	public Long getCompanyId() {
		return this.companyId;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	@NotNull
	@Length(max = 150)
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setHeader(String _header) {
		this.header = _header;
	}

	@Length(max = 150)
	@Column(name = "header")
	public String getHeader() {
		return this.header;
	}

	public void setType(String _type) {
		this.type = _type;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setSourceName(String _sourceName) {
		this.sourceName = _sourceName;
	}

	@NotNull
	@Length(max = 150)
	@Column(name = "sourceName")
	public String getSourceName() {
		return this.sourceName;
	}

	public void setColumns(String _columns) {
		this.columns = _columns;
	}

	@NotNull
	@Length(max = 750)
	@Column(name = "columns")
	public String getColumns() {
		return this.columns;
	}

	public void setColumnAliases(String _columnAliases) {
		this.columnAliases = _columnAliases;
	}

	@Length(max = 500)
	@Column(name = "columnAliases")
	public String getColumnAliases() {
		return this.columnAliases;
	}

	public void setConditions(String _conditions) {
		this.conditions = _conditions;
	}

	@Length(max = 250)
	@Column(name = "conditions")
	public String getConditions() {
		return this.conditions;
	}

	public void setGroupColumns(String _groupColumns) {
		this.groupColumns = _groupColumns;
	}

	@Length(max = 250)
	@Column(name = "groupColumns")
	public String getGroupColumns() {
		return this.groupColumns;
	}

	public void setOrderByColumns(String _orderByColumns) {
		this.orderByColumns = _orderByColumns;
	}

	@Length(max = 255)
	@Column(name = "orderByColumns")
	public String getOrderByColumns() {
		return this.orderByColumns;
	}

	public void setTransposeData(String _transposeData) {
		this.transposeData = _transposeData;
	}

	@Length(max = 250)
	@Column(name = "transposeData")
	public String getTransposeData() {
		return this.transposeData;
	}

	public void setTransposeWithTotals(Character _transposeWithTotals) {
		this.transposeWithTotals = _transposeWithTotals;
	}

	@Column(name = "transposeWithTotals")
	public Character getTransposeWithTotals() {
		return this.transposeWithTotals;
	}

	public void setKeyColumns(String _keyColumns) {
		this.keyColumns = _keyColumns;
	}

	@Length(max = 250)
	@Column(name = "keyColumns")
	public String getKeyColumns() {
		return this.keyColumns;
	}

	public void setSumOnColumn(String _sumOnColumn) {
		this.sumOnColumn = _sumOnColumn;
	}

	@Length(max = 45)
	@Column(name = "sumOnColumn")
	public String getSumOnColumn() {
		return this.sumOnColumn;
	}

	public void setCount(Character _count) {
		this.count = _count;
	}

	@Column(name = "count")
	public Character getCount() {
		return this.count;
	}

	public void setGraphType(String _graphType) {
		this.graphType = _graphType;
	}

	@Length(max = 45)
	@Column(name = "graphType")
	public String getGraphType() {
		return this.graphType;
	}

	public void setEmail(Character _email) {
		this.email = _email;
	}

	@Column(name = "email")
	public Character getEmail() {
		return this.email;
	}

	public void setDisplayColumns(Integer _displayColumns) {
		this.displayColumns = _displayColumns;
	}

	@NotNull
	@Column(name = "displayColumns")
	public Integer getDisplayColumns() {
		return this.displayColumns;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseReport other = (BaseReport) o;
		if (ObjectUtil.isEqual(getCompanyId(), other.getCompanyId()) && ObjectUtil.isEqual(getName(), other.getName())
				&& ObjectUtil.isEqual(getHeader(), other.getHeader()) && ObjectUtil.isEqual(getType(), other.getType())
				&& ObjectUtil.isEqual(getSourceName(), other.getSourceName())
				&& ObjectUtil.isEqual(getColumns(), other.getColumns())
				&& ObjectUtil.isEqual(getColumnAliases(), other.getColumnAliases())
				&& ObjectUtil.isEqual(getConditions(), other.getConditions())
				&& ObjectUtil.isEqual(getGroupColumns(), other.getGroupColumns())
				&& ObjectUtil.isEqual(getOrderByColumns(), other.getOrderByColumns())
				&& ObjectUtil.isEqual(getTransposeData(), other.getTransposeData())
				&& ObjectUtil.isEqual(getTransposeWithTotals(), other.getTransposeWithTotals())
				&& ObjectUtil.isEqual(getKeyColumns(), other.getKeyColumns())
				&& ObjectUtil.isEqual(getSumOnColumn(), other.getSumOnColumn())
				&& ObjectUtil.isEqual(getCount(), other.getCount())
				&& ObjectUtil.isEqual(getGraphType(), other.getGraphType())
				&& ObjectUtil.isEqual(getEmail(), other.getEmail())
				&& ObjectUtil.isEqual(getDisplayColumns(), other.getDisplayColumns())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (reportId != null ? reportId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.reportId == null) {
				list.add(new ValidationMessage("Field " + FIELD_reportId + " cannot be null"));
			}

		}
		if (this.companyId == null) {
			list.add(new ValidationMessage("Field " + FIELD_companyId + " cannot be null"));
		}

		if (StringUtil.isNullOrEmpty(this.name)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be null"));
		}

		if ((this.name != null) && (this.name.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_name + " cannot be longer than: " + 150));
		}

		if ((this.header != null) && (this.header.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_header + " cannot be longer than: " + 150));
		}

		if (StringUtil.isNullOrEmpty(this.type)) {
			list.add(new ValidationMessage("Field " + FIELD_type + " cannot be null"));
		}

		if ((this.type != null) && (this.type.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_type + " cannot be longer than: " + 45));
		}

		if (StringUtil.isNullOrEmpty(this.sourceName)) {
			list.add(new ValidationMessage("Field " + FIELD_sourceName + " cannot be null"));
		}

		if ((this.sourceName != null) && (this.sourceName.length() > 150)) {
			list.add(new ValidationMessage("Field " + FIELD_sourceName + " cannot be longer than: " + 150));
		}

		if (StringUtil.isNullOrEmpty(this.columns)) {
			list.add(new ValidationMessage("Field " + FIELD_columns + " cannot be null"));
		}

		if ((this.columns != null) && (this.columns.length() > 500)) {
			list.add(new ValidationMessage("Field " + FIELD_columns + " cannot be longer than: " + 500));
		}

		if ((this.columnAliases != null) && (this.columnAliases.length() > 500)) {
			list.add(new ValidationMessage("Field " + FIELD_columnAliases + " cannot be longer than: " + 500));
		}

		if ((this.conditions != null) && (this.conditions.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_conditions + " cannot be longer than: " + 250));
		}

		if ((this.groupColumns != null) && (this.groupColumns.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_groupColumns + " cannot be longer than: " + 250));
		}

		if ((this.orderByColumns != null) && (this.orderByColumns.length() > 255)) {
			list.add(new ValidationMessage("Field " + FIELD_orderByColumns + " cannot be longer than: " + 255));
		}

		if ((this.transposeData != null) && (this.transposeData.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_transposeData + " cannot be longer than: " + 250));
		}

		if ((this.keyColumns != null) && (this.keyColumns.length() > 250)) {
			list.add(new ValidationMessage("Field " + FIELD_keyColumns + " cannot be longer than: " + 250));
		}

		if ((this.sumOnColumn != null) && (this.sumOnColumn.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_sumOnColumn + " cannot be longer than: " + 45));
		}

		if ((this.graphType != null) && (this.graphType.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_graphType + " cannot be longer than: " + 45));
		}

		if (this.displayColumns == null) {
			list.add(new ValidationMessage("Field " + FIELD_displayColumns + " cannot be null"));
		}

		if (this.isFromDB()) {
			if (StringUtil.isNullOrEmpty(this.lastUpdatedBy)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be null"));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if (this.lastUpdatedTs == null) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedTs + " cannot be null"));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("reportId = " + reportId + "\n");
		str.append("companyId = " + companyId + "\n");
		str.append("name = " + name + "\n");
		str.append("header = " + header + "\n");
		str.append("type = " + type + "\n");
		str.append("sourceName = " + sourceName + "\n");
		str.append("columns = " + columns + "\n");
		str.append("columnAliases = " + columnAliases + "\n");
		str.append("conditions = " + conditions + "\n");
		str.append("groupColumns = " + groupColumns + "\n");
		str.append("orderByColumns = " + orderByColumns + "\n");
		str.append("transposeData = " + transposeData + "\n");
		str.append("transposeWithTotals = " + transposeWithTotals + "\n");
		str.append("keyColumns = " + keyColumns + "\n");
		str.append("sumOnColumn = " + sumOnColumn + "\n");
		str.append("count = " + count + "\n");
		str.append("graphType = " + graphType + "\n");
		str.append("email = " + email + "\n");
		str.append("displayColumns = " + displayColumns + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (reportId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_reportId, getReportId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getReportId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Report report = new Report();
		report.setFromDB(false);
		report.setCompanyId(getCompanyId());
		report.setName(getName());
		report.setHeader(getHeader());
		report.setType(getType());
		report.setSourceName(getSourceName());
		report.setColumns(getColumns());
		report.setColumnAliases(getColumnAliases());
		report.setConditions(getConditions());
		report.setGroupColumns(getGroupColumns());
		report.setOrderByColumns(getOrderByColumns());
		report.setTransposeData(getTransposeData());
		report.setTransposeWithTotals(getTransposeWithTotals());
		report.setKeyColumns(getKeyColumns());
		report.setSumOnColumn(getSumOnColumn());
		report.setCount(getCount());
		report.setGraphType(getGraphType());
		report.setEmail(getEmail());
		report.setDisplayColumns(getDisplayColumns());
		// afterClone(report);
		return report;
	}
}