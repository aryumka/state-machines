import io.kotest.core.spec.style.FunSpec
import org.junit.jupiter.api.Assertions.*
import type.Actions
import type.Events
import type.States

sealed interface State {
  fun onEntry(function: () -> Unit)
  object READY : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object PLACED : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object PAID : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object SHIPPED : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object DELIVERED : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object PENDING : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
  object CANCELLED : State {
    override fun onEntry(function: () -> Unit) {
      TODO("Not yet implemented")
    }
  }
}

class GeneralStateMachineTest: FunSpec ({
  context("creating a GeneralStateMachine") {
    val transition = createTransition()
    val newMachine = GeneralStateMachine.create<States, Events, Actions> {
      //초기 상태
      initialState(State.READY)

      //state definition - 상태 정의
      state<State.READY> {
        onEntry {

        }

        on<Events.PLACE_ORDER> {
          //TransitionDefintionBuilder에 인자로 받은 람다 적용. transition definition - 상태 전이 정의: source state -> event -> target state, action
          transition {
            States.PLACED

            //guard

          }
        }

        onExit {

        }
      }


    }

    test("initial state is READY") {
    }
  }
})

fun initialState(state: State) {
  TODO("Not yet implemented")
}

fun onExit(function: () -> Unit) {
  TODO("Not yet implemented")
}

fun <STATE> on(function: () -> Unit) {
  TODO("Not yet implemented")
}


fun transition(function: () -> Unit) {
  TODO("Not yet implemented")
}

fun transition(placed: States, orderPlaced: Actions.OrderPlaced, function: () -> Unit) {
  TODO("Not yet implemented")
}
