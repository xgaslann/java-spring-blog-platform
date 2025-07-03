package com.xgaslan.blog.services.impl;

import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.repositories.IUserRepository;
import com.xgaslan.blog.services.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository repository;

    @Override
    public User getById(UUID id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}
