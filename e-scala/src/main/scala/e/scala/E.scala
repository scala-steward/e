package e.scala

/** A generic and immutable error, containing helpful information
  *
  * @param code
  *   A numeric code identifying this error
  * @param name
  *   A name identifying this error, usually enum-like
  * @param message
  *   A message about this error, usually human-readable
  * @param causes
  *   Underlying cause(s) of this error, if any
  * @param data
  *   Arbitrary data related to this error as a key-value map
  * @param time
  *   Time when this error occurred as milliseconds, see java.lang.System#currentTimeMillis
  */
final case class E(
    code: Option[Int] = None,
    name: Option[String] = None,
    message: Option[String] = None,
    causes: List[E] = List.empty,
    data: Map[String, String] = Map.empty,
    time: Option[Long] = None
):
    /** Constructs an E containing given code
      *
      * @param c
      *   A code
      * @return
      *   A new E containing given code
      */
    def code(c: Int): E = copy(code = Some(c))

    /** Constructs an E containing given name
      *
      * @param n
      *   A name
      * @return
      *   A new E containing given name
      */
    def name(n: String): E = copy(name = Some(n))

    /** Constructs an E containing given message
      *
      * @param m
      *   Message to set
      * @return
      *   A new E containing given message
      */
    def message(m: String): E = copy(message = Some(m))

    /** Constructs an E adding given causes
      *
      * @param c
      *   Causes as a List
      * @return
      *   A new E containing causes of this E and given causes
      */
    def causes(c: List[E]): E = copy(causes = causes ++ c)

    /** Constructs an E adding given causes
      *
      * @param c
      *   Causes as variable arguments
      * @return
      *   A new E containing causes of this E and given causes
      */
    def causes(c: E*): E = copy(causes = causes ++ c.toList)

    /** Constructs an E adding given cause
      *
      * @param e
      *   A cause
      * @return
      *   A new E containing causes of this E and given causes
      */
    def cause(e: E): E = copy(causes = causes :+ e)

    /** Constructs an E adding given data
      *
      * @param d
      *   Data as a key-value map
      * @return
      *   A new E containing data of this E and given data
      */
    def data(d: Map[String, String]): E = copy(data = data ++ d)

    /** Constructs an E adding given data entry
      *
      * @param k
      *   Key of data entry
      * @param v
      *   Value of data entry
      * @tparam V
      *   Type of value of data entry
      * @return
      *   A new E containing data of this E and given data entry
      */
    def data[V](k: String, v: V): E = copy(data = data + (k -> v.toString))

    /** Constructs an E adding given data entry
      *
      * @param t
      *   Data entry
      * @tparam V
      *   Type of value of data entry
      * @return
      *   A new E containing data of this E and given data entry
      */
    def data[V](t: (String, V)): E = copy(data = data + (t._1 -> t._2.toString))

    /** Constructs an E containing given time
      *
      * @param t
      *   A time
      * @return
      *   A new E containing given time
      */
    def time(t: Long): E = copy(time = Some(t))

    /** Constructs an E containing time set to now
      *
      * @return
      *   A new E containing time set to now
      * @see
      *   java.lang.System#currentTimeMillis
      */
    def now: E = copy(time = Some(System.currentTimeMillis))

    /** Constructs an E adding given cause if condition holds
      *
      * @param condition
      *   Some condition on which to add the cause
      * @param e
      *   A cause
      * @return
      *   A new E containing causes of this E and given cause or this E as is if condition doesn't hold
      */
    def causeIf(condition: Boolean, e: => E): E = if condition then copy(causes = causes :+ e) else this

    /** Whether or not a code is set
      */
    val hasCode: Boolean = code.isDefined

    /** Whether or not a name is set
      */
    val hasName: Boolean = name.isDefined

    /** Whether or not a message is set
      */
    val hasMessage: Boolean = message.isDefined

    /** Whether or not a cause is set
      */
    val hasCause: Boolean = causes.nonEmpty

    /** Whether or not a data is set
      */
    val hasData: Boolean = data.nonEmpty

    /** Whether or not a time is set
      */
    val hasTime: Boolean = time.isDefined

    /** Converts this E to a failed EOr
      *
      * @tparam A
      *   The A type in resulting EOr
      * @return
      *   An EOr containing this E
      * @see
      *   [[e.scala.EOr]]
      */
    def toEOr[A]: EOr[A] = EOr(this)

    /** Converts this E into an exception
      *
      * @return
      *   An [[e.scala.EException]] containing this E
      */
    def toException: EException = EException(this)

    override def toString: String =
        def quote(s: String): String = s.replaceAll("\"", "\\\"")

        List(
          code.map(c => s""""code":$c"""),
          name.map(n => s""""name":"${quote(n)}""""),
          message.map(m => s""""message":"${quote(m)}""""),
          if !hasCause then None else Some(s""""causes":${causes.mkString("[", ",", "]")}"""),
          if !hasData then None
          else
              Some(
                s""""data":${data
                        .map:
                            case (k, v) => s""""${quote(k)}":"${quote(v)}""""
                        .mkString("{", ",", "}")}"""
              )
          ,
          time.map(t => s""""time":$t""")
        ).collect:
            case Some(s) => s
        .mkString(
              start = "{",
              sep = ",",
              end = "}"
            )

object E:
    /** An empty E
      */
    val empty: E = E()

    /** Constructs an E containing given code
      *
      * @param c
      *   A code
      * @return
      *   A new E containing given code
      */
    def code(c: Int): E = E(code = Some(c))

    /** Constructs an E containing given name
      *
      * @param n
      *   A name
      * @return
      *   A new E containing given name
      */
    def name(n: String): E = E(name = Some(n))

    /** Constructs an E containing given message
      *
      * @param m
      *   Message to set
      * @return
      *   A new E containing given message
      */
    def message(m: String): E = E(message = Some(m))

    /** Constructs an E containing given causes
      *
      * @param c
      *   Causes as a List
      * @return
      *   A new E containing given causes
      */
    def causes(c: List[E]): E = E(causes = c)

    /** Constructs an E containing given causes
      *
      * @param c
      *   Causes as variable arguments
      * @return
      *   A new E containing given causes
      */
    def causes(c: E*): E = E(causes = c.toList)

    /** Constructs an E containing given cause
      *
      * @param e
      *   A cause
      * @return
      *   A new E containing given cause
      */
    def cause(e: E): E = E(causes = List(e))

    /** Constructs an E containing given data
      *
      * @param d
      *   Data as a key-value map
      * @return
      *   A new E containing given data
      */
    def data(d: Map[String, String]): E = E(data = d)

    /** Constructs an E containing given data entry
      *
      * @param k
      *   Key of data entry
      * @param v
      *   Value of data entry
      * @tparam V
      *   Type of value of data entry
      * @return
      *   A new E containing given data entry
      */
    def data[V](k: String, v: V): E = E(data = Map(k -> v.toString))

    /** Constructs an E containing given data entry
      *
      * @param t
      *   Data entry
      * @tparam V
      *   Type of value of data entry
      * @return
      *   A new E containing given data entry
      */
    def data[V](t: (String, V)): E = E(data = Map(t._1 -> t._2.toString))

    /** Constructs an E containing given time
      *
      * @param t
      *   A time
      * @return
      *   A new E containing given time
      */
    def time(t: Long): E = E(time = Some(t))

    /** Constructs an E containing time set to now
      *
      * @return
      *   A new E containing time set to now
      * @see
      *   java.lang.System#currentTimeMillis
      */
    def now: E = E(time = Some(System.currentTimeMillis))

    /** Constructs an E containing given cause if condition holds
      *
      * @param condition
      *   Some condition on which to set the cause
      * @param e
      *   A cause
      * @return
      *   A new E containing given cause or empty E if condition doesn't hold
      */
    def causeIf(condition: Boolean, e: => E): E = if condition then E(causes = List(e)) else empty

    /** Constructs an E from given [[_root_.scala.Throwable]]
      *
      * @param throwable
      *   A Throwable
      * @return
      *   A new E containing message of given Throwable or wrapped E in EException if Throwable is one
      */
    def fromThrowable(throwable: Throwable): E =
        throwable match
            case EException(e) => e
            case _             => E(message = Some(throwable.getMessage))
