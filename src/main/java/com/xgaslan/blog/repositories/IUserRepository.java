package com.xgaslan.blog.repositories;

import com.xgaslan.blog.domain.entities.User;
import com.xgaslan.blog.repositories.base.IBaseRepository;

import java.util.UUID;

public interface IUserRepository extends IBaseRepository<User, UUID> {

}
