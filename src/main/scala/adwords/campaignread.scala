package adwords

import adwords.CampaignReadActor.FindAndAdd
import adwords.actors.CampaignActor.AddCampaign
import adwords.domain.CampaignCassandra
import akka.actor.{Actor, ActorRef}
import com.datastax.driver.core.Cluster

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by dmitry pavlov on 12/14/15.
  */
object CampaignReadActor {

  case class FindAndAdd(storeId: String, maximum: Int = Int.MaxValue)

}

class CampaignReadActor(cluster: Cluster, campaignActor: ActorRef) extends Actor {

  //  implicit val session = cluster.connect(Keyspaces.adwords)

  override def receive: Receive = {
    case FindAndAdd(storeId, maximum) => findAndAddCampaign(storeId)
  }

  def findAndAddCampaign(storeId: String) = {

    //    println(session.execute("select * from campaigns").all().iterator().next().toString)

    //    Campaigns.select.where(_.storeId eqs storeId).fetch

    CampaignCassandra.c(storeId) map (cas => cas.foreach(ca => println(ca)))

    CampaignCassandra.c(storeId) map {
      _.foreach(campaign => campaignActor ! AddCampaign(campaign))
    }

  }
}
