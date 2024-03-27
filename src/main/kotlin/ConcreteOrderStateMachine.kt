import type.Actions
import type.Events
import type.States

typealias Result = Map<Events, Pair<States, Actions>>

class ConcreteOrderStateMachine (
  private val transition: Map<States, List<Result>>,
  // should be initialized
  var state: States = States.READY
) {
  fun transit(event: Events): Map<States, Result> {
    val targetState = transition[state]?.find { it.containsKey(event) }?.get(event)?.first
    val action =  transition[targetState]?.find { it.containsKey(event) }?.get(event)?.second
    println("Event: $event, State: $targetState, Action: $action")

    val result = mapOf(state to mapOf(event to Pair(targetState!!, action!!)))
    this.state = targetState
    action.act()

    return result
  }
}
