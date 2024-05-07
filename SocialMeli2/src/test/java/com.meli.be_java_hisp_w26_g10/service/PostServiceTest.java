package com.meli.be_java_hisp_w26_g10.service;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.impl.BuyerRepositoryImpl;
import com.api.socialmeli.repository.impl.PostRepositoryImpl;
import com.api.socialmeli.service.impl.BuyerServiceImpl;
import com.api.socialmeli.service.impl.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meli.be_java_hisp_w26_g10.util.TestGeneratorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepositoryImpl postRepository;

    @Mock
    private BuyerRepositoryImpl buyerRepository;

    @Mock
    private BuyerServiceImpl buyerService;

    @InjectMocks
    private PostServiceImpl postService;

    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    @DisplayName("Se ordenan las publicaciones de los vendedores que sigue por fecha descendente")
    public void postByFollowedOrderDescTest() {
        //Arrange
        List<PostDto> postsDtoResponse;


        Buyer buyer = TestGeneratorUtil.buyersPostListOrderTest();

        List<Post> allPost = TestGeneratorUtil.postListOrderDesTest();
        List<PostDto> allPostDTO = allPost.stream().map(post -> mapper.convertValue(post, PostDto.class)).toList();

        buyerRepository.saveAll(List.of(TestGeneratorUtil.buyersPostListOrderTest()));
        postRepository.saveAll(TestGeneratorUtil.postListOrderTestOutOrder());


        Mockito.when(buyerService.getBuyerById(buyer.getUser_id())).thenReturn(buyer);
        Mockito.when(postRepository.getAll()).thenReturn(allPost);

        //Act
        postsDtoResponse = postService.getPostsByFollowed(buyer.getUser_id(), "date_desc").getPosts();

        //Assert
        Assertions.assertEquals(postsDtoResponse, allPostDTO);
        Mockito.verify(buyerService, Mockito.atLeastOnce()).getBuyerById(buyer.getUser_id());
        Mockito.verify(postRepository, Mockito.atLeastOnce()).getAll();
    }


}
