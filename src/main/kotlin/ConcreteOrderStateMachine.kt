import type.Actions
import type.Events
import type.States

typealias Path = Map<Events, Pair<States, Actions>>

class ConcreteOrderStateMachine (
  private val transition: Map<States, List<Path>>,
  // should be initialized
  var state: States = States.READY
) {
  fun transit(event: Events): Map<States, Path> {
    val targetState = transition[state]?.find { it.containsKey(event) }?.get(event)?.first
    val action =  transition[state]?.find { it.containsKey(event) }?.get(event)?.second

    val result = mapOf(state to mapOf(event to Pair(targetState!!, action!!)))
    this.state = targetState
    action.act()

    return result
  }
}



