import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import type.Actions
import type.Events
import type.States
import java.util.logging.Logger

class ConcreteOrderStateMachineTest: FunSpec({
  val mockLogger = mockk<Logger>()

  val transition = mapOf(
    States.READY to listOf(
      mapOf(Events.PLACE_ORDER to Pair(States.PLACED, Actions.OrderPlaced(logger = mockLogger)))
    ),
    States.PLACED to listOf(
      mapOf(Events.PAY to Pair(States.PAID, Actions.PaymentMade(logger = mockLogger)))
    ),
    States.PAID to listOf(
      mapOf(Events.SHIP to Pair(States.SHIPPED, Actions.OrderShipped(logger = mockLogger))),
      mapOf(Events.CANCEL to Pair(States.CANCELLED, Actions.orderCanceled(logger = mockLogger)))
    ),
    States.SHIPPED to listOf(
      mapOf(Events.DELIVER to Pair(States.DELIVERED, Actions.PaymentMade(logger = mockLogger))),
      mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn(logger = mockLogger)))
    ),
    States.DELIVERED to listOf(
      mapOf(Events.CANCEL to Pair(States.PENDING, Actions.watingForReturn(logger = mockLogger)))
    ),
    States.PENDING to listOf(
      mapOf(Events.RETURN to Pair(States.CANCELLED, Actions.orderCanceled(logger = mockLogger)))
    )
  )

  context("creating a ConcreteOrderStateMachine") {
    val newMachine = ConcreteOrderStateMachine(transition)

    test("initial state is READY") {
      newMachine.state shouldBe States.READY
    }
  }

  context("placing an order") {
    justRun { mockLogger.info("order is placed") }

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

    test("order is actually placed") {
      verify { action?.act() }
    }

    test("current state of machine should be PLACED") {
      machine.state shouldBe States.PLACED
    }
  }
})
