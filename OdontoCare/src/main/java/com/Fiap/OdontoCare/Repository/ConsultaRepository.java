package com.Fiap.OdontoCare.Repository;

import com.Fiap.OdontoCare.DTO.PacienteDTO;
import com.Fiap.OdontoCare.Entity.Consulta;
import com.Fiap.OdontoCare.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    //Optional<Paciente> findById(Long id);

    @Procedure(name = "INSERT_CONSULTA")
    void INSERT_CONSULTA(
            @Param("p_Data_Consulta") LocalDateTime dataConsulta,
            @Param("p_ID_Paciente") Long pacienteId,
            @Param("p_ID_Dentista") Long dentistaId,
            @Param("p_Status") String status
    );

    @Procedure(name = "UPDATE_CONSULTA")
    void UPDATE_CONSULTA(
            @Param("p_ID_Consulta") Long id,
            @Param("p_Data_Consulta") LocalDateTime dataConsulta,
            @Param("p_ID_Paciente") Long pacienteId,
            @Param("p_ID_Dentista") Long dentistaId,
            @Param("p_Status") String status
    );

    @Procedure(name = "DELETE_CONSULTA")
    void DELETE_CONSULTA(
            @Param("p_ID_Consulta") Long id
    );
}
