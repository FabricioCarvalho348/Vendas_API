package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente listarPorId(@PathVariable Long id) {
        return clienteService.pesquisarPorId(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente incluir(@RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
