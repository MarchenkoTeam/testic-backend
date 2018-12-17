package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.forms.LoginForm
import com.marchenkoteam.kotlinlearning.forms.RegistrationForm
import com.marchenkoteam.kotlinlearning.services.AuthService
import com.marchenkoteam.kotlinlearning.services.UserTestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/auth")
class AuthController @Autowired constructor(private val authService: AuthService,
                                            private val userTestService: UserTestService) {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    fun getMe(@RequestHeader authToken: String) = authService.getMe()

    @PostMapping("/login")
    fun login(@RequestBody loginForm: LoginForm) = authService.login(loginForm)

    @PostMapping("/sign_up")
    fun register(@RequestBody registrationForm: RegistrationForm){
        authService.save(registrationForm)
        userTestService.initForNewUser(authService.getMe().id)
    }
}