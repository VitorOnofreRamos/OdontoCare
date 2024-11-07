package com.Fiap.OdontoCare.Repository;

import com.Fiap.OdontoCare.Entity.HistoricoConsultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HistoricoConsultasRepository extends JpaRepository<HistoricoConsultas, Long> {
    //Optional<HistoricoConsultas> findById(Long id);

    @Procedure(name = "INSERT_HISTORICO_CONSULTA")
    void INSERT_HISTORICO_CONSULTA(
            @Param("p_ID_Consulta") Long idConsulta,
            @Param("p_Data_Atendimento") LocalDateTime dataAtendimento,
            @Param("p_Motivo_Consulta") String motivoConsulta,
            @Param("p_Observacoes") String observacoes
    );

    @Procedure(name = "UPDATE_HISTORICO_CONSULTA")
    void UPDATE_HISTORICO_CONSULTA(
            @Param("p_ID_Historico") Long id,
            @Param("p_ID_Consulta") Long idConsulta,
            @Param("p_Data_Atendimento") LocalDateTime dataAtendimento,
            @Param("p_Motivo_Consulta") String motivoConsulta,
            @Param("p_Observacoes") String observacaoes
    );

    @Procedure(name = "DELETE_HISTORICO_CONSULTA")
    void DELETE_HISTORICO_CONSULTA(
            @Param("p_ID_Historico") Long id
    );
}
