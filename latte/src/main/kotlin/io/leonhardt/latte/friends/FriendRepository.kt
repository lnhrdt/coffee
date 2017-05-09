package io.leonhardt.latte.friends

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FriendRepository : CrudRepository<Friend, UUID>
