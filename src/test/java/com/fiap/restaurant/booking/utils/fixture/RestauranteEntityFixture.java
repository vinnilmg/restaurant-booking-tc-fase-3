package com.fiap.restaurant.booking.utils.fixture;

import com.fiap.restaurant.booking.core.domains.Restaurante;
import com.fiap.restaurant.booking.core.domains.enums.TipoCulinariaEnum;
import com.fiap.restaurant.booking.infrastructure.persistence.entities.RestauranteEntity;
import com.fiap.restaurant.booking.infrastructure.persistence.mappers.EnderecoEntityMapper;

import java.time.LocalTime;

import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_CNPJ;
import static com.fiap.restaurant.booking.utils.DefaultParamsConstants.DEFAULT_NOME;
import static org.mapstruct.factory.Mappers.getMapper;

public class RestauranteEntityFixture {

    public static RestauranteEntity BY_DOMAIN_WITH_ID(final Restaurante restaurante) {
        final var enderecoEntity = getMapper(EnderecoEntityMapper.class)
                .toEntity(restaurante.getEndereco());
        final var result = new RestauranteEntity();
        result.setNome(restaurante.getNome());
        result.setCnpj(restaurante.getCnpj());
        result.setEndereco(enderecoEntity);
        result.setTipoCulinaria(restaurante.getTipoCulinaria());
        result.setInicioFuncionamento(restaurante.getInicioFuncionamento());
        result.setFimFuncionamento(restaurante.getFimFuncionamento());
        result.setCapacidade(restaurante.getCapacidade());
        result.setMediaFeedback(restaurante.getMediaFeedback());
        result.setId(1L);
        return result;
    }

    public static RestauranteEntity FULL() {
        final var result = new RestauranteEntity();
        result.setId(1L);
        result.setNome(DEFAULT_NOME);
        result.setCnpj(DEFAULT_CNPJ);
        result.setEndereco(EnderecoEntityFixture.BY_DOMAIN(EnderecoDomainFixture.NOVO()));
        result.setTipoCulinaria(TipoCulinariaEnum.JAPONESA.name());
        result.setInicioFuncionamento(LocalTime.of(10, 0));
        result.setFimFuncionamento(LocalTime.of(22, 0));
        result.setCapacidade(50);
        result.setMediaFeedback(5.0);
        return result;
    }

    public static RestauranteEntity WITHOUT_ID() {
        final var result = new RestauranteEntity();
        result.setNome(DEFAULT_NOME);
        result.setCnpj(DEFAULT_CNPJ);
        result.setEndereco(EnderecoEntityFixture.BY_DOMAIN(EnderecoDomainFixture.NOVO()));
        result.setTipoCulinaria(TipoCulinariaEnum.JAPONESA.name());
        result.setInicioFuncionamento(LocalTime.of(10, 0));
        result.setFimFuncionamento(LocalTime.of(22, 0));
        result.setCapacidade(50);
        result.setMediaFeedback(5.0);
        return result;
    }

    public static RestauranteEntity BY_DOMAIN(final Restaurante restaurante) {
        final var entity = new RestauranteEntity();
        entity.setId(restaurante.getId());
        entity.setNome(restaurante.getNome());
        entity.setCnpj(restaurante.getCnpj());
        entity.setEndereco(EnderecoEntityFixture.BY_DOMAIN(restaurante.getEndereco()));
        entity.setTipoCulinaria(restaurante.getTipoCulinaria());
        entity.setCapacidade(restaurante.getCapacidade());
        entity.setMediaFeedback(restaurante.getMediaFeedback());
        entity.setInicioFuncionamento(restaurante.getInicioFuncionamento());
        entity.setFimFuncionamento(restaurante.getFimFuncionamento());
        return entity;
    }
}
