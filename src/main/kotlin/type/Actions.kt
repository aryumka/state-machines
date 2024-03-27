package type

import java.util.logging.Logger

sealed class Actions(private val logger: Logger) {
  abstract val message: String
  fun act() { logger.info(message) }
  data class OrderPlaced(override val message: String = "order is placed", val logger: Logger): Actions(logger)
  data class PaymentMade(override val message: String = "payment is made", val logger: Logger): Actions(logger)
  data class OrderShipped(override val message: String = "order is shipped", val logger: Logger): Actions(logger)
  data class OrderDelivered(override val message: String = "order is delivered", val logger: Logger): Actions(logger)
  data class watingForReturn(override val message: String = "waiting for return", val logger: Logger): Actions(logger)
  data class orderCanceled(override val message: String = "order is canceled", val logger: Logger): Actions(logger)
}
