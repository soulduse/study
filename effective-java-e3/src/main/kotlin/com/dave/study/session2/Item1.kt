package com.dave.study.session2

import java.math.BigInteger
import java.util.Random

/**
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 */
class Item1 {
    fun test() {
        /**
         * 1. 이름을 가질 수 있다.
         * - BigInteger.probablePrime와 같이 직접 생성 하는것 보다 값이 소수인것을 더 잘 표현한다.
         */
        BigInteger.probablePrime(5, Random())
        Foo("Dave")
        Foo.withName("Dave")
        Foo.withAddress("Street 1-4")

        /**
         * 2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
         * - Boolean.valueOf
         * - 불편 클래스를 미리 만들어 놓거나 새로 생성한 클래스를 재활용하는 식으로 불필요한 객체 생성 방지
         * - 같은 객체 생성이 자주 되는 곳에 사용한다면 성능을 끌어올릴 수 있음.
         */
        Foo.getFoo()

        /**
         * 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
         */
        FooInterface.getFoo()

        /**
         * 4. 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
         */
        Foo.getFoo(false)

        /**

         */


    }
}

interface FooInterface {
    companion object {
        fun getFoo(): Foo {
            return Foo()
        }
    }
}

open class Foo {

    var name: String? = null
    var address: String? = null

    constructor()

    constructor(name: String) {
        this.name = name
    }

    companion object {
        val GOOD_NIGHT = Foo()

        fun withName(name: String) = Foo(name)

        fun withAddress(address: String) = Foo().apply {
            this.address = address
        }

        fun getFoo() = GOOD_NIGHT

        fun getFoo(flag: Boolean): Foo = if (flag) GOOD_NIGHT else ChildFoo()
    }
}

class ChildFoo : Foo()
