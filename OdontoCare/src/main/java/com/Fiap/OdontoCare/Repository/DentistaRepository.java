package com.Fiap.OdontoCare.Repository;

import com.Fiap.OdontoCare.Entity.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    //Optional<Dentista> findById(Long id);

    @Procedure(name = "INSERT_DENTISTA")
    void INSERT_DENTISTA(
            @Param("p_Nome") String nome,
            @Param("p_CRO") String cro,
            @Param("p_Especialidade") String especialidade,
            @Param("p_Telefone") String telefone
    );

    @Procedure(name = "UPDATE_DENTISTA")
    void UPDATE_DENTISTA(
            @Param("p_ID_Dentista") Long id,
            @Param("p_Nome") String nome,
            @Param("p_CRO") String cro,
            @Param("p_Especialidade") String especialidade,
            @Param("p_Telefone") String telefone
    );

    @Procedure(name = "DELETE_DENTISTA")
    void DELETE_DENTISTA(
            @Param("p_ID_Dentista") Long id
    );
}
