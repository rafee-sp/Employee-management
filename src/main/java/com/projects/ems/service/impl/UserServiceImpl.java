package com.projects.ems.service.impl;

import org.springframework.stereotype.Service;

import com.projects.ems.entity.User;
import com.projects.ems.repository.UserRepository;
import com.projects.ems.service.UserService;
//

@Service
public class UserServiceImpl implements UserService {
	
	
	private UserRepository userRepo;

	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public String userLogin(User user) {

		String responseMsg = "Invalid credentials";

		try {

			User existingUser = userRepo.getByEmail(user.getEmail());

			if(existingUser != null) {

				String existingPassword = existingUser.getPassword();

				if(existingPassword.equalsIgnoreCase(user.getPassword())) {  // Todo : password setup with spring security
					responseMsg = "success";
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseMsg;
	}

}
