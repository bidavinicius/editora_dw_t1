// Camada Service do padrão MVC - lógica de negócio da entidade Autor.
package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dw.editora.entity.Autor;
import dw.editora.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository rep;

    public AutorService(AutorRepository rep) {
        this.rep = rep;
    }

    public List<Autor> listarAutores() {
        return rep.findAll();
    }

    public Optional<Autor> buscarPorId(long id) {
        return rep.findById(id);
    }

    public Optional<Autor> criar(Autor autor) {
        if (rep.findByEmail(autor.getEmail()).isPresent())
            return Optional.empty();

        Autor novo = rep.save(new Autor(autor.getNome(), autor.getEmail(), autor.getBiografia()));
        return Optional.of(novo);
    }
}