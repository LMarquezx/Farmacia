package com.Farma.Farmacia.controller;
import com.Farma.Farmacia.docs.usuarios;
import com.Farma.Farmacia.repository.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UsrRepository usrRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "Login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String pass, Model model) {
        usuarios usuario = usrRepository.findByEmail(email);

        if (usuario != null && usuario.getPass().equals(pass)) {
            if (usuario.getEmail().equals("admin@admin.com")){
                // Autenticación exitosa, redirigir a la página principal
                return "redirect:/medicamentos";
            }else{
                return "redirect:/medicamentosU";
            }

        } else {
            // Autenticación fallida, mostrar mensaje de error
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}