import java.time.OffsetDateTime

class StateMachineExample1<STATE, EVENT>(private val routes: List<Route<STATE, EVENT>>) {
  fun transit(from: STATE, event: EVENT, at: OffsetDateTime): Transition<STATE, EVENT> {
    val route = this.routes.filter { it.event == event }.find { route -> route.from == from }
      ?: throw NoRouteDefined("No route from ${from} with event ${event}")
    return Transition.Basic(route, at)
  }

  interface Route<STATE, EVENT> {
    val from: STATE
    val event: EVENT
    val to: STATE

    data class Basic<STATE, EVENT>(
      override val from: STATE,
      override val event: EVENT,
      override val to: STATE,
    ): Route<STATE, EVENT>
  }

  interface Transition<STATE, EVENT>: Comparable<Transition<STATE, EVENT>> {
    val from: STATE
    val event: EVENT
    val to: STATE
    val at: OffsetDateTime

    override fun compareTo(other: Transition<STATE, EVENT>): Int = this.at.compareTo(other.at)

    data class Basic<STATE, EVENT>(
      override val from: STATE,
      override val event: EVENT,
      override val to: STATE,
      override val at: OffsetDateTime
    ): Transition<STATE, EVENT> {
      constructor(route: Route<STATE, EVENT>, at: OffsetDateTime):
        this(from = route.from, event = route.event, to = route.to, at = at)
    }
  }

  class NoRouteDefined(reason: String): Exception(reason)
}
