package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;

import java.util.List;

public class TestGeneratorUtil {



    public static String returnAString() {
        return "Hola";
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
