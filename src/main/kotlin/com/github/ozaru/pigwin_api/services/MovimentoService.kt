package com.github.ozaru.pigwin_api.services

import com.github.ozaru.pigwin_api.entities.Movimento
import com.github.ozaru.pigwin_api.repositories.MovimentoRepository
import org.apache.coyote.BadRequestException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class MovimentoService(
    private val movimentoRepository: MovimentoRepository,
) {

    fun filtrar(id: String, descricao: String, pageable: Pageable): Page<Movimento> {
        return movimentoRepository.filter(id, descricao, pageable)
    }

    fun obter(id: String): Movimento {
        return movimentoRepository.findByIdOrNull(id) ?: throw BadRequestException("[id] nao encontrado")
    }

    fun excluir(id: String) {
        val movimento = this.obter(id)
        movimentoRepository.delete(movimento)
    }

    fun salvar(movimento: Movimento): String {
        if(movimento.id.isBlank()){
            movimento.id = UUID.randomUUID().toString()
        }
        val regexId = Regex("[a-z0-9-]{1,36}")
        if(!regexId.matches(movimento.id)){
            throw BadRequestException("[id] invalido")
        }
        val regexDescricao = Regex("[a-z0-9-]{1,50}")
        if(!regexDescricao.matches(movimento.descricao)){
            throw BadRequestException("[descricao] invalido")
        }
        return movimentoRepository.save(movimento).id
    }

}