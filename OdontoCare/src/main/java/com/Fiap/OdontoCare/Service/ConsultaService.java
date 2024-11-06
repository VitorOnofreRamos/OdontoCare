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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository; // Injeção do repositório de Dentista

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para inserir uma consulta usando Procedure
    public boolean insertConsultaUsingProcedure(ConsultaDTO consultaDTO) {
        return jdbcTemplate.update(connection -> {
            CallableStatement call = connection.prepareCall("{execute InserirConsulta(?, ?, ?, ?)}");
            call.setDate(1, java.sql.Date.valueOf(String.valueOf(consultaDTO.getDataConsulta())));
            call.setLong(2, consultaDTO.getPacienteId());
            call.setLong(3, consultaDTO.getDentistaId());
            call.setString(4, consultaDTO.getStatus());
            return call;
        }) > 0;
    }

    // Método para atualizar uma consulta usando Procedure
    public boolean updateConsultaUsingProcedure(ConsultaDTO consultaDTO) {
        return jdbcTemplate.update(connection -> {
            CallableStatement call = connection.prepareCall("{execute AtualizarConsulta(?, ?, ?, ?, ?)}");
            call.setLong(1, consultaDTO.getIdConsulta());
            call.setDate(2, java.sql.Date.valueOf(String.valueOf(consultaDTO.getDataConsulta())));
            call.setLong(3, consultaDTO.getPacienteId());
            call.setLong(4, consultaDTO.getDentistaId());
            call.setString(5, consultaDTO.getStatus());
            return call;
        }) > 0;
    }

    // Método para deletar uma consulta usando Procedure
    public boolean deleteConsultaUsingProcedure(Long idConsulta) {
        return jdbcTemplate.update(connection -> {
            CallableStatement call = connection.prepareCall("{execute DeletarConsulta(?)}");
            call.setLong(1, idConsulta);
            return call;
        }) > 0;
    }

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return Optional.ofNullable(consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id)));
    }

    public Consulta save(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());
        consulta.setDetalhes(consultaDTO.getDetalhes());

        // Buscar paciente
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + consultaDTO.getPacienteId()));
        consulta.setPaciente(paciente);

        // Buscar dentista
        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + consultaDTO.getDentistaId()));
        consulta.setDentista(dentista); // Supondo que você tenha um método setDentista na classe Consulta

        return consultaRepository.save(consulta);
    }

    public Consulta update(ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(consultaDTO.getIdConsulta())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + consultaDTO.getIdConsulta()));

        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());
        consulta.setDetalhes(consultaDTO.getDetalhes());

        if (consultaDTO.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + consultaDTO.getPacienteId()));
            consulta.setPaciente(paciente);
        }

        if (consultaDTO.getDentistaId() != null) {
            Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + consultaDTO.getDentistaId()));
            consulta.setDentista(dentista); // Atualizando o dentista
        }

        return consultaRepository.save(consulta);
    }

    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }
}
