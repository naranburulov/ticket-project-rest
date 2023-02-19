package com.cydeo.repo;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByIsDeletedOrderByFirstNameDesc(Boolean deleted);
    User findByUserNameAndIsDeleted(String username, Boolean deleted);
    List<User> findByRoleDescriptionIgnoreCaseAndIsDeleted(String roleDescription, Boolean deleted);


}
