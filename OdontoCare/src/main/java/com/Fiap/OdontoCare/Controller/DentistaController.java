package com.Fiap.OdontoCare.Controller;

import com.Fiap.OdontoCare.Entity.Dentista;
import com.Fiap.OdontoCare.Service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Fiap.OdontoCare.DTO.DentistaDTO;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping
    public List<Dentista> getAllDentistas() {
        return dentistaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> getDentistaById(@PathVariable Long id) {
        return dentistaService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
        this.dentistaService.insertWithProcedure(dentistaDTO);
        return ResponseEntity.ok("Dentista criado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDentista(@PathVariable Long id, @Valid @RequestBody DentistaDTO dentistaDTO) {
        dentistaDTO.setIdDentista(id);
        this.dentistaService.updateWithProcedure(dentistaDTO);
        return ResponseEntity.ok("Dentista atualizado com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentista(@PathVariable Long id) {
        this.dentistaService.deleteWithProcedure(id);
        return ResponseEntity.noContent().build();
    }
}