package com.fiap.restaurant.booking.core.domains;

import com.fiap.restaurant.booking.core.exceptions.ValidationException;

import static java.util.Objects.isNull;

public class EnderecoDomain implements Endereco {
    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoDomain(
            final Long id,
            final String rua,
            final String numero,
            final String complemento,
            final String bairro,
            final String cidade,
            final String estado,
            final String cep
    ) {
        this.id = idValidation(id);
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cepValidation(cep);
    }

    public EnderecoDomain(
            final String rua,
            final String numero,
            final String complemento,
            final String bairro,
            final String cidade,
            final String estado,
            final String cep
    ) {
        this.rua = ruaValidation(rua);
        this.numero = numeroValidation(numero);
        this.complemento = complemento;
        this.bairro = bairroValidation(bairro);
        this.cidade = cidadeValidation(cidade);
        this.estado = estadoValidation(estado);
        this.cep = cepValidation(cep);
    }

    @Override
    public Long getId() { return id; }

    @Override
    public String getRua() { return rua; }

    @Override
    public String getBairro() { return bairro; }

    @Override
    public String getNumero() { return numero; }

    @Override
    public String getComplemento() { return complemento; }

    @Override
    public String getCidade() { return cidade; }

    @Override
    public String getEstado() { return estado; }

    @Override
    public String getCep() { return cep; }

    @Override
    public void updateEndereco(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.cep = endereco.getCep();
    }

    private static Long idValidation(final Long id) {
        if (isNull(id)) throw ValidationException.of("Endereco Id", "cannot be null");
        if (id < 0) throw ValidationException.of("Endereco Id", "cannot be negative");
        return id;
    }

    private static String ruaValidation(final String rua) {
        if (isNull(rua)) throw ValidationException.of("Endereço Rua", "cannot be null");
        return rua;
    }

    private static String numeroValidation(final String numero) {
        if (isNull(numero)) throw ValidationException.of("Endereço numero", "cannot be null");
        return numero;
    }

    private static String bairroValidation(final String bairro) {
        if (isNull(bairro)) throw ValidationException.of("Endereço Bairro", "cannot be null");
        return bairro;
    }

    private static String cidadeValidation(final String cidade) {
        if (isNull(cidade)) throw ValidationException.of("Endereço Cidade", "cannot be null");
        return cidade;
    }

    private static String estadoValidation(final String estado) {
        if (isNull(estado)) throw ValidationException.of("Endereço Estado", "cannot be null");
        return estado;
    }

    private static String cepValidation(final String cep) {
        if (isNull(cep)) throw ValidationException.of("Endereço CEP", "cannot be null");
        if (cep.length() < 8) throw ValidationException.of("Endereço CEP", "must be 8 positions");
        return cep;
    }
}
