package com.example.desafio.Desafio.repositories;

import com.example.desafio.Desafio.DTOs.SellerDTO;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface SellerRepository {

    SellerDTO findById (Integer id) throws Exception;
    SellerDTO updateSeller (Integer id, SellerDTO seller) throws Exception;
}
