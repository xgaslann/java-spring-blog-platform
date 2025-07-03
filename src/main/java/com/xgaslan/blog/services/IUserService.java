package com.xgaslan.blog.services;

import com.xgaslan.blog.domain.entities.User;

import java.util.UUID;

public interface IUserService {
    User getById(UUID id);
}
