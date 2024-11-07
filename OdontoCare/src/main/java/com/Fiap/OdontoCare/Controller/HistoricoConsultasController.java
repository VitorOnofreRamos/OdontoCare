package com.Fiap.OdontoCare.Controller;

import com.Fiap.OdontoCare.DTO.HistoricoConsultasDTO;
import com.Fiap.OdontoCare.Entity.HistoricoConsultas;
import com.Fiap.OdontoCare.Service.HistoricoConsultasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/historico-consultas")
public class HistoricoConsultasController {

    @Autowired
    private HistoricoConsultasService historicoConsultasService;

    @GetMapping
    public List<HistoricoConsultas> getAllHistoricoConsultas() {
        return historicoConsultasService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoConsultas> getHistoricoConsultaById(@PathVariable Long id) {
        return historicoConsultasService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createHistoricoConsulta(@Valid @RequestBody HistoricoConsultasDTO historicoConsultaDTO) {
        this.historicoConsultasService.insertWithProcedure(historicoConsultaDTO);
        return ResponseEntity.ok("Historico criado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHistoricoConsulta(@PathVariable Long id, @Valid @RequestBody HistoricoConsultasDTO historicoConsultaDTO) {
        historicoConsultaDTO.setId(id);
        this.historicoConsultasService.updateWithProcedure(historicoConsultaDTO);
        return ResponseEntity.ok("Historico atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoricoConsulta(@PathVariable Long id) {
        this.historicoConsultasService.deleteWithProcedure(id);
        return ResponseEntity.noContent().build();
    }
}