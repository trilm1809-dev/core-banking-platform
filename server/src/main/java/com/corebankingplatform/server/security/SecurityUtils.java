package com.corebankingplatform.server.security;

import com.corebankingplatform.server.entites.Customer;
import com.corebankingplatform.server.entites.User;
import com.corebankingplatform.server.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            return user;
        }
        throw new BusinessException("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    public static Customer getCurrentCustomer() {
        User user = getCurrentUser();
        Customer customer = user.getCustomer();
        if (customer == null) {
            throw new BusinessException("Customer profile not found", HttpStatus.NOT_FOUND);
        }
        return customer;
    }
}
