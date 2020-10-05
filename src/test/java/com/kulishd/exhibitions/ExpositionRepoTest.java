package com.kulishd.exhibitions;

import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.ExpositionRepo;
import com.kulishd.exhibitions.repos.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@RunWith(MockitoJUnitRunner.class)
//public class ExpositionRepoTest {
//    @InjectMocks
//    ExpositionRepo expositionRepo;
//    @Test
//    public void updateCountOfTickets(){
//        assertEquals(2,expositionRepo.updateCountOfTickets(3));
//    }
//}
