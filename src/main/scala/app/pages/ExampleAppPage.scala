package app.pages

import little.lift.page.Page

import scala.xml.NodeSeq

trait ExampleAppPage extends Page {
  override def decorate(content: NodeSeq) = <div class="lift:surround?with=exampleWrapper;at=content">{ content }</div>
}