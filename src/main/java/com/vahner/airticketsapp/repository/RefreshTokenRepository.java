package com.vahner.airticketsapp.repository;

import com.vahner.airticketsapp.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByLogin(String login);

    default void saveToken(String login, String refreshToken) {
        RefreshToken token = findByLogin(login)
                .orElse(new RefreshToken(login, refreshToken));
        token.setLogin(login);
        token.setToken(refreshToken);
        save(token);
    }

    default Optional<String> findTokenByLogin(String login) {
        return findByLogin(login).map(RefreshToken::getToken);
    }
}