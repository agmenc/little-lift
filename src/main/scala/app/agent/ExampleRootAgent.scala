package app.agent

import little.lift.Agent
import net.liftweb.http.js.JsCmd
import org.agmenc.lijq._

class ExampleRootAgent extends Agent {
  override def render = <div>This page was rendered for you by {this.getClass.getSimpleName}<br/><div id="outputDiv">boring</div></div>

  def onSomeStimulus(stimulus: String): JsCmd = $("#outputDiv").text(s"I was stimulated by: $stimulus")
}