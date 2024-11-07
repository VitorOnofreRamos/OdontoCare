package com.Fiap.OdontoCare.Controller;

import com.Fiap.OdontoCare.DTO.PacienteDTO;
import com.Fiap.OdontoCare.Entity.Paciente;
import com.Fiap.OdontoCare.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        this.pacienteService.insertWithProcedure(pacienteDTO);
        return ResponseEntity.ok("Paciente criado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        pacienteDTO.setId(id);
        this.pacienteService.updateWithProcedure(pacienteDTO);
        return ResponseEntity.ok("Paciente atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        this.pacienteService.deleteWithProcedure(id);
        return ResponseEntity.noContent().build();
    }
}