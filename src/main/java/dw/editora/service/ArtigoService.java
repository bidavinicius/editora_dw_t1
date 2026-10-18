package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dw.editora.entity.Artigo;
import dw.editora.entity.Autor;
import dw.editora.repository.ArtigoRepository;
import dw.editora.repository.AutorRepository;

@Service
public class ArtigoService {

    private final ArtigoRepository rep;
    private final AutorRepository autorRep;

    public ArtigoService(ArtigoRepository rep, AutorRepository autorRep) {
        this.rep = rep;
        this.autorRep = autorRep;
    }

    public List<Artigo> listarArtigos(String titulo) {
        if (titulo == null || titulo.isEmpty())
            return rep.findAll();
        else
            return rep.findByTituloContaining(titulo);
    }

    public Optional<Artigo> buscarPorId(long id) {
        return rep.findById(id);
    }

    public Optional<Artigo> criar(Artigo artigo) {
        if (artigo.getAutor() == null)
            return Optional.empty();

        Optional<Autor> autor = autorRep.findById(artigo.getAutor().getId());
        if (autor.isEmpty())
            return Optional.empty();

        Artigo novo = rep.save(new Artigo(
            artigo.getTitulo(),
            artigo.getResumo(),
            artigo.isPublicado(),
            autor.get()
        ));
        return Optional.of(novo);
    }

    public Optional<Artigo> atualizar(long id, Artigo dadosAtualizados) {
        Optional<Artigo> data = rep.findById(id);
        if (data.isPresent()) {
            Artigo a = data.get();
            a.setTitulo(dadosAtualizados.getTitulo());
            a.setResumo(dadosAtualizados.getResumo());
            a.setPublicado(dadosAtualizados.isPublicado());
            if (dadosAtualizados.getAutor() != null) {
                Optional<Autor> autor = autorRep.findById(dadosAtualizados.getAutor().getId());
                if (autor.isPresent())
                    a.setAutor(autor.get());
            }
            return Optional.of(rep.save(a));
        }
        return Optional.empty();
    }

    public boolean deletar(long id) {
        if (rep.existsById(id)) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }

    public void deletarTodos() {
        rep.deleteAll();
    }

    public List<Artigo> listarPublicados() {
        return rep.findByPublicado(true);
    }
}