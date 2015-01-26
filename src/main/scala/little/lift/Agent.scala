package little.lift

import scala.xml.NodeSeq

trait Agent {
  def render: NodeSeq
}