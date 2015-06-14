package fpinscala.datastructures

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0 
    case Cons(x,xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x,xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h,t) => Cons(h, append(t, a2))
    }

  def tail[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("tail of empty list")
      case Cons(_,t) => t
    }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => sys.error("setHead on empty list")
    case Cons(_,t) => Cons(h,t)
  }

  def drop[A](l: List[A], n: Int): List[A] =
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_,t) => drop(t, n-1)
    }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
    l match {
      case Cons(h,t) if f(h) => dropWhile(t, f)
      case _ => l
    }

  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_,Nil) => Nil
      case Cons(h,t) => Cons(h,init(t))
    }

  // right -> left order (not tail-recursion,)
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  // left -> right order
  @annotation.tailrec
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B =
    as match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f) 
    }

  def length[A](as: List[A]): Int =
    foldLeft(as, 0)((acc, _) => acc + 1)

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def sum3(ns: List[Int]) =
    foldLeft(ns, 0)(_ + _)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _)

  def product3(ns: List[Double]) =
    foldLeft(ns, 1.0)(_ * _)

  def reverse[A](as: List[A]): List[A] =
    foldLeft(as, Nil: List[A])((acc, l) => Cons(l, acc))

  // foldLeft를 foldRight로 구현 가능?
  // foldRight를 foldLeft로 구현 가능?
  // 가능할 것 같긴 한데...
  def foldLeft2[A, B](as: List[A], z: B)(f: (B, A) => B): B =
    foldRight(reverse(as), z)((a: A, b: B) => f(b, a))

  // 그냥 돌리는 건 말이 안되는 듯...
  // 구현이 틀렸는데;;; 어떻게 오른쪽 -> 왼쪽으로 하지?
  def foldRight2[A,B](as: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(as, z)((a: B, b: A) => f(b, a))

  // append를 foldLeft, foldRight
  // a2 = a,b,c
  // a1 = 1,2,3
  // 1,2,3,a,b,c
  def append2[A](a1: List[A], a2: List[A]): List[A] =
    //reverse(foldLeft(a2, reverse(a1))((acc, l) => Cons(l, acc)))
    foldRight(a1, a2)(Cons(_, _))

  def concat[A](l: List[List[A]]): List[A] =
    foldRight(l, Nil: List[A])(append)

  def incMap(l: List[Int]): List[Int] =
    foldRight(l, Nil: List[Int])((l, acc) => Cons(l + 1, acc))

  def map[A,B](l: List[A])(f: A => B): List[B] = 
    foldRight(l, Nil: List[B])((i, acc) => Cons(f(i), acc))

  def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =
    foldRight(l, Nil: List[B])((i, acc) => append(f(i), acc))

  // more clear answer better than me. it's simple
  def flatMapAns[A,B](l: List[A])(f: A => List[B]): List[B] =
    concat(map(l)(f))

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil: List[A])((i, acc) => 
      if (f(i)) Cons(i, acc)
      else acc
    )

  def filterViaFlatMap[A](as: List[A])(f: A => Boolean): List[A] =
    flatMap(as)((h) => if (f(h)) List(h) else Nil)

  // 음..이러면 두 개가 갯수가 같은 경우만 되는거 같은데;; 원래 정의가 그런가?
  def zipWith[A,B,C](as: List[A], bs: List[B])(f: (A, B) => C): List[C] =
    (as, bs) match {
      case (_, Nil) => Nil
      case (Nil, _) => Nil
      case (Cons(hl, tl), Cons(hr, tr)) => Cons(f(hl, hr), zipWith(tl, tr)(f))

    }


}


