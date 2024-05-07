package com.meli.be_java_hisp_w26_g10.service;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.service.impl.BuyerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.be_java_hisp_w26_g10.util.TestGeneratorUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @Mock
    IBuyerRepository buyerRepository;

    @InjectMocks
    BuyerServiceImpl buyerService;


    @Test
    @DisplayName("Obtener la lista de seguidos por usuario de manera exitosa")
    public void GetFollowedListByUserSuccessful(){
        //Arrange
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = TestGeneratorUtil.getBuyerById(1);
        when(buyerRepository.getById(1)).thenReturn(buyer);
        when(buyerService.getBuyerById(1)).thenReturn(buyer);
        //Act
        Buyer buyerResult = mapper.convertValue(buyerService
                .GetFollowedListByUser(buyer.getUser_id(),null),Buyer.class);
        //Assert
        assertEquals(buyer,buyerResult);
    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario que no existe")
    public void GetFollowedListByUserNotFound(){

    }

    @Test
    @DisplayName("validar que funcione el ordenamiento ascendente")
    public void ValidateOrderUpwardSuccessful(){

    }

    @Test
    @DisplayName("validar que funcione el ordenamiento descendente")
    public void ValidateOrderDownwardSuccessful(){

    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario ordenada ascendentemente")
    public void GetFollowedListByUserOrderUpwardSuccessful(){

    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario ordenada descendentemente")
    public void GetFollowedListByUserOrderDownwardSuccessful(){

    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario con parametros de ordenamiento incorrectos")
    public void GetFollowedListByUserOrderFailed(){

    }

}
