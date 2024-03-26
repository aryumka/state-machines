// current state -> event, next state -> action
// SOURCE 상태 - 이벤트 - TARGET 상태 - 액션
class MyStateMachine1 {
  class StateMachine (
    private val transition: Map<States, List<Map<Events, Pair<States, Actions>>>>,
  ) {
    private var state: States = States.READY

    fun setState(state: States) {
      this.state = state
    }

    fun transit(event: Events): States {
      val action =  transition[state]?.find { it.containsKey(event) }?.get(event)?.second?.message ?: Actions.IGNORE.message
      println("Event: $event, State: $state, Action: $action")

      return transition[state]?.find { it.containsKey(event) }?.get(event)?.first ?: state
    }
  }

  enum class States {
    READY, PLACED, PAID, SHIPPED, DELIVERED, CANCELLED, PENDING, RETURNED
  }

  enum class Events {
    PLACE_ORDER, PAY, SHIP, DELIVER, CANCEL, RETURN
  }


  enum class Actions(val message: String) {
    ALLOW("allowed"), DENY("denied"), REFUND("refunded"), IGNORE("ignored");
  }
}

fun main() {
}
