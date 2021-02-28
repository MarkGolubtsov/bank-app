package org.fando.piris.piris.services

import org.fando.piris.piris.entities.Address
import org.fando.piris.piris.models.Addresses
import org.fando.piris.piris.repositories.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AddressesService @Autowired constructor(
        val addressRepository: AddressRepository
) {

    fun saveAddress(requestAddress: Addresses): Address {
        val address = Address(
                requestAddress.country,
                requestAddress.city,
                requestAddress.street,
                requestAddress.buildingNumber,
                requestAddress.apartsNumber
        )
        return addressRepository.save(address);
    }
}