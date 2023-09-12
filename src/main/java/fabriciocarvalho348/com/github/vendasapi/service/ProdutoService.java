package fabriciocarvalho348.com.github.vendasapi.service;

import fabriciocarvalho348.com.github.vendasapi.model.entity.Produto;
import fabriciocarvalho348.com.github.vendasapi.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {

    private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto pesquisarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }

    public List<Produto> pesquisarTodos() {
        return produtoRepository.findAll();
    }

    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto, Long id) {
//        Cliente encontrado = pesquisarPorId(id);
//        cliente.setId(encontrado.getId());
//        return clienteRepository.save(cliente);

        // API STREAM JAVA
        return produtoRepository.findById(id)
                .map(m -> {
                    produto.setId(m.getId());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }

    public void excluir(Long id) {
//        Cliente encontrado = pesquisarPorId(id);
//        clienteRepository.delete(encontrado);
        produtoRepository.findById(id)
                .map(m -> {
                    produtoRepository.delete(m);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
    }
}
