package little.lift

import im.mange.little.LittleServer

object LittleApp extends App {
  private val port = 8080
  new LittleServer(port)
  println(s"### Started ${this.getClass.getSimpleName} on $port")
}