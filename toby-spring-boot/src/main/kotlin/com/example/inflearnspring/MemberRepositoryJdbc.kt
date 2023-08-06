package com.example.inflearnspring

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class MemberRepositoryJdbc(
    private val jdbcTemplate: JdbcTemplate,
) : MemberRepository {
    override fun findMember(name: String): Member? {
        return try {
            jdbcTemplate.queryForObject(
                "SELECT * FROM MEMBER WHERE name = '$name'",
            ) { rs, _ ->
                Member(
                    id = rs.getLong("id"),
                    name = rs.getString("name"),
                    age = rs.getInt("age"),
                )
            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override fun increaseAge(name: String) {
        val member = findMember(name)
        if (member == null) jdbcTemplate.update(
            "INSERT INTO MEMBER VALUES (?, ?, ?)",
            1,
            name,
            1,
        )
        else jdbcTemplate.update(
            "UPDATE MEMBER SET age = ? WHERE name = ?",
            member.age + 1,
            member.name,
        )
    }

}
