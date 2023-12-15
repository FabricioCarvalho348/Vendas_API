package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.exception.ApiErros;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Produto;
import fabriciocarvalho348.com.github.vendasapi.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obter detalhes de um produto.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto encontrado."),
            @ApiResponse(code = 404, message = "Produto não encontrado para o Id informado.", response = ApiErros.class)
    })
    public Produto pesquisarPorId(@PathVariable Long id) {
        return produtoService.pesquisarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os produtos cadastrados.")
    @ApiResponses({@ApiResponse(code = 200, message = "Lista de produtos cadastrados.")})
    public List<Produto> pesquisarTodos() {
        return produtoService.pesquisarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva um novo produto.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto salvo com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação", response = ApiErros.class)
    })
    public Produto incluir(@Valid @RequestBody Produto produto) {
        return produtoService.incluir(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza um produto existente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Produto atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class),
            @ApiResponse(code = 404, message = "Produto não encontrado.", response = ApiErros.class)
    })
    public Produto atualizar(@Valid @RequestBody Produto produto, @PathVariable Long id) {
        return produtoService.atualizar(produto, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um produto existente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto excluído com sucesso."),
            @ApiResponse(code = 404, message = "Produto não encontrado.", response = ApiErros.class)
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }

}
