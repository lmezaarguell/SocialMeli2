package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestGeneratorUtil {
    private static List<Buyer> buyers = loadData();
    private static List<Buyer> loadData(){
        List<Buyer> buyers = new ArrayList<>();
        String route = "classpath:buyer.json";
        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile(route);

            Buyer[] buyersArray = objectMapper.readValue(file, Buyer[].class);

            for (Buyer b : buyersArray) {
                buyers.add(b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buyers;
    }

    public static Buyer getBuyerById(Integer id) {
        Buyer buyer = buyers.stream().filter(
                b -> b.getUser_id().equals(id)).findFirst().orElse(null);
        //Valida que sea un usario registrado y retorna el cliente
        if (buyer == null) throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        return buyer;
    }
}
