package com.meli.be_java_hisp_w26_g10.util;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.exception.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class TestGeneratorUtil {
    public static String orderAsc = "name_asc";
    public static String orderDesc = "name_desc";
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

    public static List<Seller> OrderFollowedListByName(String order, List<Seller> sellers){
        //Ordenamiento ascendente mediante expresiones lambda
        if (order.equals(orderAsc)){
            return ((sellers.stream()
                    .sorted(Comparator.comparing(Seller::getUser_name)).toList()));
        }
        //Ordenamiento descendente mediante expresiones lambda
        if (order.equals(orderDesc)){
            return ((sellers.stream()
                    .sorted(Comparator.comparing(Seller::getUser_name).reversed()).toList()));
        }
        //Si no se encuentra el ordenamiento solicitado en el US0008 entonces lanza la excepción BadRequest
        throw new BadRequestException("Parametros incorrectos para el ordenamiento");
    }
}
