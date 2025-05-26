package com.example.viceriseidorteste.presentation

import com.example.viceriseidorteste.domain.UserRepository
import com.example.viceriseidorteste.domain.models.User
import com.example.viceriseidorteste.presentation.features.list.ListUserViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ListUserViewModelTest {
    private lateinit var viewModel: ListUserViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val userRepository = mock<UserRepository>()
    private lateinit var isRefreshingFlow: MutableStateFlow<Boolean>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        isRefreshingFlow = MutableStateFlow(false)
        viewModel = ListUserViewModel(userRepository)
    }

    @Test
    fun `fetchUsers update users state`() = runTest {
        val user1 = mock<User>()
        val users = listOf(user1)
        whenever(userRepository.getUsers(1, 20)).thenReturn(users)

        viewModel.fetchUsers()
        advanceUntilIdle()
        assertEquals(users, viewModel.users.value)

    }

    @Test
    fun `fetchUsers emits error message when repository throws exception`() = runTest {
        whenever(userRepository.getUsers(1, 20)).thenThrow(RuntimeException("Network error"))

        viewModel.fetchUsers()
        advanceUntilIdle()
        assertEquals("Erro ao carregar usuários", viewModel.errorMessage.value)
    }

    @Test
    fun `fetchUsers shouldn't call repository when its loading already `() = runTest {
        isRefreshingFlow.value = true
        viewModel.fetchUsers()
        advanceUntilIdle()
        verify(userRepository, never()).getUsers(1, 20)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}