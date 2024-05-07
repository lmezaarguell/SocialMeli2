package com.meli.be_java_hisp_w26_g10.service;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.service.impl.BuyerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.be_java_hisp_w26_g10.util.TestGeneratorUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.collections4.CollectionUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import com.api.socialmeli.repository.impl.BuyerRepositoryImpl;
import org.junit.jupiter.api.Assertions;
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

    @Test
    @DisplayName("Obtener la lista de seguidos por usuario sin un ordenamiento definido de manera exitosa")
    public void GetFollowedListByUserSuccessful(){
        //Arrange
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = TestGeneratorUtil.getBuyerById(1);
        when(repository.getById(1)).thenReturn(buyer);
        //Act
        Buyer buyerResult = mapper.convertValue(service
                .GetFollowedListByUser(buyer.getUser_id(),null),Buyer.class);
        //Assert
        assertEquals(buyer,buyerResult);
    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario que no existe")
    public void GetFollowedListByUserNotFound(){
        //Arrange
        when(repository.getById(anyInt())).thenReturn(null);
        //Act && Assert
        assertThrows(NotFoundException.class,
                () -> service.GetFollowedListByUser(11, null)
        );
    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario ordenada ascendentemente")
    public void GetFollowedListByUserOrderUpwardSuccessful(){
        //Arrange
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = TestGeneratorUtil.getBuyerById(10);
        buyer.setFollowed(TestGeneratorUtil.OrderFollowedListByName("name_asc",buyer.getFollowed()));
        when(repository.getById(buyer.getUser_id())).thenReturn(buyer);
        //Act
        Buyer response = mapper.convertValue(service
                .GetFollowedListByUser(buyer.getUser_id(),"name_asc"),Buyer.class);
        //Assert
        assertTrue(CollectionUtils.isEqualCollection(buyer.getFollowed(),response.getFollowed()));
    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario ordenada descendentemente")
    public void GetFollowedListByUserOrderDownwardSuccessful(){
        //Arrange
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = TestGeneratorUtil.getBuyerById(10);
        buyer.setFollowed(TestGeneratorUtil.OrderFollowedListByName("name_desc",buyer.getFollowed()));
        when(repository.getById(buyer.getUser_id())).thenReturn(buyer);
        //Act
        Buyer response = mapper.convertValue(service
                .GetFollowedListByUser(buyer.getUser_id(),"name_desc"),Buyer.class);
        //Assert
        assertTrue(CollectionUtils.isEqualCollection(buyer.getFollowed(),response.getFollowed()));
    }

    @Test
    @DisplayName("Obtener la lista de seguidos de un usuario con parametros de ordenamiento invalidos")
    public void GetFollowedListByUserOrderFailedParamsInvalid(){
        //Arrange
        Buyer buyer = TestGeneratorUtil.getBuyerById(10);
        when(repository.getById(buyer.getUser_id())).thenReturn(buyer);
        //Act && Assert
        assertThrows(BadRequestException.class,
                () -> service.GetFollowedListByUser(buyer.getUser_id(), "any")
        );
    }

}
