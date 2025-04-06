package com.suraj.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.suraj.exception.UserException;
import com.suraj.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;

}