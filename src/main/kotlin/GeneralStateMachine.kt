class GeneralStateMachine<STATE, EVENT, ACTION> {
  companion object {
    fun <STATE, EVENT, ACTION> create(init: () -> Unit): Any {
      TODO("Not yet implemented")
    }
  }

  //state context
  class StateContext<STATE, EVENT, ACTION> {
    //state definition
    class StateDefinition<STATE, EVENT, ACTION> {
      //transition
      fun transition(transition: () -> Unit) {
        TODO("Not yet implemented")
      }
    }

    //state
    fun state(state: STATE, init: StateDefinition<STATE, EVENT, ACTION>.() -> Unit) {
      TODO("Not yet implemented")
    }
  }
}


