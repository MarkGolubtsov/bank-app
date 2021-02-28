package org.fando.piris.piris.entities

import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractJpaPersistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AbstractJpaPersistable
        if (id != other.id) return false
        return true
    }

}