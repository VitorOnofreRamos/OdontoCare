package com.Fiap.OdontoCare.Repository;

import com.Fiap.OdontoCare.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    //Optional<Paciente> findById(Long id);

    @Procedure(name = "INSERT_PACIENTE")
    void INSERT_PACIENTE(
            @Param("p_Nome") String nome,
            @Param("p_Data_Nascimento") LocalDate dataNascimento,
            @Param("p_CPF") String cpf,
            @Param("p_Endereco") String endereco,
            @Param("p_Telefone") String telefone,
            @Param("p_Carteirinha") Long carteirinha
    );

    @Procedure(name = "UPDATE_PACIENTE")
    void UPDATE_PACIENTE(
            @Param("p_ID_Paciente") Long id,
            @Param("p_Nome") String nome,
            @Param("p_Data_Nascimento") LocalDate dataNascimento,
            @Param("p_CPF") String cpf,
            @Param("p_Endereco") String endereco,
            @Param("p_Telefone") String telefone,
            @Param("p_Carteirinha") Long carteirinha
    );

    @Procedure(name = "DELETE_PACIENTE")
    void DELETE_PACIENTE(
            @Param("p_ID_Paciente") Long id
    );
}
