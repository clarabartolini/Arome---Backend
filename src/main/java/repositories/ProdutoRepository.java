package repositories;

import models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByName(String nome); // Caso o nome seja único
    List<Produto> findAll();
    void deleteByName(String nome); // Retorna o número de registros deletados
}

