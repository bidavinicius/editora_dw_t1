package dw.editora.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "artigo") 
public class Artigo {
    
    @Id
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    
    @Column(nullable = false, length = 80)
    private String titulo;

    @Column(nullable = false)
    private String resumo;

    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean publicado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonIgnoreProperties("artigos")
    private Autor autor;
    
    public Artigo() {
    }

    
    public Artigo(String titulo, String resumo, boolean publicado) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.publicado = publicado;
    }

    public Artigo(String titulo, String resumo, boolean publicado, Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.publicado = publicado;
        this.autor = autor;
    }

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public String getTitulo() { return this.titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getResumo() { return this.resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public boolean isPublicado() { return this.publicado; }
    public void setPublicado(boolean publicado) { this.publicado = publicado; }

    public Autor getAutor() { return this.autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Artigo [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", publicado=" + publicado + ", autor=" + autor + "]";
    }
}