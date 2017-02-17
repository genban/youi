package io.youi.example

import scribe.Logging
import io.youi.app.ClientApplication
import io.youi.example.screen.{CommunicationScreen, LoginScreen}
import io.youi.example.ui.UIExamples

import scala.scalajs.js.JSApp

object ClientExampleApplication extends JSApp with ExampleApplication with ClientApplication with Logging {
  val communication = CommunicationScreen
  val login = LoginScreen

  val uiExamples = UIExamples

  override def main(): Unit = {
    logger.info("Initialized!")
  }
}