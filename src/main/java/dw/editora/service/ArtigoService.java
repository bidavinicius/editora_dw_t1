package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dw.editora.entity.Artigo;
import dw.editora.repository.ArtigoRepository;

@Service
public class ArtigoService {

    private final ArtigoRepository rep;

    public ArtigoService(ArtigoRepository rep) {
        this.rep = rep;
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

    public Artigo criar(Artigo artigo) {
        return rep.save(new Artigo(artigo.getTitulo(), artigo.getResumo(), artigo.isPublicado()));
    }

    public Optional<Artigo> atualizar(long id, Artigo dadosAtualizados) {
        Optional<Artigo> data = rep.findById(id);
        if (data.isPresent()) {
            Artigo a = data.get();
            a.setTitulo(dadosAtualizados.getTitulo());
            a.setResumo(dadosAtualizados.getResumo());
            a.setPublicado(dadosAtualizados.isPublicado());
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