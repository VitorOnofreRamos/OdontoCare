package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.ConsultaDTO;
import com.Fiap.OdontoCare.Entity.Consulta;
import com.Fiap.OdontoCare.Entity.Paciente;
import com.Fiap.OdontoCare.Entity.Dentista;
import com.Fiap.OdontoCare.Exception.ResourceNotFoundException;
import com.Fiap.OdontoCare.Repository.ConsultaRepository;
import com.Fiap.OdontoCare.Repository.PacienteRepository;
import com.Fiap.OdontoCare.Repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

//    @Autowired
//    private PacienteRepository pacienteRepository;
//
//    @Autowired
//    private DentistaRepository dentistaRepository; // Injeção do repositório de Dentista

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return Optional.ofNullable(consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id)));
    }

    @Transactional
    public void insertWithProcedure(ConsultaDTO consultaDTO){
        this.consultaRepository.INSERT_CONSULTA(
                consultaDTO.getDataConsulta(),
                consultaDTO.getPacienteId(),
                consultaDTO.getDentistaId(),
                consultaDTO.getStatus()
        );
    }

//    public Consulta save(ConsultaDTO consultaDTO) {
//        Consulta consulta = new Consulta();
//        consulta.setDataConsulta(consultaDTO.getDataConsulta());
//        consulta.setStatus(consultaDTO.getStatus());
//
//        // Buscar paciente
//        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
//                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + consultaDTO.getPacienteId()));
//        consulta.setPaciente(paciente);
//
//        // Buscar dentista
//        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
//                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + consultaDTO.getDentistaId()));
//        consulta.setDentista(dentista); // Supondo que você tenha um método setDentista na classe Consulta
//
//        return consultaRepository.save(consulta);
//    }

    @Transactional
    public void updateWithProcedure(ConsultaDTO consultaDTO){
        this.consultaRepository.UPDATE_CONSULTA(
                consultaDTO.getIdConsulta(),
                consultaDTO.getDataConsulta(),
                consultaDTO.getPacienteId(),
                consultaDTO.getDentistaId(),
                consultaDTO.getStatus()
        );
    }

//    public Consulta update(ConsultaDTO consultaDTO) {
//        Consulta consulta = consultaRepository.findById(consultaDTO.getIdConsulta())
//                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + consultaDTO.getIdConsulta()));
//
//        consulta.setDataConsulta(consultaDTO.getDataConsulta());
//        consulta.setStatus(consultaDTO.getStatus());
//
//        if (consultaDTO.getPacienteId() != null) {
//            Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + consultaDTO.getPacienteId()));
//            consulta.setPaciente(paciente);
//        }
//
//        if (consultaDTO.getDentistaId() != null) {
//            Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + consultaDTO.getDentistaId()));
//            consulta.setDentista(dentista); // Atualizando o dentista
//        }
//
//        return consultaRepository.save(consulta);
//    }

    @Transactional
    public void deleteWithProcedure(Long id) {
        this.consultaRepository.DELETE_CONSULTA(id);
    }
}