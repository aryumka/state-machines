package type

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
