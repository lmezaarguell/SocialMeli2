package com.meli.be_java_hisp_w26_g10.service;

import com.api.socialmeli.dto.SellersCountFollowersDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.impl.SellerServiceImpl;
import com.meli.be_java_hisp_w26_g10.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {
    @Mock
    IBuyerRepository buyerRepository;

    @Mock
    ISellerRepository sellerRepository;

    @InjectMocks
    SellerServiceImpl sellerService;

    @Test
    @DisplayName("Testeando un correcto escenario para contar seguidores")
    public void followerCounterOk(){
        //Arrange

        List<Buyer> buyers = TestGeneratorUtil.createBuyerEnvironment();
        List<Seller> sellers = TestGeneratorUtil.createSellerEnvironment();

        when(buyerRepository.getAll()).thenReturn(buyers);
        when(sellerRepository.getAll()).thenReturn(sellers);

        SellersCountFollowersDto result = sellerService.getCountOfSellerFollowers(1);

        Assertions.assertEquals(2,result.getFollowers_count());
    }
}
