package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.enums.UserRole;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of UserDao interface
 * @author Balcirak Peter <peter.balcirak@gmail.com>
 * @author Sassmann Vojtech <vojtech.sassmann@gmail.com>
 */
@Stateless
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public User findByEmail(String email) {
		if(email == null){
			throw new IllegalArgumentException("Parameter email cannot be null!");
		}
		if(email.isEmpty()){
			throw new IllegalArgumentException("Parameter email cannot be empty!");
		}
		try {
			return em
					.createQuery("select u from User u where u.email = :email",
							User.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public List<User> getAllForRole(UserRole role) {
		if (role == null) {
			throw new IllegalArgumentException("Parameter role cannot be null!");
		}
		return em
				.createQuery("select u from User u where u.role= :role",User.class)
				.setParameter("role", role)
				.getResultList();
	}
}

