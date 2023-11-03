package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.exception.ApiErros;
import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Obter detalhes de um cliente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente encontrado."),
            @ApiResponse(code = 404, message = "Cliente não encontrado com o Id informado", response = ApiErros.class)
    })
    public Cliente pesquisarPorId(@PathVariable Long id) {
        return clienteService.pesquisarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista todos os clientes cadastrados.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Lista de clientes cadastrados.")
    })
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva um novo cliente.")
    @ApiResponses
            (
                    {
                            @ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
                            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class)
                    }
            )
    public Cliente incluir(@Valid @RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza um cliente existente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Erro(s) de validação.", response = ApiErros.class),
            @ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiErros.class)
    })
    public Cliente atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Exclui um cliente existente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Cliente excluído com sucesso."),
            @ApiResponse(code = 404, message = "Cliente não encontrado.", response = ApiErros.class)
    })
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
