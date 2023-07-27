package com.example.inflearnspring

interface HelloService {
    fun hello(name: String): String
}

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
