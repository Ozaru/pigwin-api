package com.github.ozaru.pigwin_api.controllers

import com.github.ozaru.pigwin_api.entities.Movimento
import com.github.ozaru.pigwin_api.services.MovimentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("movimento")
class MovimentoController(
    private val movimentoService: MovimentoService,
) {

    @GetMapping
    fun filtrar(
        @RequestParam("id") id: String = "",
        @RequestParam("descricao") descricao: String = "",
        pageable: Pageable,
    ): Page<Movimento> {
        return movimentoService.filtrar(id, descricao, pageable)
    }

    @GetMapping("{id}")
    fun obter(@PathVariable("id") id: String): Movimento {
        return movimentoService.obter(id)
    }

    @DeleteMapping("{id}")
    fun excluir(@PathVariable("id") id: String) {
        movimentoService.excluir(id)
    }

    @PostMapping
    fun salvar(@RequestBody movimento: Movimento): String {
        return movimentoService.salvar(movimento)
    }

}