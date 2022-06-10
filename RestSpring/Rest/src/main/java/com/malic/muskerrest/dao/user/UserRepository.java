package com.malic.muskerrest.dao.user;

import com.malic.muskerrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    List<User> findUsersByTipoUsuario_TipoUsuarioId(int id);
}
