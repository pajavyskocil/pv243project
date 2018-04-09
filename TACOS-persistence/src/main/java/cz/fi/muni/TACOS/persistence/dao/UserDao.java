package cz.fi.muni.TACOS.persistence.dao;

import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.persistence.enums.UserRole;

import java.util.List;

/**
 * Interface of UserDao
 * @author Balcirak Peter <peter.balcirak@gmail.com>
 * @author Sassmann Vojtech <vojtech.sassmann@gmail.com>
 */
public interface UserDao extends Dao<User> {

	/**
	 * Finds user in database by his email and returns him as object User
	 * @param email by which will be user find
	 * @return User or null if there is not user whit that email
	 *
	 * @throws IllegalArgumentException when given parameter is null or empty
	 */
	User findByEmail(String email);

	/**
	 * Gets all User entities stored in database with given role
	 * @param role
	 * @return List of Users or empty list if there is not user with that role
	 *
	 * @throws IllegalArgumentException when given parameter is null
	 */
	List<User> getAllForRole(UserRole role);
}
