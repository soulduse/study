package com.dave.study.session2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger
import java.util.Random


/**
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 */
class Item1Test {

    /**
     * [----------------------------- 장점 ---------------------------------]
     */

    /**
     * BigInteger.probablePrime와 같이 직접 생성 하는것 보다 값이 소수인것을 더 잘 표현한다.
     */
    @Test
    fun `이름을 가질 수 있다`() {
        BigInteger.probablePrime(5, Random())
        Foo("Dave")
        Foo.withName("Dave")
        Foo.withAddress("Street 1-4")
    }

    /**
     * - Boolean.valueOf
     * - 불편 클래스를 미리 만들어 놓거나 새로 생성한 클래스를 재활용하는 식으로 불필요한 객체 생성 방지
     * - 같은 객체 생성이 자주 되는 곳에 사용한다면 성능을 끌어올릴 수 있음.
     */
    @Test
    fun `호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다`() {
        Foo.getFoo()
    }

    @Test
    fun `반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다`() {
        FooInterface.getFoo()
    }

    @Test
    fun `매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다`() {
        val foo1 = Foo.getFoo(true)
        assertThat(foo1::class).isEqualTo(Foo::class)
        val foo2 = Foo.getFoo(false)
        assertThat(foo2::class).isEqualTo(ChildFoo::class)
    }

    @Test
    fun `정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다`() {
        // 필요한 시점에서 반환하는 클래스가 존재하지 않은 상태에서 나중에 생성한다 리플렉션같은..
    }

    /**
     * [----------------------------- 단점 ---------------------------------]
     */
    @Test
    fun `public 또는 protected 생성자 없이 static public 메소드만 제공하는 클래스는 상속불가`() {
        /**
         * Collections 프레임워크에서 제공하는 편의성 구현체(java.uitl.Collections)는 상속할 수 없다.
         * 오히려 불편타입인 경우나 상속 대신 컴포지션을 권장하기 때문에 장점이라 말할지도 모르겠다.
         *
         * 아래와 같이 사용불가. 이유는 Collections 클래스 내부에 public, protected 생성자가 없고, static public 메소드만 제공.
         * class ChildCollections : Collections() { .. }
         */
    }

    @Test
    fun `프로그래머가 static 팩토리 메소드를 찾는게 어렵다`() {
        /**
         * JavaDoc에 보면 생성자의 경우 summery 가 자동생되어 상단에 모아서 보여주지만, static 팩토리 메소드는 API 문서에 특별히 다뤄주지 않음.
         * 따라서 클래스나 인터페이스 문서 상단에 팩토리 메소드에 대한 문서를 제공하는게 좋다.(주석이용)
         */
    }
}


enum class Color {
    RED, BLUE, WHITE
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
