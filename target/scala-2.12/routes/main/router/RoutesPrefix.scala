// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/joaop/Documents/GimmeWebApp/conf/routes
// @DATE:Sat Nov 17 17:54:20 GMT 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
