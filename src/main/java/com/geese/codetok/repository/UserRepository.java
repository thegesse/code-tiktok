package com.geese.codetok.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends JpaRepository<User, Long> {

}
