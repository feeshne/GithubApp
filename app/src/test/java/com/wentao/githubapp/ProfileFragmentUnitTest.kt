package com.wentao.githubapp

import com.wentao.githubapp.ui.profile.ProfileFragment
import com.wentao.githubapp.ui.profile.ProfileViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*

class ProfileFragmentUnitTest {
    @Test
    fun addition_isCorrect() {
        val fragment = mockk<ProfileViewModel>()
        every { fragment.checkUser(ProfileViewModel.name, ProfileViewModel.pass) } returns true
        assertTrue(fragment.checkUser("cwt", "123456"))
    }
}