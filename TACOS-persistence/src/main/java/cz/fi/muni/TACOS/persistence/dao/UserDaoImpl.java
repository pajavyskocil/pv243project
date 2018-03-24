package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.User;

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
public class UserDaoImpl implements UserDao {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Override
	public void create(User user) {
		if(user == null){
			throw new IllegalArgumentException("Parameter User cannot be null!");
		}
		em.persist(user);
	}

	@Override
	public void delete(User user) {
		if(user == null){
			throw new IllegalArgumentException("Parameter User cannot be null!");
		}
		em.remove(user);
	}

	@Override
	public User findById(Long id) {
		if(id == null){
			throw new IllegalArgumentException("Parameter id cannot be null!");
		}
		if(id < 0){
			throw new IllegalArgumentException("Parameter id cannot be less than 0!");
		}
		return em.find(User.class, id);
	}

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
					.createQuery("select u from User u where email = :email",
							User.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException nrf) {
			return null;
		}
	}

	@Override
	public List<User> getAll() {
		return em.createQuery("SELECT u FROM User u",
				User.class).getResultList();
	}
}

