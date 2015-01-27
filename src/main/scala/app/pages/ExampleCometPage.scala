package app.pages

import app.comet.ExampleCometActor
import net.liftweb.sitemap.Loc

case class ExampleCometPage(override val params: Loc.LocParam[Any]*) extends MyAppPage {
  def render() = decorate(
      <lift:comet type={classOf[ExampleCometActor].getSimpleName}/>
      <div style="clear:both"></div>
  )
}