package com.xgaslan.blog.aspect;

import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SoftDeleteFilterAspect {

    private final EntityManager entityManager;

    public SoftDeleteFilterAspect(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void enableSoftDeleteFilter() {
        System.out.println("SoftDeleteFilter ENABLED!"); // log.info da olur
        Session session = entityManager.unwrap(Session.class);
        if (session.getEnabledFilter("deletedFilter") == null) {
            session.enableFilter("deletedFilter").setParameter("isDeleted", false);
        }
    }
}