package org.fando.piris.piris.controllers

import org.fando.piris.piris.models.BelarusCities
import org.fando.piris.piris.models.CountriesEnum
import org.fando.piris.piris.models.RussiaCities
import org.fando.piris.piris.models.UkraineCities
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("supporting")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class SupportingApiController {

    @GetMapping("countries")
    fun getCountries() = ResponseEntity.ok(CountriesEnum.values())

    @GetMapping("cities")
    fun getCities(@RequestParam("country") country: CountriesEnum) = when (country) {
        CountriesEnum.BLR -> ResponseEntity.ok(BelarusCities.values())
        CountriesEnum.RUS -> ResponseEntity.ok(RussiaCities.values())
        CountriesEnum.UKR -> ResponseEntity.ok(UkraineCities.values())
        else -> ResponseEntity.badRequest().body("Invalid country passed")
    }
}