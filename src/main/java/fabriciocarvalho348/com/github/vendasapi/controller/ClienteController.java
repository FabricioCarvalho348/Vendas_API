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
        Cliente response = clienteService.pesquisarPorId(id);
        return response;
    }

    @GetMapping
    public List<Cliente> pesquisarTodos() {
        List<Cliente> response = clienteService.pesquisarTodos();
        return response;
    }

    @PostMapping
    public Cliente incluir(@RequestBody Cliente cliente) {
        Cliente response = clienteService.incluir(cliente);
        return response;
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
        Cliente response = clienteService.atualizar(cliente, id);
        return response;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }
}
