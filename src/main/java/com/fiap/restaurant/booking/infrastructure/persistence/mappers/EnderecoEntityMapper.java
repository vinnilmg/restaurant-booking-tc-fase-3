package com.fiap.restaurant.booking.infrastructure.persistence.mappers;

import com.fiap.restaurant.booking.core.domains.Endereco;
import com.fiap.restaurant.booking.core.domains.EnderecoDomain;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.EnderecoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EnderecoEntityMapper {

    @Mapping(target = "rua", source = "rua")
    @Mapping(target = "numero", source = "numero")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "bairro", source = "bairro")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cep", source = "cep")
    EnderecoEntity toEntity(Endereco endereco);

    default Endereco toDomain(EnderecoEntity endereco) {
        return new EnderecoDomain(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }

    default List<Endereco> toDomains(List<EnderecoEntity> enderecos) {
        return enderecos
                .stream()
                .map(this::toDomain)
                .toList();
    }
}
