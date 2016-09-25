package com.deepricer

/**
  * Created by dmitry pavlov on 12/14/15.
  */
package object adwords {

  private[adwords] object Keyspaces {
    val adwords = "adwords"
  }

  private[adwords] object ColumnFamilies {
    val campaigns = "campaigns"
    val adGroups = "adgroups"
    val ads = "ads"
  }

}
