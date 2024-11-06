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

import java.time.LocalDateTime;
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

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return Optional.ofNullable(consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id)));
    }

    public void save(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());

        // Buscar paciente
        Paciente paciente = pacienteRepository.findById(consultaDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + consultaDTO.getPacienteId()));
        consulta.setPaciente(paciente);

        // Buscar dentista
        Dentista dentista = dentistaRepository.findById(consultaDTO.getDentistaId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + consultaDTO.getDentistaId()));
        consulta.setDentista(dentista); // Supondo que você tenha um método setDentista na classe Consulta

        callProcedureInsert(consulta.getDataConsulta(), consulta.getPaciente().getId(), consulta.getDentista().getIdDentista(), consulta.getStatus());

        //return consultaRepository.save(consulta);
    }

    // Método para chamar a procedure de inserção
    private void callProcedureInsert(LocalDateTime dataConsulta, Long pacienteId, Long dentistaId, String status) {
        String sql = "CALL INSERT_CONSULTA(?, ?, ?, ?)";

        jdbcTemplate.update(sql, dataConsulta, pacienteId, dentistaId, status);
    }

    public void update(ConsultaDTO consultaDTO) {
        Consulta consulta = consultaRepository.findById(consultaDTO.getIdConsulta())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + consultaDTO.getIdConsulta()));

        consulta.setDataConsulta(consultaDTO.getDataConsulta());
        consulta.setStatus(consultaDTO.getStatus());

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

        callProcedureUpdate(consulta.getIdConsulta(), consulta.getDataConsulta(), consulta.getPaciente().getId(), consulta.getDentista().getIdDentista(), consulta.getStatus());

        //return consultaRepository.save(consulta);
    }

    // Método para chamar a procedure de atualização
    private void callProcedureUpdate(Long idConsulta, LocalDateTime dataConsulta, Long pacienteId, Long dentistaId, String status) {
        String sql = "CALL UPDATE_CONSULTA(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, idConsulta, dataConsulta, pacienteId, dentistaId, status);
    }

    public void deleteById(Long id) {
        callProcedureDelete(id);
        //consultaRepository.deleteById(id);
    }

    //Método pra chamar a procedure de deleção
    private void callProcedureDelete(Long idConsulta){
        String sql = "CALL DELETE_CONSULTA(?)";

        jdbcTemplate.update(sql, idConsulta);
    }
}
