package com.meli.be_java_hisp_w26_g10.service;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.repository.impl.BuyerRepositoryImpl;
import com.api.socialmeli.repository.impl.SellerRepositoryImpl;
import com.api.socialmeli.service.IBuyerService;
import com.api.socialmeli.service.ISellerService;
import com.api.socialmeli.service.impl.BuyerServiceImpl;
import com.api.socialmeli.service.impl.SellerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @Mock
    BuyerRepositoryImpl buyerRepository;
    @Mock
    SellerServiceImpl sellerService;

    @InjectMocks
    BuyerServiceImpl buyerService;

    @Test
    @DisplayName("Seguir a un usuario exitosamente")
    public void followSuccessfullyAnUser(){
        //Act && Arrange
        Buyer buyer = new Buyer(15, "Nicolas", new ArrayList<>());
        Seller seller = new Seller(15, "Nike");
        Buyer finalBuyer = new Buyer(15, "Nicolas", List.of(seller));
        when(sellerService.getSellerById(seller.getUser_id())).thenReturn(seller);
        when(buyerRepository.getById(buyer.getUser_id())).thenReturn(buyer);
        when(buyerRepository.followUser(buyer,seller)).thenReturn(finalBuyer);
        Buyer buyerReturned = buyerService.followUser(buyer.getUser_id(),seller.getUser_id());
        //Assert
        Assertions.assertEquals(finalBuyer, buyerReturned);
    }

    @Test
    @DisplayName("Seguir a un usuario el cual ya se seguia previamente")
    public void followUserTwice(){
        //Act && Arrange
        Seller seller = new Seller(15, "Nike");
        Buyer buyer = new Buyer(15, "Nicolas", List.of(seller));
        when(sellerService.getSellerById(seller.getUser_id())).thenReturn(seller);
        when(buyerRepository.getById(buyer.getUser_id())).thenReturn(buyer);
        //Assert
        Assertions.assertThrows(BadRequestException.class,
                () -> buyerService.followUser(buyer.getUser_id(),seller.getUser_id()));
    }
}
