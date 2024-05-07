package com.meli.be_java_hisp_w26_g10.service;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.impl.BuyerRepositoryImpl;
import com.api.socialmeli.service.impl.BuyerServiceImpl;
import com.meli.be_java_hisp_w26_g10.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @Mock
    BuyerRepositoryImpl repository;

    @InjectMocks
    BuyerServiceImpl service;

    @Test
    @DisplayName("Realizar la accion de dejar de seguir a un vendedor de forma exitosa")
    public void unfollowUserGoodWay() {

        Integer followerId = 1;
        Integer unfollowId = 1;

        Buyer buyerExpected = TestGeneratorUtil.getSingleBuyer();

        when(repository.getById(followerId)).thenReturn(buyerExpected);

        service.unfollowUser(followerId, unfollowId);

        verify(repository, times(2)).getById(followerId);

    }
    @Test
    @DisplayName("Dejar de seguir a un vendedor mandando la excepcion NotFoundException")
    public void unfollowUserFirstSadPath() {
        Integer followerId = 1;
        Integer unfollowId = 2;


        when(repository.getById(followerId)).thenReturn(null);

        Assertions.assertThrows(NotFoundException.class, () -> service.unfollowUser(followerId, unfollowId));

    }

    @Test
    @DisplayName("Dejar de seguir a un vendedor mandando la excepcion NotFoundException con el mensaje : 'No sigues al vendedor'")
    public void unfollowUserSecondSadPath() {
        Integer followerId = 1;
        Integer unfollowId = 2;

        Buyer buyerExpected = TestGeneratorUtil.getSingleBuyer();

        when(repository.getById(followerId)).thenReturn(buyerExpected);

        Assertions.assertThrows(NotFoundException.class, () -> service.unfollowUser(followerId, unfollowId));

    }

}
