package cz.fi.muni.TACOS.dto;


import java.util.Arrays;
import java.util.Objects;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCategoryCreateDTO {

	private String name;

	private Byte[] image;

	private Long parentCategoryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCategoryCreateDTO that = (ProductCategoryCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Arrays.equals(image, that.image) &&
				Objects.equals(parentCategoryId, that.parentCategoryId);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(name, parentCategoryId);
		result = 31 * result + Arrays.hashCode(image);
		return result;
	}

	@Override
	public String toString() {
		return "ProductCategoryCreateDTO{" +
				"name='" + name + '\'' +
				", image=" + Arrays.toString(image) +
				", parentCategoryId=" + parentCategoryId +
				'}';
	}
}
