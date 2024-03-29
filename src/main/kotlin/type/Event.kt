package type

sealed class Event {
  abstract val message: String
  fun act() { println(message) }
  data class PlaceOrder(override val message: String = "order is placed"): Event()
  data class Pay(override val message: String = "payment is made"): Event()
  data class Ship(override val message: String = "order is shipped"): Event()
  data class Deliver(override val message: String = "order is delivered"): Event()
  data class Cancel(override val message: String = "order is canceled"): Event()
  data class Return(override val message: String = "waiting for return"): Event()
}

//enum class Events {
//  PLACE_ORDER, PAY, SHIP, DELIVER, CANCEL, RETURN
//}
