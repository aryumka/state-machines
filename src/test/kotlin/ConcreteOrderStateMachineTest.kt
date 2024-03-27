import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import type.Actions
import type.Events
import type.States

fun createTransition() = mapOf(
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
    mapOf(Events.DELIVER to Pair(States.DELIVERED, Actions.OrderDelivered())),
    mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn()))
  ),
  States.DELIVERED to listOf(
    mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn()))
  ),
  States.PENDING to listOf(
    mapOf(Events.RETURN to Pair(States.CANCELLED, Actions.orderCanceled()))
  )
)

class ConcreteOrderStateMachineTest: FunSpec({
  context("creating a ConcreteOrderStateMachine") {
    val transition = createTransition()
    val newMachine = ConcreteOrderStateMachine(transition)

    test("initial state is READY") {
      newMachine.state shouldBe States.READY
    }
  }

  context("placing an order") {
    val transition = createTransition()

    val event = Events.PLACE_ORDER
    val machine = ConcreteOrderStateMachine(transition)
    val result = machine.transit(event)

    val sourceState = result.keys.first()
    test("source state should be READY") {
      sourceState shouldBe States.READY
    }

    val target = result[sourceState]
    test("target state should be PLACED") {
      target?.values?.first()?.first shouldBe States.PLACED
    }

    val action = target?.values?.first()?.second
    test("action should be OrderPlaced") {
      action is Actions.OrderPlaced
    }

//    test("order is actually placed") {
//      verify { action?.act() }
//    }

    test("current state of machine should be PLACED") {
      machine.state shouldBe States.PLACED
    }
  }

  context("paying for an order") {
    val transition = createTransition()

    val event = Events.PAY
    val machine = ConcreteOrderStateMachine(transition)
    machine.state = States.PLACED
    val result = machine.transit(event)

    val sourceState = result.keys.first()
    test("source state should be PLACED") {
      sourceState shouldBe States.PLACED
    }

    val target = result[sourceState]
    test("target state should be PAID") {
      target?.values?.first()?.first shouldBe States.PAID
    }

    val action = target?.values?.first()?.second
    test("action should be PaymentMade") {
      action is Actions.PaymentMade
    }

//    test("payment is actually made") {
//      verify { action?.act() }
//    }

    test("current state of machine should be PAID") {
      machine.state shouldBe States.PAID
    }
  }
})
