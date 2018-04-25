package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.Order;
import cz.fi.muni.TACOS.enums.OrderState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of UserDao interface
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
@Stateless
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public List<Order> getAllForState(OrderState state) {
		if (state == null) {
			throw new IllegalArgumentException("Parameter state cannot be null!");
		}
		return em
				.createQuery("select o from Order o where o.state = :state", Order.class)
				.setParameter("state", state)
				.getResultList();
	}

	@Override
	public List<Order> getAllWithoutNewOrders() {

		return em
				.createQuery("select o from Order o where o.state <> :state", Order.class)
				.setParameter("state", OrderState.BASKET)
				.getResultList();
	}

}
