package app.agent

import little.lift.Agent
import net.liftweb.http.js.JsCmd

class ExampleRootAgent extends Agent {
  override def render = <div>This page was rendered for you by {this.getClass.getSimpleName}</div>

  def onSomeStimulus(stimulus: String): JsCmd = $("#outputDiv").text(s"I was stimulated by: $stimulus")
}