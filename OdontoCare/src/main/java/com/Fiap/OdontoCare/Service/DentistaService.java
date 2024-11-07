package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.DentistaDTO;
import com.Fiap.OdontoCare.DTO.PacienteDTO;
import com.Fiap.OdontoCare.Entity.Dentista;
import com.Fiap.OdontoCare.Exception.ResourceNotFoundException;
import com.Fiap.OdontoCare.Repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    public List<Dentista> findAll() {
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> findById(Long id) {
        return Optional.ofNullable(dentistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + id)));
    }

    @Transactional
    public void insertWithProcedure(DentistaDTO dentistaDTO){
        this.dentistaRepository.INSERT_DENTISTA(
                dentistaDTO.getNome(),
                dentistaDTO.getCro(),
                dentistaDTO.getEspecialidade(),
                dentistaDTO.getTelefone()
        );
    }

    @Transactional
    public void updateWithProcedure(DentistaDTO dentistaDTO){
        this.dentistaRepository.UPDATE_DENTISTA(
                dentistaDTO.getIdDentista(),
                dentistaDTO.getNome(),
                dentistaDTO.getCro(),
                dentistaDTO.getEspecialidade(),
                dentistaDTO.getTelefone()
        );
    }

    @Transactional
    public void deleteWithProcedure(Long dentistaID){
        this.dentistaRepository.DELETE_DENTISTA(dentistaID);
    }
}