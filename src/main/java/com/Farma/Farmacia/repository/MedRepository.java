/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farma.Farmacia.repository;

import com.Farma.Farmacia.docs.medicamentos;
import com.Farma.Farmacia.docs.usuarios;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author marqueza834
 */
public interface MedRepository extends MongoRepository<medicamentos,Integer>{
    
    List<medicamentos> findByIdAsList(int terminoBusqueda);
    List<medicamentos> findByFarmacoContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByPrincActContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByCaduContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByPreseContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByConcenContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByLabContainingIgnoreCase(String terminoBusqueda);
    List<medicamentos> findByEstatusContainingIgnoreCase(String terminoBusqueda);
    
}
