package com.example.inflearnspring

import org.springframework.stereotype.Service

interface HelloService {
    fun hello(name: String): String
    fun ageOf(name: String): Int
}

@Service
class SimpleHelloService(
    private val memberRepository: MemberRepository,
) : HelloService {

    override fun hello(name: String): String {
        memberRepository.increaseAge(name)
        return "Hello, $name"
    }

    override fun ageOf(name: String): Int {
        return memberRepository.ageOf(name)
    }
}

class ComplexHelloService {

    fun hello(name: String): String {
        return "Hello, $name"
    }
}
