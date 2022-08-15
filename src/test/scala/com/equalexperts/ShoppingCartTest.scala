package com.equalexperts

import org.junit._
import Assert._
import com.equalexperts._

@Test
class ShoppingCartTest {

  @Test
  def addNone() {
    var cart = new ShoppingCart()

    assert(cart.tax == 0)
    assert(cart.total == 0)
  }

  @Test
  def addOne() {
    var cart = new ShoppingCart()

    var name = "Foo"
    var price = BigDecimal(10.00)
    var count = 1

    cart.add(name, price, count)

    val items = cart.items
    assert(items.size == count)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    assert(cart.tax == 0.00)
    assert(cart.total == 10.00)
  }
  
  @Test
  def addOneWithTax() {
    var cart = new ShoppingCart()

    var name = "Foo"
    var price = BigDecimal(10.00)
    var count = 1

    cart.add(name, price, count)

    val items = cart.items
    assert(items.size == count)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    cart.addTax(10.0)

    assert(cart.tax == 1.00)
    assert(cart.total == 11.00)
  }
  
  @Test
  def addTwo() {
    var cart = new ShoppingCart()

    var name = "Foo"
    var price = BigDecimal(10.00)
    var count = 2

    cart.add(name, price, count)

    val items = cart.items
    assert(items.size == count)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    assert(cart.tax == 0.00)
    assert(cart.total == 20.00)
  }
  
  @Test
  def addTwoWithTax() {
    var cart = new ShoppingCart()

    var name = "Foo"
    var price = BigDecimal(10.00)
    var count = 2

    cart.add(name, price, count)

    val items = cart.items
    assert(items.size == count)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    cart.addTax(10.0)

    assert(cart.tax == 2.00)
    assert(cart.total == 22.00)
  }
  
  @Test
  def test1() {
    var cart = new ShoppingCart()

    var name = "Dove Soap"
    var price = BigDecimal(39.99)
    var count = 5

    cart.add(name, price, count)

    val items = cart.items
    assert(items.size == count)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    val receipt = cart.receipt
    Console.printf("%s", receipt)

    assert(cart.total == 199.95)
  }
  
  @Test
  def test2() {
    var cart = new ShoppingCart()

    var name = "Dove Soap"
    var price = BigDecimal(39.99)
    var count1 = 5
    var count2 = 3

    cart.add(name, price, count1)
    cart.add(name, price, count2)

    val items = cart.items
    assert(items.size == count1 + count2)

    for(i <- items) {
      assert(i.name == name && i.price == price)
    }

    val receipt = cart.receipt
    Console.printf("%s", receipt)

    assert(cart.total == 319.92)
  }

  @Test
  def test3() {
    var cart = new ShoppingCart()

    var name1 = "Dove Soap"
    var price1 = BigDecimal(39.99)
    var count1 = 2

    var name2 = "Axe Deo"
    var price2 = BigDecimal(99.99)
    var count2 = 2

    cart.add(name1, price1, count1)
    cart.add(name2, price2, count2)

    val items = cart.items
    assert(items.size == count1 + count2)

    var j = 0
    for(i <- items) {
      if(j < count1) {
        assert(i.name == name1 && i.price == price1)
      } else {
        assert(i.name == name2 && i.price == price2)
      }
      j += 1
    }

    cart.addTax(12.5)

    assert(cart.tax == 35.00)

    val receipt = cart.receipt
    Console.printf("%s", receipt)

    assert(cart.total == 314.96)
  }

}


