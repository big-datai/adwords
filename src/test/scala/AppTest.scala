import ads.utils.Utils
import com.google.api.ads.adwords.axis.factory.AdWordsServices
import com.google.api.ads.adwords.axis.utils.v201506.SelectorBuilder
import com.google.api.ads.adwords.axis.v201506.cm.{AdGroupBidModifierServiceInterface, Paging, Selector}
import com.google.api.ads.adwords.axis.v201509.cm.CampaignServiceInterface
import org.junit._
import org.junit.runner.RunWith
import org.scalatest.Assertions
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
@Test
class AppTest extends Assertions {
  @Test
  def updateKeyTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      val campaign = Utils.findCampaign("dewalt")
      println(campaign.getName + " id : " + campaign.getId)

      Utils.updateKeywordBid(adWordsServices, session, 23716284536L, 142713060296L, 10000L)
      //updateCampaignBids(adWordsServices, session, campaign.getId)
      assert(true)
    } catch {
      case e => assert(false)
    }
  }


  def updateCampaignTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      val campaign = Utils.findCampaign("dewalt")
      println(campaign.getName + " id : " + campaign.getId)

      Utils.updateKeywordBid(adWordsServices, session, 23716284536L, 142713060296L, 10000L)
      Utils.updateCampaignBids(adWordsServices, session, campaign.getId)
      assert(true)
    } catch {
      case e => assert(false)
    }
  }

  def updateCampaignBidTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      val campaign = Utils.findCampaign("dewalt")
      println(campaign.getName + " id : " + campaign.getId)

      Utils.updateKeywordBid(adWordsServices, session, 23716284536L, 142713060296L, 10000L)
      Utils.updateCampaignBids(adWordsServices, session, campaign.getId)
      assert(!"".equals(Utils.showDataOnCampaign(campaign.getId.toString())))

    } catch {
      case e => assert(false)
    }
  }

  def getDataTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      val campaign = Utils.findCampaign("dewalt")
      println(campaign.getName + " id : " + campaign.getId)

      Utils.updateKeywordBid(adWordsServices, session, 23716284536L, 142713060296L, 10000L)
      Utils.updateCampaignBids(adWordsServices, session, campaign.getId)
      assert(!"".equals(Utils.showDataOnCampaign(campaign.getId.toString())))

    } catch {
      case e => assert(false)
    }
  }

  def iterateDataTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      // Get the CampaignService.
      val campaignService = adWordsServices.get(session, classOf[CampaignServiceInterface]);

      val campaign = Utils.findCampaign("dewalt")
      println(campaign.getName + " id : " + campaign.getId)

      Utils.updateKeywordBid(adWordsServices, session, 23716284536L, 142713060296L, 10000L)
      Utils.updateCampaignBids(adWordsServices, session, campaign.getId)

      Utils.iterateGroups(adWordsServices, session, 352064696L)
      assert(true)
    } catch {
      case e => assert(false)
    }
  }

  def reportsServiceTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices
      // Get the CampaignService.
      val campaignService = adWordsServices.get(session, classOf[CampaignServiceInterface]);

      val campaign = Utils.findCampaign("dewalt")

      val bidModifierService: AdGroupBidModifierServiceInterface = adWordsServices.get(session, classOf[AdGroupBidModifierServiceInterface])
      val builder = new SelectorBuilder()
      val selector: Selector = builder.fields("CampaignName", "CampaignId", "AdGroupId", "Id", "BidModifier", "KeywordText", "FirstPageCpc")
        .contains("CampaignName", "itdeviceonline")
        //      .orderAscBy(CampaignField.Name)
        .offset(0)
        .limit(100)
        .build()
      selector.setPaging(new Paging(0, 10))
      assert(true)
    } catch {
      case e => assert(false)
    }
  }

  def estimateTraficÒTest(args: Array[String]): Unit = {
    try {
      val session = Utils.getSession
      val adWordsServices: AdWordsServices = new AdWordsServices


      //createAdGroup("mpn", 352064696, adWordsServices, session)
      val adGroupId = 23232312296L
      val criterionId = 23232312296L
      Utils.estimateTrafic(adGroupId, criterionId, "", adWordsServices, session)
      assert(true)
    } catch {
      case e => assert(false)
    }
  }


}


