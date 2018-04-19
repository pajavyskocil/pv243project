package cz.fi.muni.TACOS.dto;


import java.util.Arrays;
import java.util.Objects;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class ProductCategoryCreateDTO {

	private String name;

	private Byte[] image;

	private Long parentCategory;

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

	public Long getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Long parentCategory) {
		this.parentCategory = parentCategory;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCategoryCreateDTO that = (ProductCategoryCreateDTO) o;
		return Objects.equals(name, that.name) &&
				Arrays.equals(image, that.image) &&
				Objects.equals(parentCategory, that.parentCategory);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(name, parentCategory);
		result = 31 * result + Arrays.hashCode(image);
		return result;
	}

	@Override
	public String toString() {
		return "ProductCategoryCreateDTO{" +
				"name='" + name + '\'' +
				", image=" + Arrays.toString(image) +
				", parentCategory=" + parentCategory +
				'}';
	}
}
