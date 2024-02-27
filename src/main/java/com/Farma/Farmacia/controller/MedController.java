
package com.Farma.Farmacia.controller;

import com.Farma.Farmacia.docs.medicamentos;
import com.Farma.Farmacia.docs.usuarios;
import com.Farma.Farmacia.repository.MedRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.Farma.Farmacia.repository.UsrRepository;
import java.util.Optional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author marqueza834
 */
@Controller
@RequestMapping
public class MedController {
    
    @Autowired
    private MedRepository medRepository;
    @Autowired
    private UsrRepository usrRepository;

    //Metodos para mostrar pagina de Ingresos de medicamentos
    @GetMapping("/medicamentos")
    public String mostrarMedicamentos(Model model) {
        List<medicamentos> medicamentos = medRepository.findAll();
        model.addAttribute("medicamentos", medicamentos);
        model.addAttribute("medicamento", new medicamentos()); // Objeto para el formulario
        return "listaMeds";
    }

    @GetMapping("/medicamentosU")
    public String mostrarMedicamentosUsuario(Model model){
        List<medicamentos> medicamentos = medRepository.findAll();
        model.addAttribute("medicamentos",medicamentos);
        model.addAttribute("medicamento", new medicamentos());
        return "listaMedsUser";
    }

    // Método para procesar el formulario y guardar medicamentos
    @PostMapping("medicamentos/guardar")
    public String guardarDatos(@ModelAttribute("medicamento") @Validated medicamentos medicamento,
                                BindingResult result,RedirectAttributes redirectAttrs) {
        if(result.hasErrors()){
            redirectAttrs
                .addFlashAttribute("org.springframework.validation.BindingResult.usuario", result)
                .addFlashAttribute("medicamento", medicamento);
            return "listaMeds";
        }
        // Guardar en la base de datos MongoDB
        medRepository.save(medicamento);
        // Redirigir a la página principal que muestra la lista de medicamentos
        redirectAttrs
            .addFlashAttribute("mensaje", "Guardado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos";
    }

    // Método para procesar el formulario y guardar medicamentosU
    @PostMapping("/medicamentosU/guardarU")
    public String guardarDatosU(@ModelAttribute("medicamento") @Validated medicamentos medicamento,
                               BindingResult result,RedirectAttributes redirectAttrs) {
        if(result.hasErrors()){
            redirectAttrs
                    .addFlashAttribute("org.springframework.validation.BindingResult.usuario", result)
                    .addFlashAttribute("medicamento", medicamento);
            return "listaMedsUser";
        }
        // Guardar en la base de datos MongoDB
        medRepository.save(medicamento);
        // Redirigir a la página principal que muestra la lista de medicamentos
        redirectAttrs
                .addFlashAttribute("mensaje", "Guardado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/medicamentosU";
    }
    
    /////////////////////////////////Actualizar//////////////////////////////////////////////////////
    
    // Método para mostrar el formulario de actualización
    @GetMapping("medicamentos/update/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model) {
        medicamentos medicamento = medRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de medicamento no válido"));
        model.addAttribute("medicamento", medicamento);
        return "formUpdate"; // Crea un formulario similar al de ingreso con los datos actuales
    }

    // Método para procesar la actualización
    @PostMapping("medicamentos/actualizar/{id}")
    public String actualizarMedicamento(@PathVariable Integer id, @ModelAttribute medicamentos medicamento, Model modelo, RedirectAttributes redirectAttrs) {
        medRepository.save(medicamento);
        redirectAttrs
            .addFlashAttribute("mensaje", "Actualizado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos"; // Redirige a la página principal de medicamentos
    }

    @GetMapping("medicamentosU/updateU/{id}")
    public String mostrarFormularioActualizarU(@PathVariable Integer id, Model model) {
        medicamentos medicamento = medRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de medicamento no válido"));
        model.addAttribute("medicamento", medicamento);
        return "formUpdateUsuario"; // Crea un formulario similar al de ingreso con los datos actuales
    }

    // Método para procesar la actualización
    @PostMapping("medicamentosU/actualizarU/{id}")
    public String actualizarMedicamentoU(@PathVariable Integer id, @ModelAttribute medicamentos medicamento, Model modelo, RedirectAttributes redirectAttrs) {
        medRepository.save(medicamento);
        redirectAttrs
                .addFlashAttribute("mensaje", "Actualizado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/medicamentosU"; // Redirige a la página principal de medicamentos
    }
    
    // Método para eliminar un medicamento
    @GetMapping("medicamentos/eliminar/{id}")
    public String eliminarMedicamento(@PathVariable Integer id, Model modelo, RedirectAttributes redirectAttrs) {
        medRepository.deleteById(id);
        redirectAttrs
            .addFlashAttribute("mensaje", "Eliminado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos"; // Redirige a la página principal de medicamentos
    }

    @GetMapping("medicamentosU/eliminarU/{id}")
    public String eliminarMedicamentoU(@PathVariable Integer id, Model modelo, RedirectAttributes redirectAttrs) {
        medRepository.deleteById(id);
        redirectAttrs
                .addFlashAttribute("mensaje", "Eliminado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/medicamentosU"; // Redirige a la página principal de medicamentos
    }
    
    
   @GetMapping("medicamentos/buscar")
    public String mostrarPaginaBusqueda(Model model) {
        List<medicamentos> medicamentos = medRepository.findAll();
        model.addAttribute("medicamentos", medicamentos);
        return "search";
    }

    @PostMapping("medicamentos/rbusqueda")
    public String buscarMedicamentos(@RequestParam("campoBusqueda") String campoBusqueda,
                                  @RequestParam("terminoBusqueda") String terminoBusqueda,
                                  Model model) {
    List<medicamentos> resultadosBusqueda;

    switch (campoBusqueda) {
        case "farmaco":
            resultadosBusqueda = medRepository.findByFarmacoContainingIgnoreCase(terminoBusqueda);
            break;
        case "princAct":
            resultadosBusqueda = medRepository.findByPrincActContainingIgnoreCase(terminoBusqueda);
            break;
        case "cadu":
            resultadosBusqueda = medRepository.findByCaduContainingIgnoreCase(terminoBusqueda);
            break;
        case "prese":
            resultadosBusqueda = medRepository.findByPreseContainingIgnoreCase(terminoBusqueda);
            break;
        case "concen":
            resultadosBusqueda = medRepository.findByConcenContainingIgnoreCase(terminoBusqueda);
            break;
        case "lab":
            resultadosBusqueda = medRepository.findByLabContainingIgnoreCase(terminoBusqueda);
            break;
        case "estatus":
            resultadosBusqueda = medRepository.findByEstatusContainingIgnoreCase(terminoBusqueda);
            break;
        default:
            resultadosBusqueda = new ArrayList<>(); 
    }

    model.addAttribute("medicamentos", resultadosBusqueda);
    return "search";
}

    @GetMapping("/medicamentosU/buscarU")
    public String mostrarPaginaBusquedaU(Model model) {
        List<medicamentos> medicamentos = medRepository.findAll();
        model.addAttribute("medicamentos", medicamentos);
        return "searchUser";
    }

    @PostMapping("/medicamentosU/rbusquedaU")
    public String buscarMedicamentosU(@RequestParam("campoBusqueda") String campoBusqueda,
                                     @RequestParam("terminoBusqueda") String terminoBusqueda,
                                     Model model) {
        List<medicamentos> resultadosBusqueda;

        switch (campoBusqueda) {
            case "id":
                resultadosBusqueda = medRepository.findByIdContainingIgnoreCase(terminoBusqueda);
                break;
            case "farmaco":
                resultadosBusqueda = medRepository.findByFarmacoContainingIgnoreCase(terminoBusqueda);
                break;
            case "princAct":
                resultadosBusqueda = medRepository.findByPrincActContainingIgnoreCase(terminoBusqueda);
                break;
            case "cadu":
                resultadosBusqueda = medRepository.findByCaduContainingIgnoreCase(terminoBusqueda);
                break;
            case "prese":
                resultadosBusqueda = medRepository.findByPreseContainingIgnoreCase(terminoBusqueda);
                break;
            case "concen":
                resultadosBusqueda = medRepository.findByConcenContainingIgnoreCase(terminoBusqueda);
                break;
            case "lab":
                resultadosBusqueda = medRepository.findByLabContainingIgnoreCase(terminoBusqueda);
                break;
            case "estatus":
                resultadosBusqueda = medRepository.findByEstatusContainingIgnoreCase(terminoBusqueda);
                break;
            default:
                resultadosBusqueda = new ArrayList<>();
        }

        model.addAttribute("medicamentos", resultadosBusqueda);
        return "searchUser";
    }
    
    //////////////////////// Metodos Usuarios/////////////////////////////////////////////////////////////
    
    @GetMapping("medicamentos/usuarios")
    public String mostrarFormularioRegistro(Model model) {
        // Agregar el formulario
        model.addAttribute("usuario", new usuarios());

        // Obtener todos los usuarios y agregarlos para la tabla
        List<usuarios> usuarios = usrRepository.findAll();
        model.addAttribute("usuarios", usuarios);

        return "users";
    }
     
    // Método para mostrar el formulario de actualización
    @GetMapping("/medicamentos/usuarios/updateUsr/{id}")
    public String mostrarFormActUsr(@PathVariable Integer id, Model model) {
        usuarios usuario = usrRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de medicamento no válido"));
        model.addAttribute("usuario", usuario);
        return "formUpdateUsr"; 
    }

    // Método para procesar la actualización
    @PostMapping("/medicamentos/usuarios/actualizarUsr/{id}")
    public String actualizarUsr(@PathVariable Integer id, @ModelAttribute usuarios usuario, Model modelo, RedirectAttributes redirectAttrs) {
        usrRepository.save(usuario);
        redirectAttrs
            .addFlashAttribute("mensaje", "Actualizado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos/usuarios"; // Redirige a la página principal de medicamentos
    }

    @PostMapping("/medicamentos/usuarios/guardarU")
    public String guardarUsuarios(@ModelAttribute("usuario") @Validated usuarios usuario,
                              BindingResult result,
                              RedirectAttributes redirectAttrs){
        if (result.hasErrors()) {
             redirectAttrs
                .addFlashAttribute("org.springframework.validation.BindingResult.usuario", result)
                .addFlashAttribute("usuario", usuario);
            return "users";
        }
        
        // Guardar en la base de datos MongoDB
        usrRepository.save(usuario);
        // Redirigir a la página principal que muestra la lista de medicamentos
        redirectAttrs
            .addFlashAttribute("mensaje", "Guardado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos/usuarios";
    }
    
    @GetMapping("/medicamentos/usuarios/deleteU/{id}")
    public String eliminarUsuario(@PathVariable Integer id, Model modelo, RedirectAttributes redirectAttrs) {
        usrRepository.deleteById(id);
        redirectAttrs
            .addFlashAttribute("mensaje", "Eliminado correctamente")
            .addFlashAttribute("clase", "success");
        return "redirect:/medicamentos/usuarios"; 
    }
    
    //////////////////////////Metodos de Bajas/////////////////////////////////////////////////
    
    @GetMapping("/medicamentos/bajas")
    public String mosPaStatus(Model model) {
        List<medicamentos> medicamentos = medRepository.findAll();
        model.addAttribute("medicamentos", medicamentos);
        return "status";
    }
    
    @PostMapping("medicamentos/bajas/cambiarEstatus")
    public String cambiarEstadoMedicamento(@RequestParam("id") int id) {
        // Obtener el medicamento por ID (puedes ajustar esto según tu lógica y repositorio)
    Optional<medicamentos> optionalMedicamento = medRepository.findById(id);

    // Verificar si el medicamento existe
    if (optionalMedicamento.isPresent()) {
        medicamentos medicamento = optionalMedicamento.get();

        // Verificar el estado actual y cambiarlo
        if ("Stock".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("En Uso");
        } else if ("En Uso".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("Stock");
        }

        // Guardar el medicamento actualizado en la base de datos
        medRepository.save(medicamento);
    }

    // Después de cambiar el estado, redirigir a la página principal de búsqueda
    return "redirect:/medicamentos/bajas";
    }
    
    @GetMapping("/medicamentos/cambiarEstatus/{id}")
    public String cambiarMedicamento(@PathVariable int id) {
        // Obtener el medicamento por ID (puedes ajustar esto según tu lógica y repositorio)
    Optional<medicamentos> optionalMedicamento = medRepository.findById(id);

    // Verificar si el medicamento existe
    if (optionalMedicamento.isPresent()) {
        medicamentos medicamento = optionalMedicamento.get();

        // Verificar el estado actual y cambiarlo
        if ("Stock".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("En Uso");
        } else if ("En Uso".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("Stock");
        }

        // Guardar el medicamento actualizado en la base de datos
        medRepository.save(medicamento);
    }

    // Después de cambiar el estado, redirigir a la página principal de búsqueda
    return "redirect:/medicamentos/bajas";
    }
    
    @PostMapping("/medicamentos/restatus")
    public String buscarMedStatus(@RequestParam("campoBusqueda") String campoBusqueda,
                                  @RequestParam("terminoBusqueda") String terminoBusqueda,
                                  Model model) {
    List<medicamentos> resultadosBusqueda;

    switch (campoBusqueda) {
        case "farmaco":
            resultadosBusqueda = medRepository.findByFarmacoContainingIgnoreCase(terminoBusqueda);
            break;
        case "princAct":
            resultadosBusqueda = medRepository.findByPrincActContainingIgnoreCase(terminoBusqueda);
            break;
        case "cadu":
            resultadosBusqueda = medRepository.findByCaduContainingIgnoreCase(terminoBusqueda);
            break;
        case "prese":
            resultadosBusqueda = medRepository.findByPreseContainingIgnoreCase(terminoBusqueda);
            break;
        case "concen":
            resultadosBusqueda = medRepository.findByConcenContainingIgnoreCase(terminoBusqueda);
            break;
        case "lab":
            resultadosBusqueda = medRepository.findByLabContainingIgnoreCase(terminoBusqueda);
            break;
        case "estatus":
            resultadosBusqueda = medRepository.findByEstatusContainingIgnoreCase(terminoBusqueda);
            break;
        default:
            resultadosBusqueda = new ArrayList<>(); 
    }

    model.addAttribute("medicamentos", resultadosBusqueda);
    return "status";
}


@GetMapping("/medicamentosU/bajasU")
public String mosPaStatusU(Model model) {
    List<medicamentos> medicamentos = medRepository.findAll();
    model.addAttribute("medicamentos", medicamentos);
    return "statusUser";
}

@PostMapping("medicamentosU/bajasU/cambiarEstatusU")
public String cambiarEstadoMedicamentoU(@RequestParam("id") int id) {
    // Obtener el medicamento por ID (puedes ajustar esto según tu lógica y repositorio)
    Optional<medicamentos> optionalMedicamento = medRepository.findById(id);

    // Verificar si el medicamento existe
    if (optionalMedicamento.isPresent()) {
        medicamentos medicamento = optionalMedicamento.get();

        // Verificar el estado actual y cambiarlo
        if ("Stock".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("En Uso");
        } else if ("En Uso".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("Stock");
        }

        // Guardar el medicamento actualizado en la base de datos
        medRepository.save(medicamento);
    }

    // Después de cambiar el estado, redirigir a la página principal de búsqueda
    return "redirect:/medicamentosU/bajasU";
}

@GetMapping("/medicamentosU/cambiarEstatusU/{id}")
public String cambiarMedicamentoU(@PathVariable int id) {
    // Obtener el medicamento por ID (puedes ajustar esto según tu lógica y repositorio)
    Optional<medicamentos> optionalMedicamento = medRepository.findById(id);

    // Verificar si el medicamento existe
    if (optionalMedicamento.isPresent()) {
        medicamentos medicamento = optionalMedicamento.get();

        // Verificar el estado actual y cambiarlo
        if ("Stock".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("En Uso");
        } else if ("En Uso".equals(medicamento.getEstatus())) {
            medicamento.setEstatus("Stock");
        }

        // Guardar el medicamento actualizado en la base de datos
        medRepository.save(medicamento);
    }

    // Después de cambiar el estado, redirigir a la página principal de búsqueda
    return "redirect:/medicamentosU/bajasU";
}

@PostMapping("/medicamentosU/restatusU")
public String buscarMedStatusU(@RequestParam("campoBusqueda") String campoBusqueda,
                              @RequestParam("terminoBusqueda") String terminoBusqueda,
                              Model model) {
    List<medicamentos> resultadosBusqueda;

    switch (campoBusqueda) {
        case "farmaco":
            resultadosBusqueda = medRepository.findByFarmacoContainingIgnoreCase(terminoBusqueda);
            break;
        case "princAct":
            resultadosBusqueda = medRepository.findByPrincActContainingIgnoreCase(terminoBusqueda);
            break;
        case "cadu":
            resultadosBusqueda = medRepository.findByCaduContainingIgnoreCase(terminoBusqueda);
            break;
        case "prese":
            resultadosBusqueda = medRepository.findByPreseContainingIgnoreCase(terminoBusqueda);
            break;
        case "concen":
            resultadosBusqueda = medRepository.findByConcenContainingIgnoreCase(terminoBusqueda);
            break;
        case "lab":
            resultadosBusqueda = medRepository.findByLabContainingIgnoreCase(terminoBusqueda);
            break;
        case "estatus":
            resultadosBusqueda = medRepository.findByEstatusContainingIgnoreCase(terminoBusqueda);
            break;
        default:
            resultadosBusqueda = new ArrayList<>();
    }

    model.addAttribute("medicamentos", resultadosBusqueda);
    return "statusUser";
}
}

