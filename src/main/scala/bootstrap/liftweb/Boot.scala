package bootstrap.liftweb

import app.pages.{ExampleStaticPage, ExampleCometPage}
import little.lift.page.Pages
import net.liftweb.common.{Full, Loggable}
import net.liftweb.http._
import net.liftweb.sitemap.Loc.{If, LocGroup}
import net.liftweb.util.NamedPF
import net.liftweb.util.TimeHelpers._

class Boot extends Loggable {
  def boot { doOrDie(unsafeBoot) }

  def doOrDie(f: () => Unit) = try { f() } catch { case t: Throwable => logger.error(t.getMessage); System.exit(1) }

  val topBar = LocGroup("topBar")

  def unsafeBoot() {
    Pages(
      ExampleStaticPage("index", redirectIfNotLoggedIn, topBar),
      ExampleCometPage(topBar)
    )

    LiftRules.sessionInactivityTimeout.default.set(Full(30 minutes: Long))
    LiftRules.addToPackages("app")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
    // LiftRules.dispatch.append(Something that extends RestHelper)

    LiftRules.uriNotFound.prepend(NamedPF("404handler") {
      case (req, failure) ⇒ NotFoundAsTemplate(ParsePath(List("404"), "html", false, false))
    })

    logger.info("Lift has Booted.")
  }

  def redirectIfNotLoggedIn = If(() ⇒ /* Some check on the User object */ true, () ⇒ S.redirectTo("/login"))
}
