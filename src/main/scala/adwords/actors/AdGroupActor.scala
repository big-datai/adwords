package adwords.actors

import adwords.actors.AdGroupActor.AddAdGroup
import adwords.actors.AdKeywordActor._
import adwords.actors.AdTextActor.AddAdText
import adwords.domain.CSVLine
import akka.actor.{Actor, ActorLogging, ActorRef}
import com.google.api.ads.adwords.axis.factory.AdWordsServices
import com.google.api.ads.adwords.axis.v201509.cm._
import com.google.api.ads.adwords.lib.client.AdWordsSession

/**
  * Created by dmitry pavlov on 12/15/15.
  */

object AdGroupActor {

  case class AddAdGroup(csvLine: CSVLine)

  //  case class SetAdGroup(adGroupCassandra: AdGroupCassandra)
}

class AdGroupActor(session: AdWordsSession, adTextActor: ActorRef, key: ActorRef) extends Actor with ActorLogging {

  override def receive: Receive = {
    case AddAdGroup(csvLine: CSVLine) => addGroup(csvLine, Operator.ADD)
    //    case SetAdGroup(adGroupCassandra: AdGroupCassandra) => addCampaign(adGroupCassandra, Operator.SET))))))
  }

  def addGroup(csvLine: CSVLine, operator: Operator) = {
    val adWordsServices: AdWordsServices = new AdWordsServices
    val adGroupService: AdGroupServiceInterface =
      adWordsServices.get(session, classOf[AdGroupServiceInterface])

    // Create ad group.
    val adGroup: AdGroup = new AdGroup
    adGroup.setName(csvLine.mpn)
    adGroup.setStatus(AdGroupStatus.ENABLED)
    adGroup.setCampaignId(csvLine.campaignId.toLong)
    val operation: AdGroupOperation = new AdGroupOperation
    operation.setOperand(adGroup)
    operation.setOperator(operator)

    // Add ad groups.
    val result: AdGroupReturnValue = adGroupService.mutate(Array[AdGroupOperation](operation))
    var i: Long = 1L
    result.getValue foreach { group =>
      adTextActor ! AddAdText(csvLine, group.getId)
      key ! AddAdKeyword(csvLine, group.getId)
    }

  }
}
