package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
