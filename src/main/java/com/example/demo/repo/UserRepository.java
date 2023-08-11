package com.example.demo.repo;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByUserContact(String userContact);

    User findByUserId(UUID userId);
}
