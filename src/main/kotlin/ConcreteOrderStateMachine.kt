import type.Actions
import type.Events
import type.States

typealias TransitionTo = List<Map<Events, Pair<States, Actions>>>

class ConcreteOrderStateMachine (
  private val transition: Map<States, TransitionTo>,
  private var state: States = States.READY
) {
  fun transit(event: Events): States {
    val state = transition[state]?.find { it.containsKey(event) }?.get(event)?.first ?: States.INVALID
    val action =  transition[state]?.find { it.containsKey(event) }?.get(event)?.second?.act()
    println("Event: $event, State: $state, Action: $action")
    this.state = state

    return transition[state]?.find { it.containsKey(event) }?.get(event)?.first ?: state
  }
}
