import org.junit.jupiter.api.Assertions.*
import type.Actions
import type.Events
import type.States

class ConcreteOrderStateMachineTest {
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
//  orderStateMachine.transit(Events.PLACE_ORDER)

}
