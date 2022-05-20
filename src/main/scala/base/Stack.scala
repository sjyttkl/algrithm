package base

/**
 * Create with: base
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2021/4/13 15:49
 * version: 1.0
 * description: 
 */
class Stack[A] {
  private var elements: List[A] = Nil

  def push(x: A): Unit = {
    elements = x :: elements
  }

  def peek: A = elements.head

  def pop(): A = {
    var currentTop = peek
    elements = elements.tail
    currentTop
  }

}
