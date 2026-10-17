package dw.editora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.editora.entity.Autor;


public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    Optional<Autor> findByEmail(String email);
}