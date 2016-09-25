package adwords.actors

import adwords.actors.AdKeywordActor.AddAdKeyword
import adwords.domain.CSVLine
import akka.actor.{Actor, ActorLogging}
import com.google.api.ads.adwords.axis.factory.AdWordsServices
import com.google.api.ads.adwords.axis.v201509.cm._
import com.google.api.ads.adwords.lib.client.AdWordsSession

/**
  * Created by dmitry pavlov on 12/16/15.
  */

object AdKeywordActor {

  case class AddAdKeyword(csvLine: CSVLine, groupId: Long)

}

class AdKeywordActor(session: AdWordsSession) extends Actor with ActorLogging {

  override def receive: Receive = {
    case AddAdKeyword(csvLine: CSVLine, groupId: Long) => addKeyword(csvLine, groupId)
  }

  def addKeyword(csvLine: CSVLine, groupId: Long) = {
    try {
      val adWordsServices: AdWordsServices = new AdWordsServices
      val adGroupCriterionService: AdGroupCriterionServiceInterface =
        adWordsServices.get(session, classOf[AdGroupCriterionServiceInterface])

      // Create keyword
      val keyword = new Keyword
      keyword.setText(csvLine.mpn)
      keyword.setMatchType(KeywordMatchType.PHRASE)

      // Create biddable ad group criterion
      val keywordBiddableAdGroupCriterion: BiddableAdGroupCriterion = new BiddableAdGroupCriterion
      keywordBiddableAdGroupCriterion.setAdGroupId(groupId)
      keywordBiddableAdGroupCriterion.setCriterion(keyword)
      //val encodedFinalUrl = String.format(csvLine.adFinalUrl, URLEncoder.encode(keyword.getText, Charsets.UTF_8.name()))
      //keywordBiddableAdGroupCriterion.setFinalUrls(new UrlList(Array(encodedFinalUrl)))
      keywordBiddableAdGroupCriterion.setUserStatus(UserStatus.PAUSED)
      //keywordBiddableAdGroupCriterion.setFinalUrls(new UrlList(Array[String](csvLine.adFinalUrl)))

      val biddingStrategyConfiguration: BiddingStrategyConfiguration = new BiddingStrategyConfiguration
      val cpcBid: CpcBid = new CpcBid
      cpcBid.setBid(new Money(null, csvLine.bid.toLong))
      biddingStrategyConfiguration.setBids(Array[Bids] {
        cpcBid
      })
      keywordBiddableAdGroupCriterion.setBiddingStrategyConfiguration(biddingStrategyConfiguration)

      val keywordAdGroupAdOperation = new AdGroupCriterionOperation()
      keywordAdGroupAdOperation.setOperand(keywordBiddableAdGroupCriterion)
      keywordAdGroupAdOperation.setOperator(Operator.ADD)

      // Add ads.
      val result = adGroupCriterionService.mutate(Array[AdGroupCriterionOperation](keywordAdGroupAdOperation))
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}