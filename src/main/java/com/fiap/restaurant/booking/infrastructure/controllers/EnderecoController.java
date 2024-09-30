package com.fiap.restaurant.booking.infrastructure.controllers;

import com.fiap.restaurant.booking.core.usecases.endereco.AtualizaEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.DeleteEnderecoUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByBairroUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCepUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByCidadeUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByIdUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.FindEnderecoByRuaUseCase;
import com.fiap.restaurant.booking.core.usecases.endereco.GetAllEnderecosUseCase;
import com.fiap.restaurant.booking.infrastructure.controllers.mappers.EnderecoMapper;
import com.fiap.restaurant.booking.infrastructure.controllers.request.EnderecoRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.EnderecoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/api/addresses")
@RestController
public class EnderecoController {
    private final AtualizaEnderecoUseCase atualizaEnderecoUseCase;
    private final DeleteEnderecoUseCase deleteEnderecoUseCase;
    private final FindEnderecoByBairroUseCase findEnderecoByBairroUseCase;
    private final FindEnderecoByCepUseCase findEnderecoByCepUseCase;
    private final FindEnderecoByCidadeUseCase findEnderecoByCidadeUseCase;
    private final FindEnderecoByIdUseCase findEnderecoByIdUseCase;
    private final FindEnderecoByRuaUseCase findEnderecoByRuaUseCase;
    private final GetAllEnderecosUseCase getAllEnderecosUseCase;
    private final EnderecoMapper enderecoMapper;

    public EnderecoController(
            AtualizaEnderecoUseCase atualizaEnderecoUseCase,
            DeleteEnderecoUseCase deleteEnderecoUseCase,
            FindEnderecoByBairroUseCase findEnderecoByBairroUseCase,
            FindEnderecoByCepUseCase findEnderecoByCepUseCase,
            FindEnderecoByCidadeUseCase findEnderecoByCidadeUseCase,
            FindEnderecoByIdUseCase findEnderecoByIdUseCase,
            FindEnderecoByRuaUseCase findEnderecoByRuaUseCase,
            GetAllEnderecosUseCase getAllEnderecosUseCase,
            EnderecoMapper enderecoMapper
    ) {
        this.atualizaEnderecoUseCase = atualizaEnderecoUseCase;
        this.deleteEnderecoUseCase = deleteEnderecoUseCase;
        this.findEnderecoByBairroUseCase = findEnderecoByBairroUseCase;
        this.findEnderecoByCepUseCase = findEnderecoByCepUseCase;
        this.findEnderecoByCidadeUseCase = findEnderecoByCidadeUseCase;
        this.findEnderecoByIdUseCase = findEnderecoByIdUseCase;
        this.findEnderecoByRuaUseCase = findEnderecoByRuaUseCase;
        this.getAllEnderecosUseCase = getAllEnderecosUseCase;
        this.enderecoMapper = enderecoMapper;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponse>> getAllEnderecos() {
        final var enderecos = getAllEnderecosUseCase.execute();
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecosResponse(enderecos));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EnderecoResponse> getEnderecoById(@PathVariable final Long id) {
        final var endereco = findEnderecoByIdUseCase.execute(id);
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecoResponse(endereco));
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<List<EnderecoResponse>> getEnderecoByRua(@PathVariable final String rua) {
        final var enderecos = findEnderecoByRuaUseCase.execute(rua);
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecosResponse(enderecos));
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<EnderecoResponse>> getEnderecoByCidade(@PathVariable final String cidade) {
        final var enderecos = findEnderecoByCidadeUseCase.execute(cidade);
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecosResponse(enderecos));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<EnderecoResponse>> getEnderecoByCep(@PathVariable final String cep) {
        final var enderecos = findEnderecoByCepUseCase.execute(cep);
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecosResponse(enderecos));
    }

    @GetMapping("/bairro/{bairro}")
    public ResponseEntity<List<EnderecoResponse>> getEnderecoByBairro(@PathVariable final String bairro) {
        final var enderecos = findEnderecoByBairroUseCase.execute(bairro);
        return ResponseEntity.status(OK)
                .body(enderecoMapper.toEnderecosResponse(enderecos));
    }

    @PutMapping("/{id}")
    public void updateEndereco(@PathVariable(name = "id") final Long id, @Valid @RequestBody final EnderecoRequest request) {
        atualizaEnderecoUseCase.execute(enderecoMapper.toEndereco(request), id);
    }
}
