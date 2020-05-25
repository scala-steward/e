import scala.util.Try

package object e {
  /**
   * Type alias for EOr, allowing a more pleasing syntax as following:
   *
   * {{{
   *   String or E // instead of EOr[String]
   * }}}
   *
   * @tparam A Type of the value EOr can contain
   *
   * @see [[e.E]]
   * @see [[e.EOr]]
   */
  type or[+A, _ <: E] = EOr[A]

  implicit class ValueExtensions[A](a: => A) {
    /**
     * Converts this value to a successful A or E
     *
     * @return An A or E containing this value
     *
     * @see [[e.EOr]]
     */
    @inline def orE: A or E = EOr(a)

    /**
     * Constructs an EOr from evaluating this value and by converting caught Exception using given function
     *
     * @param ifFailure An E conversion function in case evaluating this value throws
     *
     * @return An EOr containing either evaluated value or an E computed by given function
     */
    @inline def catching(ifFailure: Throwable => E): A or E = EOr.fromTry(Try(a), ifFailure)
  }

  implicit class OptionExtensions[A](option: Option[A]) {
    /**
     * Constructs an EOr from this [[scala.Option]]
     *
     * @param ifNone An E to use in case this Option is None
     *
     * @return An EOr containing either value in this Option or given E
     */
    @inline def orE(ifNone: => E): A or E = EOr.fromOption(option, ifNone)
  }

  implicit class EitherExtensions[L, R](either: Either[L, R]) {
    /**
     * Constructs an EOr from this [[scala.util.Either]]
     *
     * @param ifLeft An E conversion function in case this Either is Left
     *
     * @return An EOr containing either Right value in this Either or an E computed by given function
     */
    @inline def orE(ifLeft: L => E): R or E = EOr.fromEither(either, ifLeft)
  }

  implicit class TryExtensions[A](`try`: Try[A]) {
    /**
     * Constructs an EOr from this [[scala.util.Try]]
     *
     * @param ifFailure An E conversion function in case this Try is Failure
     *
     * @return An EOr containing either value in this Try or an E computed by given function
     */
    @inline def orE(ifFailure: Throwable => E): A or E = EOr.fromTry(`try`, ifFailure)
  }

  implicit class ThrowableExtensions(throwable: Throwable) {
    /**
     * Constructs an E containing message of this [[java.lang.Throwable]]
     *
     * @return A new E containing message of this Throwable
     */
    @inline def toE: E = E.fromThrowable(throwable)
  }
}