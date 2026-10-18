package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dw.editora.entity.Artigo;
import dw.editora.entity.Autor;
import dw.editora.repository.AutorRepository;
import dw.editora.repository.ArtigoRepository;

@Service
public class AutorService {

    private final AutorRepository rep;
    private final ArtigoRepository artigoRep;

    public AutorService(AutorRepository rep, ArtigoRepository artigoRep) {
        this.rep = rep;
        this.artigoRep = artigoRep;
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

    public boolean deletar(long id) {
        if (rep.existsById(id)) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<List<Artigo>> listarArtigosPublicadosDoAutor(long autorId) {
        if (!rep.existsById(autorId))
            return Optional.empty();

        return Optional.of(artigoRep.findByAutorIdAndPublicado(autorId, true));
    }
}