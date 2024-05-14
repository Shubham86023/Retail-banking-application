package com.example.authenticationservice.Model.Repository;

import com.example.authenticationservice.Model.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
            select t from Token t inner join Employee u on t.id = u.id
            where t.id = :userId and t.loggedOut = false
            """)
    List<Token> findAllTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
