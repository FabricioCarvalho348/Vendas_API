package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Produto;
import fabriciocarvalho348.com.github.vendasapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public Produto listarPorId(@PathVariable Long id) {
        return produtoService.pesquisarPorId(id);
    }

    @GetMapping
    public List<Produto> pesquisarTodos() {
        return produtoService.pesquisarTodos();
    }

    @PostMapping
    public Produto incluir(@RequestBody @Valid Produto produto) {
        return produtoService.incluir(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@RequestBody @Valid Produto produto, @PathVariable Long id) {
        return produtoService.atualizar(produto, id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
