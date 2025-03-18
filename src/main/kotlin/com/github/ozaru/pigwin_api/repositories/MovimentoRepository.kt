package com.github.ozaru.pigwin_api.repositories

import com.github.ozaru.pigwin_api.entities.Movimento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MovimentoRepository: JpaRepository<Movimento, String> {

    @Query(
        "select t from Movimento t"
                + " where 1 = 1"
                + " and (:id = '' or lower(t.id) like lower(concat('%', :id, '%')))"
                + " and (:descricao = '' or lower(t.descricao) like lower(concat('%', :descricao, '%')))"
    )
    fun filter(
        @Param("id") id: String,
        @Param("descricao") descricao: String,
        pageable: Pageable,
    ): Page<Movimento>

}