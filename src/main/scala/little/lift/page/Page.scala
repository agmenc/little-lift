package little.lift.page

import xml.{Elem, NodeSeq}
import net.liftweb.common.Full
import net.liftweb.sitemap.{ConvertableToMenu, Menu, Loc}
import net.liftweb.util.StringHelpers
import net.liftweb.sitemap.Menu.Menuable

trait Page {
  protected def title: String = this.getClass.getSimpleName
  protected def path: String = StringHelpers.snakify(title)
  protected def params: Seq[Loc.LocParam[Any]] = Seq()

  def render(): NodeSeq

  def menuItem(): ConvertableToMenu = params.foldLeft(menuRoot)((m, p) => m >> p)

  private def menuRoot: Menuable = Menu.i(title) / path

  protected def decorate(content: NodeSeq): Elem

  private lazy val pathAsList = path.split("/").toList

  protected[page] def view: PartialFunction[List[String], Left[() => Full[NodeSeq], Nothing]] = {
    case `pathAsList` ⇒ display()
  }

  protected def display() = Left(() ⇒ Full(render()))
}
