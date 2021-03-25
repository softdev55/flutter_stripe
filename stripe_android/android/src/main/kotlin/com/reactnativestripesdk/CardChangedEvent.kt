package com.reactnativestripesdk

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap

internal class CardChangedEvent constructor(viewTag: Int, private val cardDetails: MutableMap<String, Any>, private val postalCodeEnabled: Boolean, private val complete: Boolean) {

  private fun getValOr(map: MutableMap<String, Any>, key: String, default: String? = null): String? {
    return if ((map[key] as CharSequence).isNotEmpty()) map[key] as String? else default
  }

  private fun serializeEventData(): WritableMap {
    val eventData = Arguments.createMap()
    eventData.putString("number", cardDetails["number"].toString())
    val expMonth = getValOr(cardDetails, "expiryMonth", null)
    val expYear = getValOr(cardDetails, "expiryYear", null)
    eventData.putString("cvc", cardDetails["cvc"].toString())
    eventData.putInt("expiryMonth", expMonth?.toInt() ?: 0)
    eventData.putInt("expiryYear", expYear?.toInt() ?: 0)
    eventData.putBoolean("complete", complete)

    if (postalCodeEnabled) {
      eventData.putString("postalCode", cardDetails["postalCode"]?.toString())
    }

    return eventData
  }

  companion object {
    const val EVENT_NAME = "onCardChange"
  }

}
