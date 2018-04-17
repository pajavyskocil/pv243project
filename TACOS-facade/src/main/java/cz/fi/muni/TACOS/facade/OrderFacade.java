package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.OrderCreateDTO;
import cz.fi.muni.TACOS.dto.OrderDTO;
import cz.fi.muni.TACOS.persistence.enums.OrderState;

import java.util.List;

/**
 * Facade interface for Order entity
 *
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface OrderFacade extends Facade<OrderDTO, OrderCreateDTO> {

	/**
	 * Find all orders in given state
	 *
	 * @param state  OrderState to be used for search
	 * @return List of orders with given state
	 */
	List<OrderDTO> getAllForState(OrderState state);

	/**
	 * Add Product to Order's list of products
	 *
	 * @param orderId order's Id
	 * @param productId createdProduct's Id
	 */
	void addProduct(Long orderId, Long productId);

	/**
	 * Remove Product from Order's list of products
	 *
	 * @param orderId order's Id
	 * @param productId createdProduct's Id
	 */
	void removeProduct(Long orderId, Long productId);

	/**
	 * Submits order with given Id
	 *
	 * @param orderId order's Id
	 */
	void submitOrder(Long orderId);

	/**
	 * Cancels the order with given Id
	 *
	 * @param orderId order's Id
	 */
	void cancelOrder(Long orderId);

	/**
	 * Finishes the order with given Id
	 *
	 * @param orderId order's Id
	 */
	void finishOrder(Long orderId);

	/**
	 * Sets given order's state to Processed
	 *
	 * @param orderId order's Id
	 */
	void processOrder(Long orderId);
}
