package com.example.inflearnspring

import org.springframework.stereotype.Component

interface HelloService {
    fun hello(name: String): String
}

@Component
class SimpleHelloService : HelloService {

    override fun hello(name: String): String {
        return "Hello, $name"
    }
}

class ComplexHelloService {

    fun hello(name: String): String {
        return "Hello, $name"
    }
}
