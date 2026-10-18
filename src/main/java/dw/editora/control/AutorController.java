package dw.editora.control;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.editora.entity.Artigo;
import dw.editora.entity.Autor;
import dw.editora.service.AutorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores() {
        try {
            List<Autor> la = service.listarAutores();
            if (la.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(la, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutor(@PathVariable("id") long id) {
        Optional<Autor> data = service.buscarPorId(id);
        if (data.isPresent())
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
        try {
            Optional<Autor> novo = service.criar(autor);
            if (novo.isPresent())
                return new ResponseEntity<>(novo.get(), HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/artigos")
    public ResponseEntity<List<Artigo>> getArtigosDoAutor(@PathVariable("id") long id) {
        try {
            Optional<List<Artigo>> artigos = service.listarArtigosPublicadosDoAutor(id);
            if (artigos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            List<Artigo> lista = artigos.get();
            if (lista.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}