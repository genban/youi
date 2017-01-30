package io.youi.app

import com.outr.reactify._
import io.youi.communication.Communication
import io.youi.http.Connection

import scala.language.experimental.macros

/**
  * Base trait to define shared client and server information. This trait must be extended in the shared code as a trait
  * and implemented in both client and server implementations.
  */
trait YouIApplication {
  /**
    * Current Connection. On the server this will be defined as part of ThreadLocal. On the client this will represent
    * a singleton.
    */
  def connection: Connection

  /**
    * Listing of all active connections. On the client this will only every have one entry.
    */
  def connections: Val[Set[Connection]]

  def communication[C <: Communication]: CommunicationManager[C] = macro Macros.communication[C]
}

class CommunicationManager[C <: Communication](application: YouIApplication, create: Connection => C) {
  val instances: Val[Set[C]] = Val(application.connections.map(create))

  def current: C = instances.find(_.connection eq application.connection).get
}