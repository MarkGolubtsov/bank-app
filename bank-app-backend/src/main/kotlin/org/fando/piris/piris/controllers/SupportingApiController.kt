package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("supporting")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class SupportingApiController {

    @GetMapping("countries")
    fun getCountries() = ResponseEntity.ok(CountriesEnum.values())

    @GetMapping("disabilities")
    fun getDisabilities() = ResponseEntity.ok(DisabilityEnum.values().map { it.disability })

    @GetMapping("martial-statuses")
    fun getMartialStatuses() = ResponseEntity.ok(FamilyStatusEnum.values().map { it.status })

    @GetMapping("gender")
    fun getGenders() = ResponseEntity.ok(GenderEnum.values().map { it.gender })

    @GetMapping("cities")
    fun getCities(@RequestParam("country") country: CountriesEnum) = when (country) {
        CountriesEnum.BLR -> ResponseEntity.ok(BelarusCities.values().map { it.city })
        CountriesEnum.RUS -> ResponseEntity.ok(RussiaCities.values().map { it.city })
        CountriesEnum.UKR -> ResponseEntity.ok(UkraineCities.values().map { it.city })
        else -> ResponseEntity.badRequest().body("Invalid country passed")
    }
}