package cz.fi.muni.TACOS.service;

import cz.fi.muni.TACOS.persistence.entity.CreatedProduct;
import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.enums.OrderState;

import java.util.List;

/**
 * EntityService interface for managing Order entities
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface OrderService extends EntityService<Order> {

	/**
	 * Find all orders in given state
	 *
	 * @param state  OrderState to be used for search
	 * @return List of orders with given state
	 */
	List<Order> getAllForState(OrderState state);

	/**
	 * Add Product to Order's list of products
	 *
	 * @param order Order
	 * @param product CreatedProduct
	 */
	void addProduct(Order order, CreatedProduct product);

	/**
	 * Remove Product from Order's list of products
	 *
	 * @param order Order
	 * @param product CreatedProduct
	 */
	void removeProduct(Order order, CreatedProduct product);

	/**
	 * Submits given order.
	 *
	 * @param order order
	 */
	void submitOrder(Order order);

	/**
	 * Cancels the given order.
	 *
	 * @param order order
	 */
	void cancelOrder(Order order);

	/**
	 * Finishes the given order.
	 *
	 * @param order order
	 */
	void finishOrder(Order order);

	/**
	 * Sets given order's state to Processed.
	 *
	 * @param order order
	 */
	void processOrder(Order order);
}
