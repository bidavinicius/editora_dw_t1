package dw.editora.model;

import jakarta.persistence.*;


@Entity
@Table(name = "artigo")
public class Artigo {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 80, nullable = false)
    private String titulo;

    @Column (nullable = false)
    private String resumo;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean publicado = true;

}
