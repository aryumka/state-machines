// current state -> event, next state -> action
class MyStateMachine1 {
  class StateMachine (
    private val transition: Map<States, List<Map<Events, Pair<States, Actions>>>>,
  ){
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
    READY, PROCESSING, COMPLETE
  }

  enum class Events {
    COIN, PUSH
  }

  enum class Actions(val message: String) {
    ALLOW("allowed"), DENY("denied"), REFUND("refunded"), IGNORE("ignored");
  }
}

fun main() {
  // Define the state machine
  val transition = mapOf(
    MyStateMachine1.States.READY to listOf(
      mapOf(MyStateMachine1.Events.COIN to Pair(MyStateMachine1.States.PROCESSING, MyStateMachine1.Actions.ALLOW)),
      mapOf(MyStateMachine1.Events.PUSH to Pair(MyStateMachine1.States.READY, MyStateMachine1.Actions.IGNORE)),
    ),
    MyStateMachine1.States.PROCESSING to listOf(
      mapOf(MyStateMachine1.Events.COIN to Pair(MyStateMachine1.States.PROCESSING, MyStateMachine1.Actions.REFUND)),
      mapOf(MyStateMachine1.Events.PUSH to Pair(MyStateMachine1.States.PROCESSING, MyStateMachine1.Actions.REFUND)),
    ),
    MyStateMachine1.States.COMPLETE to listOf(
      mapOf(MyStateMachine1.Events.COIN to Pair(MyStateMachine1.States.COMPLETE, MyStateMachine1.Actions.IGNORE)),
      mapOf(MyStateMachine1.Events.PUSH to Pair(MyStateMachine1.States.READY, MyStateMachine1.Actions.IGNORE)),
    ),
  )

  val stateMachine = MyStateMachine1.StateMachine(transition)

  // Test the state machine
  stateMachine.setState(MyStateMachine1.States.READY)
}
