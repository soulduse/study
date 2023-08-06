package com.example.inflearnspring

interface MemberRepository {
    fun findMember(name: String): Member?
    fun increaseAge(name: String)
    fun ageOf(name: String): Int {
        val member = findMember(name)
        return member?.age ?: 0
    }
}

class Member(
    val id: Long = 0L,
    val name: String = "",
    val age: Int = 0,
)
