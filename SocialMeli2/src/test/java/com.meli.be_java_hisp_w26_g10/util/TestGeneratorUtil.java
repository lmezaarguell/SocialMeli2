package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.dto.UserDto;
import com.api.socialmeli.entity.Buyer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestGeneratorUtil {



    public static String returnAString() {
        return "Hola";
    }

    public static List<Buyer> generateBuyerList(){
        return new ArrayList<>(
                List.of(
                        new Buyer(1, "Antonio", Collections.emptyList()),
                        new Buyer(2, "Cesar", Collections.emptyList()),
                        new Buyer(3, "Jorge", Collections.emptyList()),
                        new Buyer(4, "Octavio", Collections.emptyList()),
                        new Buyer(5, "Zapata", Collections.emptyList())
                )
        );
    }

    public static List<UserDto> generateUserDtoList(){
        return new ArrayList<>(
                List.of(
                        new UserDto(
                                3,
                                "Zapata"
                        ),
                        new UserDto(
                                1,
                                "Antonio"
                        ),
                        new UserDto(
                                2,
                                "Octavio"
                        ),
                        new UserDto(
                                4,
                                "Cesar"
                        )
                )
        );
    }
}
