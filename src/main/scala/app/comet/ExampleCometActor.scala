package app.comet

import app.agent.ExampleRootAgent
import net.liftweb.common.Loggable
import net.liftweb.http.CometActor

class ExampleCometActor extends CometActor with Loggable {
  private val rootAgent = new ExampleRootAgent(this)

  def render = rootAgent.render

  override def lowPriority = {
    case someData: String => partialUpdate(rootAgent.onSomeStimulus(someData))
    case e => throw new Exception("Unexpected message received: " + e)
  }
}