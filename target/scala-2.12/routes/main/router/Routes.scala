// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/joaop/Documents/GimmeWebApp/conf/routes
// @DATE:Sat Nov 17 17:54:20 GMT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:9
  Assets_1: controllers.Assets,
  // @LINE:13
  WebServiceController_2: controllers.WebServiceController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:9
    Assets_1: controllers.Assets,
    // @LINE:13
    WebServiceController_2: controllers.WebServiceController
  ) = this(errorHandler, HomeController_0, Assets_1, WebServiceController_2, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, WebServiceController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """session""", """controllers.WebServiceController.getSession"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user-info""", """controllers.WebServiceController.getUserInfo"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_Assets_versioned1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_WebServiceController_getSession2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("session")))
  )
  private[this] lazy val controllers_WebServiceController_getSession2_invoker = createInvoker(
    WebServiceController_2.getSession,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebServiceController",
      "getSession",
      Nil,
      "POST",
      this.prefix + """session""",
      """Web Service""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_WebServiceController_getUserInfo3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user-info")))
  )
  private[this] lazy val controllers_WebServiceController_getUserInfo3_invoker = createInvoker(
    WebServiceController_2.getUserInfo,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebServiceController",
      "getUserInfo",
      Nil,
      "POST",
      this.prefix + """user-info""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:9
    case controllers_Assets_versioned1_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(Assets_1.versioned(path, file))
      }
  
    // @LINE:13
    case controllers_WebServiceController_getSession2_route(params@_) =>
      call { 
        controllers_WebServiceController_getSession2_invoker.call(WebServiceController_2.getSession)
      }
  
    // @LINE:14
    case controllers_WebServiceController_getUserInfo3_route(params@_) =>
      call { 
        controllers_WebServiceController_getUserInfo3_invoker.call(WebServiceController_2.getUserInfo)
      }
  }
}
