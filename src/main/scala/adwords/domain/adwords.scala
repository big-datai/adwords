package adwords.domain

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._

/**
  * Created by dmitry pavlov on 12/15/15.
  */
case class AdGroupCassandra(
                             campaignId: Long,
                             name: String
                           )

sealed class AdGroups extends CassandraTable[AdGroups, AdGroupCassandra] {

  override def fromRow(row: Row): AdGroupCassandra = {
    AdGroupCassandra(
      campaignId(row),
      name(row)
    )
  }

  object campaignId extends LongColumn(this) with PrimaryKey[Long] {
    override def name = "campaign_id"
  }

  object name extends StringColumn(this) with PartitionKey[String]

}

object AdGroups extends AdGroups

case class AdTextCassandra(
                            groupId: Long,
                            headline: String,
                            desc1: Option[String],
                            desc2: Option[String],
                            displayUrl: String,
                            finalUrl: String
                          )

sealed class AdTexts extends CassandraTable[AdTexts, AdTextCassandra] {

  override def fromRow(row: Row): AdTextCassandra = {
    AdTextCassandra(
      groupId(row),
      headline(row),
      desc1(row),
      desc2(row),
      displayUrl(row),
      finalUrl(row)
    )
  }

  object groupId extends LongColumn(this) with PrimaryKey[Long] {
    override def name = "group_id"
  }

  object headline extends StringColumn(this) with PartitionKey[String]

  object desc1 extends OptionalStringColumn(this)

  object desc2 extends OptionalStringColumn(this)

  object displayUrl extends StringColumn(this)

  object finalUrl extends StringColumn(this)

}

object AdTexts extends AdTexts