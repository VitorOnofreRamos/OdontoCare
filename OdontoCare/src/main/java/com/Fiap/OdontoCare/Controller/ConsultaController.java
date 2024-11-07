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

    @PostMapping
    public ResponseEntity createConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
        this.consultaService.insertWithProcedure(consultaDTO);
        return ResponseEntity.ok("Consulta criada com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaDTO consultaDTO) {
        consultaDTO.setIdConsulta(id);
        this.consultaService.updateWithProcedure(consultaDTO);
        return ResponseEntity.ok("Consulta atualizada com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        this.consultaService.deleteWithProcedure(id);
        return ResponseEntity.noContent().build();
    }
}