package com.SuperChicken.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
@EnableScanCount
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll(Pageable pageable);
    List<User> findAllByPhoneStartingWith(String nationCode, Pageable pageable);
}
