package com.equalexperts

import scala.math.BigDecimal
import scala.collection.mutable.ListBuffer

class Item(val name: String, val price: BigDecimal)

/**
 * @author Anonymous
 */
class ShoppingCart {
  
  private var _items: ListBuffer[Item] = ListBuffer()
  private var _total = BigDecimal("0")
  private var _tax = BigDecimal("0")

  def add(name: String, price: BigDecimal, count: Int) {
    for(_ <- 1 to count) {
      _items += new Item(name, price)
      _total += price
    }
  }

  def addTax(rate: BigDecimal) {
    _tax = (_total * rate / BigDecimal(100.00))
    _total += _tax
  }
  
  def receipt: String = {
    var _receipt: String = ""
    for(item <- _items) {
      var tabs: String = "\t"
      if(item.name.size < 8) tabs += "\t"
      _receipt += (item.name + tabs + item.price + "\n")
    }
    if(_tax != 0) {
      _receipt += ("Tax:\t\t" + tax + "\n")
    }
    _receipt += ("Total:\t\t" + total + "\n")
    return _receipt
  }

  def items: ListBuffer[Item] = _items

  def tax: BigDecimal = {
    return _tax.setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

  def total: BigDecimal = {
    return _total.setScale(2, BigDecimal.RoundingMode.HALF_UP)
  }

}
