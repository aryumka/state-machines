package type

sealed class Actions {
  abstract val message: String
  fun act() { println(message) }
  data class OrderPlaced(override val message: String = "order is placed"): Actions()
  data class PaymentMade(override val message: String = "order is delivered"): Actions()
  data class OrderShipped(override val message: String = "order is shipped"): Actions()
  data class watingForReturn(override val message: String = "waiting for return"): Actions()
  data class orderCanceled(override val message: String = "order is canceled"): Actions()
}
