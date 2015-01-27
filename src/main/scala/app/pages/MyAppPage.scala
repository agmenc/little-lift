package app.pages

import little.lift.page.Page

import scala.xml.NodeSeq

trait MyAppPage extends Page {
  override def decorate(content: NodeSeq) = <div class="lift:surround?with=myApp;at=content">{ content }</div>
}