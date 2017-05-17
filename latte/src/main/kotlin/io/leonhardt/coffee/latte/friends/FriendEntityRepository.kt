package io.leonhardt.coffee.latte.friends

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FriendEntityRepository : JpaRepository<FriendEntity, UUID>
