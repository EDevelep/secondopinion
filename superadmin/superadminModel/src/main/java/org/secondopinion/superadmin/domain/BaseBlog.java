package org.secondopinion.superadmin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.secondopinion.utils.threadlocal.AppThreadLocal;
import org.secondopinion.domain.BaseDomainObject;
import org.secondopinion.domain.KeyField;
import org.secondopinion.domain.validation.ValidationMessage;
import org.secondopinion.superadmin.dto.Blog;
import org.secondopinion.utils.StringUtil;
import org.secondopinion.utils.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings({ "serial" })
@MappedSuperclass
public abstract class BaseBlog extends BaseDomainObject<Long> {

	public static final String FIELD_blogId = "blogId";
	public static final String FIELD_description = "description";
	public static final String FIELD_title = "title";
	public static final String FIELD_active = "active";
	public static final String FIELD_image = "image";
	public static final String FIELD_createdBy = "createdBy";
	public static final String FIELD_createdDate = "createdDate";
	public static final String FIELD_lastUpdatedBy = "lastUpdatedBy";
	public static final String FIELD_lastUpdatedTs = "lastUpdatedTs";

	private Long blogId;
	private String description;
	private String title;
	private String active;
	private byte[] image;

	public void setBlogId(Long _blogId) {
		this.blogId = _blogId;
	}

	@Id
	@Column(name = "blogId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getBlogId() {
		return this.blogId;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}

	@NotNull
	@Length(max = 245)
	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setTitle(String _title) {
		this.title = _title;
	}

	@NotNull
	@Length(max = 245)
	@Column(name = "title")
	public String getTitle() {
		return this.title;
	}

	public void setActive(String _active) {
		this.active = _active;
	}

	@NotNull
	@Length(max = 45)
	@Column(name = "active")
	public String getActive() {
		return this.active;
	}

	public void setImage(byte[] _image) {
		this.image = _image;
	}

	@Column(name = "image")
	public byte[] getImage() {
		return this.image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseBlog other = (BaseBlog) o;
		if (ObjectUtil.isEqual(getDescription(), other.getDescription())
				&& ObjectUtil.isEqual(getTitle(), other.getTitle())
				&& ObjectUtil.isEqual(getActive(), other.getActive())
				&& ObjectUtil.isEqual(getImage(), other.getImage())
				&& ObjectUtil.isEqual(getCreatedBy(), other.getCreatedBy())
				&& ObjectUtil.isEqual(getCreatedDate(), other.getCreatedDate())
				&& ObjectUtil.isEqual(getLastUpdatedBy(), other.getLastUpdatedBy())
				&& ObjectUtil.isEqual(getLastUpdatedTs(), other.getLastUpdatedTs())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = result + (blogId != null ? blogId.hashCode() : 0);

		return result;
	}

	public void validate(boolean validatePk) {
		resetValidationMessages();

		List<ValidationMessage> list = new ArrayList<ValidationMessage>();
		if (validatePk) {
			if (this.blogId == null) {
				list.add(new ValidationMessage("Field " + FIELD_blogId + " cannot be null"));
			}

		}
		if (StringUtil.isNullOrEmpty(this.description)) {
			list.add(new ValidationMessage("Field " + FIELD_description + " cannot be null"));
		}

		if ((this.description != null) && (this.description.length() > 245)) {
			list.add(new ValidationMessage("Field " + FIELD_description + " cannot be longer than: " + 245));
		}

		if (StringUtil.isNullOrEmpty(this.title)) {
			list.add(new ValidationMessage("Field " + FIELD_title + " cannot be null"));
		}

		if ((this.title != null) && (this.title.length() > 245)) {
			list.add(new ValidationMessage("Field " + FIELD_title + " cannot be longer than: " + 245));
		}

		if (StringUtil.isNullOrEmpty(this.active)) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be null"));
		}

		if ((this.active != null) && (this.active.length() > 45)) {
			list.add(new ValidationMessage("Field " + FIELD_active + " cannot be longer than: " + 45));
		}

		if (this.isFromDB()) {
			if ((this.createdBy != null) && (this.createdBy.length() > 45)) {
				list.add(new ValidationMessage("Field " + FIELD_createdBy + " cannot be longer than: " + 45));
			}
		}
		if (this.isFromDB()) {
			if ((this.lastUpdatedBy != null) && (this.lastUpdatedBy.length() > 100)) {
				list.add(new ValidationMessage("Field " + FIELD_lastUpdatedBy + " cannot be longer than: " + 100));
			}
		}
		if (list.size() > 0)
			setHasValidationErrors(true);

		setValidationMessages(list);

	}

	@Override
	public final void setAuditFields() {
		if (!isFromDB()) {
			this.createdBy = AppThreadLocal.getUserName();
		}
		this.lastUpdatedBy = AppThreadLocal.getUserName();
		this.lastUpdatedTs = AppThreadLocal.getRequestStartDate();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("blogId = " + blogId + "\n");
		;
		str.append("description = " + description + "\n");
		str.append("title = " + title + "\n");
		str.append("active = " + active + "\n");
		str.append("image = " + image + "\n");
		str.append("createdBy = " + createdBy + "\n");
		str.append("createdDate = " + createdDate + "\n");
		str.append("lastUpdatedBy = " + lastUpdatedBy + "\n");
		str.append("lastUpdatedTs = " + lastUpdatedTs + "\n");
		return str.toString();
	}

	@Transient
	@Override
	public final boolean isKeyNull() {
		return (blogId == null);
	}

	@Transient
	@Override
	public final List<KeyField> getKeyField() {
		List<KeyField> list = new ArrayList<KeyField>();
		list.add(new KeyField(FIELD_blogId, getBlogId()));
		return list;
	}

	@Transient
	@Override
	public Long getId() {
		return getBlogId();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Blog blog = new Blog();
		blog.setFromDB(false);
		blog.setDescription(getDescription());
		blog.setTitle(getTitle());
		blog.setActive(getActive());
		blog.setImage(getImage());
		blog.setCreatedDate(getCreatedDate());
		// afterClone(blog);
		return blog;
	}
}