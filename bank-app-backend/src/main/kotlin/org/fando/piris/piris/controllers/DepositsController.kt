package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.ResponseDeposit
import org.fando.piris.piris.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("deposit")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class DepositsController  @Autowired constructor(
        val depositService: DepositService,
        val responseService: ResponseService
) {

    @GetMapping("all")
    fun getDepositPrograms() : ResponseEntity<List<ResponseDeposit>> {
        val depositPrograms = depositService.getAllDeposits();
        return ResponseEntity.ok(responseService.generateResponseDepositEntity(depositPrograms))
    }
}