package com.emil.store_api.service;

import com.emil.store_api.dto.LoginDto;
import com.emil.store_api.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
