package dw.editora.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import dw.editora.repository.ArtigoRepository;

@RestController

public class ArtigoController {

    @Autowired
    ArtigoRepository rep;

}
