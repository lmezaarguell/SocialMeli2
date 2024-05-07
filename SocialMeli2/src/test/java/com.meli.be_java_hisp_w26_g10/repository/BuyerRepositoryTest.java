package com.meli.be_java_hisp_w26_g10.repository;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.repository.impl.BuyerRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyerRepositoryTest {

    BuyerRepositoryImpl buyerRepository = new BuyerRepositoryImpl();

    @Test
    @DisplayName("Obtener un usuario que existe ")
    public void getAnExistingUser(){
        //Act && Arrange
        Buyer buyerObtained = buyerRepository.getById(1);
        //Assert
        Assertions.assertNotNull(buyerObtained);
    }

    @Test
    @DisplayName("Obtener un usuario que no existe ")
    public void getANonExistingUser(){
        //Act && Arrange
        Buyer buyerObtained = buyerRepository.getById(100);
        //Assert
        Assertions.assertNull(buyerObtained);
    }
}
