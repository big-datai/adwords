import ads.utils.Utils
import com.google.api.ads.adwords.axis.factory.AdWordsServices
import com.google.api.ads.adwords.axis.v201509.cm.CampaignServiceInterface
import org.junit._
import org.junit.runner.RunWith
import org.scalatest.Assertions
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
@Test
class AppTest extends Assertions {
    @Test
    def campaignServiceTest() {
        try {
            val session = Utils.getSession
            val adWordsServices: AdWordsServices = new AdWordsServices
            // Get the CampaignService.
            val campaignService = adWordsServices.get(session, classOf[CampaignServiceInterface])

            val campaign = Utils.findCampaign("dewalt")
        } catch {
            case e =>
                assert(false)
        }
    }
    @Test
    def adwordsServiceTest() {
        try {
            val session = Utils.getSession
            val adWordsServices: AdWordsServices = new AdWordsServices
        } catch {
            case e =>
                assert(false)
        }
    }
    @Test
    def sessionTest() {
        try {
            val session = Utils.getSession
        } catch {
            case e =>
                assert(false)
        }
    }
    @Test
    def campaignTest() {
        try {
            val session = Utils.getSession
            val adWordsServices: AdWordsServices = new AdWordsServices
            // Get the CampaignService.
            val campaignService = adWordsServices.get(session, classOf[CampaignServiceInterface])

            val campaign = Utils.findCampaign("dewalt")
            println(campaign.getName + " id : " + campaign.getId)

            //updateCampaignBids(adWordsServices, session, campaign.getId)
        } catch {
            case e =>
            assert(false)
        }
    }

}


