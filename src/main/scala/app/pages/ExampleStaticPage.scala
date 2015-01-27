package app.pages

import net.liftweb.sitemap.Loc

case class ExampleStaticPage(override val path: String, override val params: Loc.LocParam[Any]*) extends MyAppPage {
  def render() = decorate(
    <div class="hero-unit">
      <p>Animals:</p>
      <div class="row">
        <div class="span3">
          Monkeys monkeys monkeys
        </div>
      </div>
    </div>
  )
}