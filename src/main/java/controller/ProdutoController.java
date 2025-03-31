package controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProdutoService;

import java.util.List;



@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto Controller", description = "Endpoints para gerenciar produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @Operation(summary = "Adicionar produto", description = "Endpoint para cadastrar um novo produto.")
    @PostMapping
    public ResponseEntity<String> adicionarProduto(@RequestBody Produto produto) {
        String resposta = produtoService.adicionarProduto(produto);
        if (resposta.equals("Produto já cadastrado")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta); // 409 Conflict
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta); // 201 Created
    }


    @Operation(summary = "Listar todos os produtos", description = "Endpoint para listar todos os produtos cadastrados.")
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(produtos); // 200 OK
    }


    @Operation(summary = "Remover produto", description = "Endpoint para remover um produto pelo nome.")
    @DeleteMapping("/{nome}")
    public ResponseEntity<String> removerProduto(@PathVariable String nome) {
        String resposta = produtoService.removeProduto(nome);
        if (resposta.equals("Produto não encontrado!")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta); // 404 Not Found
        }
        return ResponseEntity.ok(resposta); // 200 OK
    }
}

