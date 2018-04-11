package cz.fi.muni.TACOS.service.Impl;

import cz.fi.muni.TACOS.persistence.dao.OrderDao;
import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.persistence.enums.OrderState;
import cz.fi.muni.TACOS.service.OrderService;

import javax.inject.Inject;
import java.util.List;

/**
 * Service layer for Order Entity
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Inject
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    public void create(Order order) {
        orderDao.create(order);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }

    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

	@Override
	public List<Order> getAllForState(OrderState state) {
		return orderDao.getAllForState(state);
	}

	@Override
	public void addProduct(Order order, CreatedProduct product) {
		if (order.getProducts().contains(product)) {
			throw new IllegalArgumentException("Product is already in products for order");
		}
		order.addProduct(product);
	}

	@Override
	public void removeProduct(Order order, CreatedProduct product) {
		if (!order.getProducts().contains(product)) {
			throw new IllegalArgumentException("Product is not in products for order");
		}
		order.removeProduct(product);
	}
}
