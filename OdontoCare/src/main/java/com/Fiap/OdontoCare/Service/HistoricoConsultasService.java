package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.ConsultaDTO;
import com.Fiap.OdontoCare.DTO.HistoricoConsultasDTO;
import com.Fiap.OdontoCare.Entity.HistoricoConsultas;
import com.Fiap.OdontoCare.Exception.ResourceNotFoundException;
import com.Fiap.OdontoCare.Repository.HistoricoConsultasRepository;
import com.Fiap.OdontoCare.Repository.ConsultaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoConsultasService {

    @Autowired
    private HistoricoConsultasRepository historicoConsultasRepository;

//    @Autowired
//    private ConsultaRepository consultaRepository; // Para buscar a consulta

    public List<HistoricoConsultas> findAll() {
        return historicoConsultasRepository.findAll();
    }

    public Optional<HistoricoConsultas> findById(Long id) {
        return Optional.ofNullable(historicoConsultasRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Histórico de consultas não encontrado com ID: " + id)));
    }

    @Transactional
    public void insertWithProcedure(HistoricoConsultasDTO historicoConsultasDTO){
        this.historicoConsultasRepository.INSERT_HISTORICO_CONSULTA(
                historicoConsultasDTO.getConsultaId(),
                historicoConsultasDTO.getDataAtendimento(),
                historicoConsultasDTO.getMotivoConsulta(),
                historicoConsultasDTO.getObservacoes()
        );
    }

//    public HistoricoConsultas save(HistoricoConsultasDTO historicoConsultaDTO) {
//        HistoricoConsultas historicoConsulta = new HistoricoConsultas();
//        historicoConsulta.setConsulta(consultaRepository.findById(historicoConsultaDTO.getConsultaId())
//                .orElseThrow(() -> new RuntimeException("Consulta não encontrada.")));
//        historicoConsulta.setDataAtendimento(historicoConsultaDTO.getDataAtendimento());
//        historicoConsulta.setMotivoConsulta(historicoConsultaDTO.getMotivoConsulta());
//        historicoConsulta.setObservacoes(historicoConsultaDTO.getObservacoes());
//        return historicoConsultasRepository.save(historicoConsulta);
//    }

    @Transactional
    public void updateWithProcedure(HistoricoConsultasDTO historicoConsultasDTO){
        this.historicoConsultasRepository.UPDATE_HISTORICO_CONSULTA(
                historicoConsultasDTO.getId(),
                historicoConsultasDTO.getConsultaId(),
                historicoConsultasDTO.getDataAtendimento(),
                historicoConsultasDTO.getMotivoConsulta(),
                historicoConsultasDTO.getObservacoes()
        );
    }

    public void deleteWithProcedure(Long id) {
        this.historicoConsultasRepository.DELETE_HISTORICO_CONSULTA(id);
    }
}