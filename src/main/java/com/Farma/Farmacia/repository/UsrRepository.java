/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farma.Farmacia.repository;

import com.Farma.Farmacia.docs.usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author laclavees12345
 */
public interface UsrRepository extends MongoRepository<usuarios, Integer> {
    usuarios findByEmail(String Email);


}