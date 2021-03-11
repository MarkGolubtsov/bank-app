package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.ResponseAccount
import org.fando.piris.piris.services.AccountService
import org.fando.piris.piris.services.ResponseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("accounts")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class AccountController @Autowired constructor(
        private val accountService: AccountService,
        private val responseService: ResponseService
) {

    

    @GetMapping()
    fun getAllAccounts(): ResponseEntity<List<ResponseAccount>>{
        val accounts = accountService.getAllAccounts()
        return ResponseEntity.ok(responseService.generateResponseAccountEntity(accounts))
    }
}