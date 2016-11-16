package io.youi.example

import java.io.File

import io.youi.http.{HttpRequest, HttpResponse}
import io.youi.net.ContentType
import io.youi.net.URLMatcher._
import io.youi.server.UndertowServer
import io.youi.server.handler.{CachingManager, HttpHandler, SenderHandler}

object ExampleServer extends UndertowServer {
  def main(args: Array[String]): Unit = {
    handlers.add(path.exact("/hello.txt"))(SenderHandler("Hello, World!", ContentType.Text, caching = CachingManager.MaxAge(120L)))
    handlers.add(path.exact("/test.txt"))(SenderHandler(new File("src/main/web/test.txt"), ContentType.Text, caching = CachingManager.LastModified()))
    handlers.add(path.exact("/cookies.html"))(CookiesExample)

    start()
  }
}