package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.entity.Product;

import java.time.LocalDate;
import java.util.*;

public class TestGeneratorUtil {

    public static Buyer getSingleBuyer() {
        List<Seller> sellerList = new ArrayList<>();
        Seller seller1 = new Seller(1, "Luis");

        sellerList.add(seller1);

        Buyer myBuyer = new Buyer(1, "Luis", sellerList);
        return myBuyer;
    }

    public static String returnAString() {
        return "Hola";
    }


    public static List<Post> postListOrderDesTest() {
        return postListOrderTestOutOrder();
    }

    public static List<Post> postListOrderAscTest() {
        List<Post> postList = postListOrderTestOutOrder();
        Collections.reverse(postList);
        return postList;
    }

    public static Buyer buyersPostListOrderTest() {
        return new Buyer(10, "Warren Buffett,",
                List.of(new Seller(1,"Jeff Bezos"),
                        new Seller(2,"Steve Jobs")
                        ));
    }


    private static List<Product> productsPostListOrderTest() {
        return  List.of(
        new Product(1, "Smartphone Galaxy S21", "Samsung", "Electrónico",
                "Negro", "Nuevo en caja"),
        new Product(2, "Portátil MacBook Air", "Apple", "Electrónico",
                "Gris", "Usado en excelente estado"),
        new Product(3, "Zapatillas Running UltraBoost 21", "Adidas", "Calzado",
                "Blanco", "Con tecnología Boost"),
        new Product(4, "Cámara Mirrorless EOS R5", "Canon", "Fotografía",
                "Negro", "Grabación 8K"),
        new Product(5, "Smartphone Galaxy S21", "Samsung", "Electrónico",
                "Negro", "Nuevo en caja"));
    }

    public static List<Post> postListOrderTestOutOrder(){
        return Arrays.asList(
                new Post(1, LocalDate.now().minusDays(0), 1, 1500000.0, 1,
                        productsPostListOrderTest().get(0), false, 0.0),
                new Post(2, LocalDate.now().minusDays(1),  1, 3200000.0, 1,
                        productsPostListOrderTest().get(1), false, 0.0),
                new Post(3, LocalDate.now().minusDays(2),  1, 1800000.0, 1,
                        productsPostListOrderTest().get(2), false, 0.0),
                new Post(4, LocalDate.now().minusDays(3),  1, 2500000.0, 1,
                        productsPostListOrderTest().get(3), false, 0.0),
                new Post(5, LocalDate.now().minusDays(4),  2, 800000.0, 2,
                        productsPostListOrderTest().get(4), false, 0.0));
    }


    public static List<Buyer> createBuyerEnvironment(){
        Buyer buyerOne = new Buyer(1,"Miguel Guzman",List.of(new Seller(1,"Meli"),new Seller(2,"Adidas"),new Seller(3,"Levis")));
        Buyer buyerTwo = new Buyer(2,"Juan Perez",List.of(new Seller(1,"Meli")));
        return List.of(buyerOne,buyerTwo);
    }
    public static List<Seller> createSellerEnvironment(){
        return List.of(new Seller(1,"Meli"), new Seller(2,"Adidas"),new Seller(3,"Levis"));
    }
}
