package cz.fi.muni.TACOS.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class AttributeCategoryCreateDTO {

	private String name;

	private Set<Long> attributeIds = new HashSet<>();

	private Set<Long> templateIds = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Long> getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(Set<Long> attributes) {
		this.attributeIds = attributes;
	}

	public Set<Long> getTemplateIds() {
		return templateIds;
	}

	public void setTemplateIds(Set<Long> templates) {
		this.templateIds = templates;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AttributeCategoryCreateDTO that = (AttributeCategoryCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Objects.equals(attributeIds, that.attributeIds) &&
				Objects.equals(templateIds, that.templateIds);
	}

	@Override
	public int hashCode() {

		return Objects.hash(name, attributeIds, templateIds);
	}

	@Override
	public String toString() {
		return "AttributeCategoryCreateDTO{" +
				"name='" + name + '\'' +
				", attributeIds=" + attributeIds +
				", templateIds=" + templateIds +
				'}';
	}
}
