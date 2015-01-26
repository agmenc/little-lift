package bootstrap.liftweb

import little.lift.page.Pages
import net.liftweb.common.{Full, Loggable}
import net.liftweb.http.{S, Html5Properties, Req, LiftRules}
import net.liftweb.sitemap.Loc.{If, LocGroup}

class Boot extends Loggable {
  def boot { doOrDie(unsafeBoot) }

  def doOrDie(f: () => Unit) = try { f() } catch { case t: Throwable => logger.error(t.getMessage); System.exit(1) }

  val topBar = LocGroup("topBar")

  def unsafeBoot() {
    Pages(
      ExampleStatic("index", redirectIfNotLoggedIn, topBar),
      ExampleComet(topBar),
      Hogwash(topBar),
      Login(topBar),
      Logout(redirectIfNotLoggedIn, topBar)
    )

    LiftRules.sessionInactivityTimeout.default.set(Full(30L))
    LiftRules.addToPackages("web")
    LiftRules.ajaxStart = Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    LiftRules.ajaxEnd = Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
    LiftRules.dispatch.append(Downloads)

    logger.info("Lift has Booted.")
  }

  def redirectIfNotLoggedIn = If(() ⇒ /* Some check on the User object */ true, () ⇒ S.redirectTo("/login"))
}
