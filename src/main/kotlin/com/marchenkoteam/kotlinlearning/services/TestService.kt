package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService @Autowired constructor(private val testRepository: TestRepository) {

    fun findById(id: Long): TestDto {
        val test = testRepository.findById(id)
                .orElseThrow { BadRequestException("No such test.") }
        return TestDto(test)
    }

    fun save(testForm: TestForm): TestDto {
        val test = testRepository.save(testForm.getTest())
        return TestDto(test)
    }

    fun deleteById(id: Long) = testRepository.deleteById(id)

    fun findAll(): List<TestDto> = testRepository.findAll().map(::TestDto)
}