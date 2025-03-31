package services;

import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public String adicionarProduto(Produto produto) {
        if (produtoRepository.findByName(produto.getNome()).isPresent()) {
            return "Produto já cadastrado";
        }
        produtoRepository.save(produto);
        return "Produto cadastrado com sucesso";
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public String removeProduto(String nome) {
        Optional<Produto> produto = produtoRepository.findByName(nome);
        if (produto.isPresent()) {
            produtoRepository.deleteByName(nome); // Executa a exclusão sem retorno
            return "Produto removido com sucesso!";
        }
        return "Produto não encontrado!";
    }
}

