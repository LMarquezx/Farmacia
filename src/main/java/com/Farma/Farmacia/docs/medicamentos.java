/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farma.Farmacia.docs;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author marqueza834
 */
@Document(collection="medicamentos")
public class medicamentos {
    
    @Id
    @NotNull(message="El id no puede ir vacio")
    private int id;
    private String farmaco;
    private String princAct;
    @Size(min=10,message="Elformato de fecha debe ser(DD-MM-AAAA)")
    private String cadu;
    private String prese;
    private String concen;
    private String lab;
    @NotEmpty(message="El id no puede ir vacio")
    private String estatus;
    
    public medicamentos(){
    
    }

    public medicamentos(int id, String farmaco, String princAct, String cadu, String prese, String concen, String lab, String estatus) {
    this.id = id;
    this.farmaco = farmaco;
    this.princAct = princAct;
    this.cadu = cadu;
    this.prese = prese;
    this.concen = concen;
    this.lab = lab;
    this.estatus = estatus;
    }


    public int getId() {
        return id;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public String getPrincAct() {
        return princAct;
    }

    public String getCadu() {
        return cadu;
    }

    public String getPrese() {
        return prese;
    }

    public String getConcen() {
        return concen;
    }

    public String getLab() {
        return lab;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
    }

    public void setPrincAct(String princAct) {
        this.princAct = princAct;
    }

    public void setCadu(String cadu) {
        this.cadu = cadu;
    }

    public void setPrese(String prese) {
        this.prese = prese;
    }

    public void setConcen(String concen) {
        this.concen = concen;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
}
