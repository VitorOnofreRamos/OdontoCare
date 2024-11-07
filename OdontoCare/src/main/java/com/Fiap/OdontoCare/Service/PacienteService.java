package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.DentistaDTO;
import com.Fiap.OdontoCare.DTO.PacienteDTO;
import com.Fiap.OdontoCare.Entity.Paciente;
import com.Fiap.OdontoCare.Exception.ResourceNotFoundException;
import com.Fiap.OdontoCare.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id)));
    }

    @Transactional
    public void insertWithProcedure(PacienteDTO pacienteDTO){
        this.pacienteRepository.INSERT_PACIENTE(
                pacienteDTO.getNome(),
                pacienteDTO.getDataNascimento(),
                pacienteDTO.getCpf(),
                pacienteDTO.getEndereco(),
                pacienteDTO.getTelefone(),
                pacienteDTO.getCarteirinha()
        );
    }

    @Transactional
    public void updateWithProcedure(PacienteDTO pacienteDTO){
        this.pacienteRepository.UPDATE_PACIENTE(
                pacienteDTO.getId(),
                pacienteDTO.getNome(),
                pacienteDTO.getDataNascimento(),
                pacienteDTO.getCpf(),
                pacienteDTO.getEndereco(),
                pacienteDTO.getTelefone(),
                pacienteDTO.getCarteirinha()
        );
    }

    @Transactional
    public void deleteWithProcedure(Long id) {
        this.pacienteRepository.DELETE_PACIENTE(id);
    }
}