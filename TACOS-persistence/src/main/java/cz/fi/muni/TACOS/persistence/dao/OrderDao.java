package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.enums.OrderState;

import java.util.List;

/**
 * Interface of OrderDao
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface OrderDao extends Dao<Order> {

	/**
	 * Gets all Order entities stored in database with given state
	 * @param state
	 * @return List of Orders or empty list if there is not order with that state
	 *
	 * @throws IllegalArgumentException when given parameter is null
	 */
	List<Order> getAllForState(OrderState state);

	/**
	 * Gets all Order entities stored in database with state != NEW
	 * @return List of Orders or empty list if there is not order with that state
	 *
	 * @throws IllegalArgumentException when given parameter is null
	 */
	List<Order> getAllWithoutNewOrders();
}
