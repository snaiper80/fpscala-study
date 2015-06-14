import fpinscala.datastructures._
import util.Random

object fp {
	def main(args: Array[String]) {
        val l = List(1,2,3,4,5,6,7,8,9)
        var d = List(1.0, 20.0, 30.0)
        
        // 3.10 & 3.11
        println("%s".format(List.sum3(l)))
        println("%s".format(List.product3(d)))

        // 3.12
        println("%s".format(List.reverse(l)))

        // 3.13
        println("%s".format(List.foldLeft2(l, 0)((acc, _) => acc + 1)))
        println("%s".format(List.foldRight2(l, 0)(_ + _)))

        // 3.14
        println("append  = %s".format(List.append(l, d)))
        println("append2 = %s".format(List.append2(l, d)))

        // 3.15
        val ll = List(List(1,2,3), List(4,5,6))
        println("concat  = %s".format(List.concat(ll)))

        // 3.16
        val il = List(1,2,3,4,5,6)
        println("incMap  = %s".format(List.incMap(il)))

        // 3.17
        val l17 = List(1.0, 2.0, 3.0, 4.0)
        val r17 = List.foldRight(l17, Nil: List[String])((l, acc) =>
            Cons(l.toString, acc)) 
        println("toString  = %s".format(r17))

        // 3.18
        val r18 = List.map(il)((h) => h * 2)
        println("map  = %s".format(r18))

        // 3.19
        val r19 = List.filter(il)((h) => h % 2 == 0)
        println("filter  = %s".format(r19))

        // 3.20
        val l20 = List(1, 2, 3)
        val r20 = List.flatMap(l20)((h) => List(h, h))
        println("flatMap  = %s".format(r20))

        // 3.21
        val r21 = List.filterViaFlatMap(il)((h) => h % 2 == 0)
        println("filterViaFlatMap  = %s".format(r21))

        // 3.22 & 3.23
        // precondition: la22.length == lb22.length
        val la22 = List(1, 2, 3)
        val lb22 = List(4, 5, 6)
        val r22  = List.zipWith(la22, lb22)((a, b) => a+b)
        println("zipWith  = %s".format(r22))        

	}
}