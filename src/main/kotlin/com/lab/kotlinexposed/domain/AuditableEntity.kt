package com.lab.kotlinexposed.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class AuditableEntity<T : AuditableTable>(
    id: EntityID<Long>,
    table: T
) : LongEntity(id) {
    var createdAt by table.createdAt
    var updatedAt by table.updatedAt
}