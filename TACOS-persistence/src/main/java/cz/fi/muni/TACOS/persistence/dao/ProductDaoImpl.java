package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Product;
import cz.fi.muni.TACOS.persistence.entity.ProductCategory;

import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Stateless
public class ProductDaoImpl extends AbstractDao<Product> implements ProductDao {
    @Override
    public void delete(Product entity) {

		Set<CreatedProduct> createdProductsCopy = new HashSet<>(entity.getCreatedProducts());

        for (CreatedProduct createdProduct : createdProductsCopy) {
            createdProduct.setProductFromOneSide(null);
        }

        Set<ProductCategory> productCategoriesCopy = new HashSet<>(entity.getProductCategories());

        for (ProductCategory category : productCategoriesCopy) {
            category.removeProduct(entity);
        }

        super.delete(entity);
    }
}
