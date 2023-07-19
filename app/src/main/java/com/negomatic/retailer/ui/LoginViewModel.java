package com.negomatic.retailer.ui;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import com.negomatic.retailer.repository.UserRepository;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;

    @Inject
    public LoginViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void init(String user, String password) {
        this.userRepository.attemptLoginSync(user, password);
    }
}
