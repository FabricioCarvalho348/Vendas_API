package fabriciocarvalho348.com.github.vendasapi.controller;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Cliente;
import fabriciocarvalho348.com.github.vendasapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Cliente listarPorId(@PathVariable Long id) {
        return clienteService.pesquisarPorId(id);
    }

    @GetMapping
    public List<Cliente> pesquisarTodos() {
        return clienteService.pesquisarTodos();
    }

    @PostMapping
    public Cliente incluir(@RequestBody Cliente cliente) {
        return clienteService.incluir(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.atualizar(cliente, id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
