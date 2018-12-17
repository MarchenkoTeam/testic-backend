package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.services.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/tests")
class TestController @Autowired constructor(private val testService: TestService) {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    fun getTests(@RequestHeader authToken: String): List<TestDto> = testService.findAll()

    @GetMapping("/{id}")
    fun getTest(@PathVariable("id") id: Long): TestDto = testService.findById(id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    fun saveTest(@RequestHeader authToken: String,
                 @RequestBody testForm: TestForm): TestDto {
        return testService.save(testForm)
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    fun updateTheme(@RequestHeader authToken: String,
                    @RequestBody testForm: TestForm): TestDto {
        return testService.save(testForm)
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteTheme(@RequestHeader authToken: String,
                    @PathVariable("id") id: Long) {
        testService.deleteById(id)
    }
}