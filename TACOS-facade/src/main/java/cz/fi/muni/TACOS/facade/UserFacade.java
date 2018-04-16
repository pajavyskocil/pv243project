package cz.fi.muni.TACOS.facade;

import cz.fi.muni.TACOS.dto.UserAuthenticateDTO;
import cz.fi.muni.TACOS.dto.UserCreateDTO;
import cz.fi.muni.TACOS.dto.UserDTO;
import cz.fi.muni.TACOS.dto.UserUpdateDTO;
import cz.fi.muni.TACOS.persistence.entity.User;
import cz.fi.muni.TACOS.persistence.enums.UserRole;

import java.util.List;

/**
 * Facade Interface for User entity.
 * @author Pavel Vyskocil <vyskocilpavel@muni.cz>
 */
public interface UserFacade extends Facade<UserDTO, UserCreateDTO> {

    /**
     * Update UserDTO Entity
     *
     * @param user UserUpdateDTO with new attribute
     * @return UserDTO
     */
     UserDTO update( UserUpdateDTO user);

    /**
     * Finds user by email
     * @param email email by which will be user found
     * @return UserDTO or null if there is not user with that email
     */
    UserDTO findByEmail(String email);

    /**
     * Get all users with given role
     * @param role role by which willl be return list of users
     * @return UserDTO[] with given role
     */
    List<UserDTO> getAllForRole(UserRole role);

    /**
     * Add order to user
     * @param userId Id of user
     * @param orderId Id of order
     */
    void addOrder(Long userId, Long orderId);

    /**
     * Remove order from user
     * @param userId Id of user
     * @param orderId Id of order
     */
    void removeOrder(Long userId, Long orderId);

    /**
     * Try to authenticate a user.
     *
     * @param user which will be authenticated
     * @return true only if the hashed password matches the records, false otherwise
     */
    boolean authenticate(UserAuthenticateDTO user);
}
