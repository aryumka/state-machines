import io.kotest.core.spec.style.FunSpec
import type.*

data class Order(val id: Int, val amount: Double)

class GeneralStateMachineTest: FunSpec ({
  val order = Order(1, 100.0)

  context("creating a GeneralStateMachine") {
    val orderMachine = GeneralStateMachine.create<State, Event, Actions, Order>(
      order
    ) {
      initialState(State.READY)

      //state definition - 상태 정의
      state<State.READY> {
        onEntry {
          println("READY ENTER")
        }

        on<Event.PlaceOrder> {
          transition(State.PLACED) {
            guard {
              order.amount > 0
            }
            Actions.OrderPlaced().act()
          }
        }

        onExit {
          println("READY EXIT")
        }
      }

      state<State.PLACED> {
        onEntry {
          println("PLACED ENTER")
        }

        on<Event.Pay> {
          transition(State.PAID) {
            Actions.PaymentMade().act()
          }
        }

        onExit {
          println("PLACED EXIT")
        }
      }
    }

    test("initial state is READY") {
    }

    test("placing an order") {
      orderMachine.transit(Event.PlaceOrder())
    }
  }
})

