package com.fiap.restaurant.booking.infrastructure.controllers.mappers;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.domains.EnderecoDomain;
import com.fiap.restaurant.booking.infrastructure.controllers.request.EnderecoRequest;
import com.fiap.restaurant.booking.infrastructure.controllers.response.EnderecoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EnderecoMapper {

    default EnderecoDomain toEndereco(EnderecoRequest request) {
        return new EnderecoDomain(
                request.rua(),
                request.numero(),
                request.complemento(),
                request.bairro(),
                request.cidade(),
                request.estado(),
                request.cep()
        );
    }

    List<EnderecoResponse> toEnderecosResponse(List<Endereco> enderecos);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "rua", source = "rua")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "bairro", source = "bairro")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cep", source = "cep")
    EnderecoResponse toEnderecoResponse(Endereco endereco);
}