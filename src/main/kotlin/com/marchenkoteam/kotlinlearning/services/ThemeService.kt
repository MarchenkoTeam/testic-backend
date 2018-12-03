package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val themeRepository: ThemeRepository, private val testRepository: TestRepository) {

    fun findAll() = themeRepository.findAll()
            .map(::ThemeDto)

    fun findById(id: Long): ThemeDto {
        val theme = themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
        return ThemeDto(theme)
    }

    fun save(themeForm: ThemeForm) {
        themeRepository.save(themeForm.getTheme())
    }

    fun deleteById(id: Long) = themeRepository.deleteById(id)


    fun findByThemeId(themeId: Long): List<TestDto> {
        val testList = testRepository.findTestsByThemeId(themeId)
                .orElseThrow { BadRequestException("No tests found for that theme.") }
        return testList.map (::TestDto)
    }
}
