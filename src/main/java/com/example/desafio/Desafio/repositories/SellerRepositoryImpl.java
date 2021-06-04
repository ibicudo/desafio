package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.SellerDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class SellerRepositoryImpl implements SellerRepository {

    private File sellersFile;
    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<List<SellerDTO>> sellersTypeReference = new TypeReference<List<SellerDTO>>() {};

    public SellerRepositoryImpl() throws FileNotFoundException {
        this.sellersFile = ResourceUtils.getFile("src/main/resources/sellers.json");
    }

    @Override
    public SellerDTO findById(Integer id) throws Exception {
        Optional<SellerDTO> optionalSeller = getSeller().stream().filter(sellersDTO -> sellersDTO.getId().equals(id)).findFirst();

        if (!optionalSeller.isPresent()) {
            throw new Exception("Order does not exist");
        }

        return optionalSeller.get();
    }


    public List<SellerDTO> loadDataBase() throws IOException {

        List<SellerDTO> sellers = null;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<SellerDTO>> typeRef = new TypeReference<List<SellerDTO>>() {};

        sellers = objectMapper.readValue(this.sellersFile, typeRef);

        return sellers;
    }

    private List<SellerDTO> getSeller() throws Exception {
        return mapper.readValue(this.sellersFile, sellersTypeReference);
    }

    @Override
    public SellerDTO updateSeller(Integer id, SellerDTO seller) throws Exception {
        List<SellerDTO> listSellers = loadDataBase();
        SellerDTO storedSeller = this.findById(id);
        int index = listSellers.indexOf(storedSeller);

        listSellers.set(index,seller);
        mapper.writeValue(this.sellersFile,listSellers);

        return seller;
    }

}

