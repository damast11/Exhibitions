package com.kulishd.exhibitions.service;

import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.UserRepo;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepo userRepo;
    @InjectMocks
    UserService userService;

    @Test
    public void findAll(){
        List<User> list = new ArrayList<>();
        list.add(new User(1L,"username","password",true));
        list.add(new User(2L,"usrname","pasword",true));
//        when(userRepo.findAll()).thenReturn(list);
        given(userRepo.findAll()).willReturn(list);
        List<User> expected = userService.findAllUsers();
        assertEquals(expected,list);
        assertEquals(2, expected.size());
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void saveUser(){
        User user = new User();
        when(userRepo.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertNotNull(savedUser);
        assertEquals(user,savedUser);
        verify(userRepo).save(any(User.class));
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void findUserByUsername(){
        User user = new User();
        user.setUsername("Dima");
        when(userRepo.findByUsername("Dima")).thenReturn(user);
        User expectedUser = userService.findUserByUsername("Dima");
        assertNotNull(expectedUser);
        assertEquals(user,expectedUser);
        verify(userRepo).findByUsername(any(String.class));
        verify(userRepo, times(1)).findByUsername("Dima");
    }

    @Test
    public void loadUserByUsername(){
        User user = new User();
        user.setUsername("Dima");
        when(userRepo.findByUsername("Dima")).thenReturn(user);
        UserDetails expectedUserDetails = userService.loadUserByUsername("Dima");
        assertNotNull(expectedUserDetails);
        assertEquals(user,expectedUserDetails);
        verify(userRepo).findByUsername(any(String.class));
        verify(userRepo, times(1)).findByUsername("Dima");
    }

}
