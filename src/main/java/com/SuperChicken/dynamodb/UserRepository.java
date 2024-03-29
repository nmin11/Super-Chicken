package com.SuperChicken.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
@EnableScanCount
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
    Page<User> findAllByPhoneStartingWith(String nationCode, Pageable pageable);
}
