package dw.editora.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.editora.model.Artigo;
import dw.editora.repository.ArtigoRepository;

@RestController
@RequestMapping("/editora")

public class ArtigoController {

    @Autowired
    ArtigoRepository rep;
    
     @GetMapping("/")
    public List<Artigo> listar() {
        return rep.findAll();
    }

}
