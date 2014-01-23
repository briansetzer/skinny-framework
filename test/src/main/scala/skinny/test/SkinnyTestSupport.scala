package skinny.test

import org.scalatra.test._
import skinny.controller.{ SkinnySessionInjectorController, SessionInjectorController }
import skinny.SkinnyEnv

/**
 * Skinny framework testing support
 */
trait SkinnyTestSupport { self: ScalatraTests =>

  // set skinny.env as "test"
  System.setProperty(SkinnyEnv.PropertyKey, "test")

  /**
   * Session injector controller
   */
  object SessionInjector extends SessionInjectorController {
    put("/tmp/SkinnyTestSupport/session")(update)
  }
  addFilter(SessionInjector, "/tmp/SkinnyTestSupport/session")

  object SkinnySessionInjector extends SkinnySessionInjectorController {
    put("/tmp/SkinnyTestSupport/skinnySession")(update)
  }
  addFilter(SkinnySessionInjector, "/tmp/SkinnyTestSupport/skinnySession")

  /**
   * Provides a code block with injected session.
   *
   * @param attributes attributes to inject
   * @param action code block
   * @tparam A return type
   * @return result
   */
  def withSession[A](attributes: (String, String)*)(action: => A): A = session {
    put("/tmp/SkinnyTestSupport/session", attributes)()
    action
  }

  /**
   * Provides a code block with injected session.
   *
   * @param attributes attributes to inject
   * @param action code block
   * @tparam A return type
   * @return result
   */
  def withSkinnySession[A](attributes: (String, String)*)(action: => A): A = session {
    put("/tmp/SkinnyTestSupport/skinnySession", attributes)()
    action
  }

}
