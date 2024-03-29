import type.Actions
import type.Event
import type.States
import java.util.concurrent.atomic.AtomicReference

class GeneralStateMachine<STATE, EVENT, ACTION, DOMAIN>(
  private val stateDefinition: StateDefinition<STATE, EVENT, ACTION, DOMAIN>,
) {
  companion object {
    //todo 빌더 패턴으로 변경
    fun <STATE, EVENT, ACTION, DOMAIN> create(domain: DOMAIN, init: StateDefinition<STATE, EVENT, ACTION, DOMAIN>.() -> Unit)
    : GeneralStateMachine<STATE, EVENT, ACTION, DOMAIN> =
      GeneralStateMachine(
        StateDefinition<STATE, EVENT, ACTION, DOMAIN>(StateContext(domain)).apply(init),
      )
  }

  fun transit(event: EVENT): Result<STATE, EVENT, ACTION> {
    TODO()
  }

  //state context
  class StateContext<STATE, EVENT, DOMAIN>(domain: DOMAIN) {
    val currentState = AtomicReference<STATE>()
    val domain = domain
  }

  sealed class Result<STATE, EVENT, ACTION> {
    abstract val sourceState: STATE
    abstract val event: EVENT

    class Success<STATE, EVENT, ACTION>(
      override val sourceState: STATE,
      override val event: EVENT,
      val targetState: STATE,
      val action: ACTION,
    ) : Result<STATE, EVENT, ACTION>()

    class Failure<STATE, EVENT, ACTION>(
      override val sourceState: STATE,
      override val event: EVENT,
    ) : Result<STATE, EVENT, ACTION>()
  }


  class StateDefinition<STATE, EVENT, ACTION, DOMAIN>(private val context: StateContext<STATE, EVENT, DOMAIN>) {
    val onEntryListeners = mutableListOf<(STATE, EVENT) -> Unit>()
    val onExitListeners = mutableListOf<(STATE, EVENT) -> Unit>()
    val transitions = mutableListOf<(STATE, EVENT) -> Pair<STATE, ACTION>>()
    val guards = mutableMapOf<(STATE, EVENT) -> Pair<STATE, ACTION>, DOMAIN.() -> Boolean>()

    fun guard(isValid: DOMAIN.() -> Boolean) {
    }
//      context.domain.isValid()

    fun initialState(state: STATE) {
      TODO("Not yet implemented")
    }

    fun <T> state(function: () -> Unit) {
    }

    fun onEntry(function: (EVENT) -> Unit) {
      TODO()
    }

    fun transition(placed: STATE, function: ACTION.() -> Unit): Result<STATE, EVENT, ACTION> {
      TODO()
    }

    //onExit
    fun onExit(function: (EVENT) -> Unit) {
      TODO("Not yet implemented")
    }
    //on

    fun <STATE> on(function: () -> Unit) {
      TODO("Not yet implemented")
    }

    //transition
    fun transition(transition: () -> Unit) {
      TODO("Not yet implemented")
    }
  }

  //state


}


