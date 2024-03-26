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

//READY --> PLACED: PLACE_ORDER
//PLACED --> PAID: PAY
//PAID --> SHIPPED: SHIP
//PAID --> CANCELLED: CANCEL
//SHIPPED --> DELIVERED: DELIVER
//SHIPPED --> PENDING: CANCEL
//DELIVERED --> PENDING: CANCEL
//PENDING --> CANCELLED: RETURN
fun main() {
  val transition = mapOf(
    States.READY to listOf(
      mapOf(Events.PLACE_ORDER to Pair(States.PLACED, Actions.OrderPlaced()))
    ),
    States.PLACED to listOf(
      mapOf(Events.PAY to Pair(States.PAID, Actions.PaymentMade()))
    ),
    States.PAID to listOf(
      mapOf(Events.SHIP to Pair(States.SHIPPED, Actions.OrderShipped())),
      mapOf(Events.CANCEL to Pair(States.CANCELLED, Actions.orderCanceled()))
    ),
    States.SHIPPED to listOf(
      mapOf(Events.DELIVER to Pair(States.DELIVERED, Actions.PaymentMade())),
      mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn()))
    ),
    States.DELIVERED to listOf(
      mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn()))
    ),
    States.PENDING to listOf(
      mapOf(Events.RETURN to Pair(States.CANCELLED, Actions.orderCanceled()))
    )
  )

  val orderStateMachine = ConcreteOrderStateMachine(transition)
  orderStateMachine.transit(Events.PLACE_ORDER)
}
