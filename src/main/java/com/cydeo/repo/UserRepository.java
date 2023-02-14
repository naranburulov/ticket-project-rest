package com.cydeo.repo;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

    List<User> findAllByRoleDescriptionAndEnabledIgnoreCase(String roleDescription);


}
