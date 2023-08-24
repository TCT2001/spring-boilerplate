package com.tct.springboot.repository;

import com.tct.springboot.enums.StatusCode;
import com.tct.springboot.model.users.User;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManagerFactory userLocalEntityManagerFactory;

    public QueryResult<User> findByUsername(@Nullable EntityManager entityManager, @Nonnull String username) {
        try {
            if (entityManager == null) {
                entityManager = userLocalEntityManagerFactory.createEntityManager();
            }
            User user = entityManager
                    .createQuery("SELECT u FROM User u where u.username = username limit 1", User.class)
                    .getSingleResult();
            return new QueryResult<>(StatusCode.SUCCESS, user);
        } catch (Exception e) {
            return new QueryResult<>(StatusCode.FAIL);
        }
    }

    public QueryResult<Boolean> existsByEmail(@Nullable EntityManager entityManager, @Nonnull String email) {
        try {
            if (entityManager == null) {
                entityManager = userLocalEntityManagerFactory.createEntityManager();
            }
            User user = entityManager
                    .createQuery("SELECT u FROM User u where u.email = email limit 1", User.class)
                    .getSingleResult();
            return user == null
                    ? new QueryResult<>(StatusCode.SUCCESS, false)
                    : new QueryResult<>(StatusCode.SUCCESS, true);
        } catch (Exception e) {
            return new QueryResult<>(StatusCode.FAIL);
        }
    }

    public QueryResult<Boolean> existsByUsername(@Nullable EntityManager entityManager, @Nonnull String username) {
        try {
            if (entityManager == null) {
                entityManager = userLocalEntityManagerFactory.createEntityManager();
            }
            User user = entityManager
                    .createQuery("SELECT u FROM User u where u.username = username limit 1", User.class)
                    .getSingleResult();
            return user == null
                    ? new QueryResult<>(StatusCode.SUCCESS, false)
                    : new QueryResult<>(StatusCode.SUCCESS, true);
        } catch (Exception e) {
            return new QueryResult<>(StatusCode.FAIL);
        }
    }

    public @Nonnull EntityManager getEntityManager() {
        return userLocalEntityManagerFactory.createEntityManager();
    }
}
