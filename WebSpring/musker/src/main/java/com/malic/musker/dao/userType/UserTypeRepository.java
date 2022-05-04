package com.malic.musker.dao.userType;

import com.malic.musker.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
