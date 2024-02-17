/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farma.Farmacia.docs;

/**
 *
 * @author marqueza834
 */

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



@Getter
@Setter
@Document(collection="usuarios")
public class usuarios {
    
     @Id
     private int id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String pass;

    @NotEmpty(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El formato del correo electrónico no es válido")
    private String email;


    public usuarios() {
        
    }

    public usuarios(int id, String pass, String email, String name) {
        this.id = id;
        this.pass = pass;
        this.email = email;
        this.name = name;
    }


}
