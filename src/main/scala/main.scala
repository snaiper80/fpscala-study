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

	}
}