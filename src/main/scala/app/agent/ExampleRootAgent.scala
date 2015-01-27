package app.agent

import little.lift.Agent
import net.liftweb.http.CometActor
import net.liftweb.http.SHtml._
import net.liftweb.http.js.JsCmd
import org.agmenc.lijq._

class ExampleRootAgent(parent: CometActor) extends Agent {
  private var data = "Look, squirrel!"

  override def render =
    <div>
      <p>This page was rendered for you by {this.getClass.getSimpleName}</p>
      <p id="output">and it is boring</p>
      <form class="form-inline lift:form.ajax">
        <label class="pull-left bottomless" for="startDate">
          Please enter some stimulus:&nbsp;
          {text(data, data = _, "id" -> "startDate", "class" -> "input-small")}
        </label>
        {ajaxSubmit("Comet me up", turnAjaxRequestIntoCometUpdate, "id" -> "stimulateButton", "class" -> "btn")}
      </form>
    </div>

  def turnAjaxRequestIntoCometUpdate(): JsCmd = NoChange { parent ! data }

  def onSomeStimulus(stimulus: String): JsCmd = $("#output").text(s"I was stimulated by: $stimulus")
}