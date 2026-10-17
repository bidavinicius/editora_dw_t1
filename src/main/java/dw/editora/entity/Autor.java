
package dw.editora.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "autor") 
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nome;

    
    @Column(nullable = false, unique = true, length = 150)
    private String email;


    @Column(columnDefinition = "TEXT")
    private String biografia;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Artigo> artigos = new ArrayList<>();
    
    public Autor() {
    }

    
    public Autor(String nome, String email, String biografia) {
        this.nome = nome;
        this.email = email;
        this.biografia = biografia;
    }

    
    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getBiografia() { return this.biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }

    public List<Artigo> getArtigos() { return this.artigos; }
    public void setArtigos(List<Artigo> artigos) { this.artigos = artigos; }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", biografia=" + biografia + "]";
    }
}