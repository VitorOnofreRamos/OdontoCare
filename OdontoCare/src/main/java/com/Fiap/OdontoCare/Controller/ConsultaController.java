package com.Fiap.OdontoCare.Controller;

import com.Fiap.OdontoCare.DTO.ConsultaDTO;
import com.Fiap.OdontoCare.Entity.Consulta;
import com.Fiap.OdontoCare.Service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getConsultaById(@PathVariable Long id) {
        return consultaService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping
//    public ResponseEntity<Consulta> createConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
//        Consulta savedConsulta = consultaService.save(consultaDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsulta);
//    }

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@Valid @RequestBody ConsultaDTO consultaDTO){
        Consulta savedConsulta = consultaService.insertConsultaUsingProcedure(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> updateConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        consultaDTO.setIdConsulta(id);
        Consulta updatedConsulta = consultaService.update(consultaDTO);
        return ResponseEntity.ok(updatedConsulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ---- Métodos usando Procedures ----

    @PostMapping("/procedure")
    public ResponseEntity<String> createConsultaProcedure(@Valid @RequestBody ConsultaDTO consultaDTO){
        boolean success = consultaService.insertConsultaUsingProcedure(consultaDTO);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).body("Consulta criada com sucesso via procedure.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar consulta via procedure.");
        }
    }

    @PutMapping("/procedure/{id}")
    public ResponseEntity<String> updateConsultaWithProcedure(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        consultaDTO.setIdConsulta(id);
        boolean success = consultaService.updateConsultaUsingProcedure(consultaDTO);
        if (success) {
            return ResponseEntity.ok("Consulta atualizada com sucesso via procedure.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar consulta via procedure.");
        }
    }

    @DeleteMapping("/procedure/{id}")
    public ResponseEntity<String> deleteConsultaWithProcedure(@PathVariable Long id) {
        boolean success = consultaService.deleteConsultaUsingProcedure(id);
        if (success) {
            return ResponseEntity.ok("Consulta excluída com sucesso via procedure.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir consulta via procedure.");
        }
    }
}
