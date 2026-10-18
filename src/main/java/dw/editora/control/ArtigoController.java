package dw.editora.control;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.editora.entity.Artigo;
import dw.editora.service.ArtigoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/artigos")
public class ArtigoController {

    private final ArtigoService service;

    public ArtigoController(ArtigoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Artigo>> getAllArtigos(@RequestParam(required = false) String titulo) {
        try {
            List<Artigo> la = service.listarArtigos(titulo);
            if (la.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(la, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artigo> getArtigo(@PathVariable("id") long id) {
        Optional<Artigo> data = service.buscarPorId(id);
        if (data.isPresent())
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Artigo> createArtigo(@RequestBody Artigo artigo) {
        try {
            Optional<Artigo> novo = service.criar(artigo);
            if (novo.isPresent())
                return new ResponseEntity<>(novo.get(), HttpStatus.CREATED);
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artigo> updateArtigo(@PathVariable("id") long id, @RequestBody Artigo artigo) {
        Optional<Artigo> atualizado = service.atualizar(id, artigo);
        if (atualizado.isPresent())
            return new ResponseEntity<>(atualizado.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteArtigo(@PathVariable("id") long id) {
        try {
            boolean removido = service.deletar(id);
            if (removido)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllArtigos() {
        try {
            service.deletarTodos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/publicados")
    public ResponseEntity<List<Artigo>> getAllPublicados() {
        try {
            List<Artigo> la = service.listarPublicados();
            if (la.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(la, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}