package com.Fiap.OdontoCare.Service;

import com.Fiap.OdontoCare.DTO.DentistaDTO;
import com.Fiap.OdontoCare.Entity.Dentista;
import com.Fiap.OdontoCare.Exception.ResourceNotFoundException;
import com.Fiap.OdontoCare.Repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Dentista> findAll() {
        return dentistaRepository.findAll();
    }

    public Optional<Dentista> findById(Long id) {
        return Optional.ofNullable(dentistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + id)));
    }

    public void save(DentistaDTO dentistaDTO) {
        Dentista dentista = new Dentista();
        dentista.setIdDentista(dentistaDTO.getIdDentista()); // Definindo o ID do dentista se necessário
        dentista.setNome(dentistaDTO.getNome());
        dentista.setCro(dentistaDTO.getCro()); // Inclua o mapeamento do CRO
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        dentista.setTelefone(dentistaDTO.getTelefone());

        callProcedureInsert(dentista.getNome(), dentista.getCro(), dentista.getEspecialidade(), dentista.getTelefone());

        // Se você tiver um relacionamento com consultas, você também deve mapeá-lo aqui
        //return dentistaRepository.save(dentista);
    }

    // Método para chamar a procedure de inserção
    private void callProcedureInsert(String nomeDentista, String cro, String especialidade, String telefone) {
        String sql = "CALL INSERT_DENTISTA(?, ?, ?, ?)";

        jdbcTemplate.update(sql, nomeDentista, cro, especialidade, telefone);
    }

    public void update(DentistaDTO dentistaDTO) {
        Dentista dentista = dentistaRepository.findById(dentistaDTO.getIdDentista())
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + dentistaDTO.getIdDentista()));
        dentista.setNome(dentistaDTO.getNome());
        dentista.setCro(dentistaDTO.getCro()); // Inclua o mapeamento do CRO
        dentista.setEspecialidade(dentistaDTO.getEspecialidade());
        dentista.setTelefone(dentistaDTO.getTelefone());

        callProcedureUpdate(dentista.getIdDentista(), dentista.getNome(), dentista.getCro(), dentista.getEspecialidade(), dentista.getTelefone());

        //return dentistaRepository.save(dentista);
    }

    private void callProcedureUpdate(Long idDentista, String nomeDentista, String cro, String especialidade, String telefone){
        String sql = "CALL UPDATE_CONSULTA(?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, nomeDentista, cro, especialidade, telefone);
    }

    public void deleteById(Long id) {
        callProcedureDelete(id);
        //dentistaRepository.deleteById(id);
    }

    private void callProcedureDelete(Long idDentista){
        String sql = "CALL DELETE_CONSULTA(?)";

        jdbcTemplate.update(sql, idDentista);
    }
}
