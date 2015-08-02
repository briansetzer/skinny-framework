package skinny.engine

import skinny.engine.data.{ MapWithIndifferentAccess, MultiMapHeadView }

/**
 * Params.
 */
class EngineParams(
  protected val multiMap: Map[String, Seq[String]])
    extends MultiMapHeadView[String, String]
    with MapWithIndifferentAccess[String]
