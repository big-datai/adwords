package adwords.config

import com.datastax.driver.core.{Cluster, ProtocolOptions}
import com.google.api.ads.adwords.lib.client.AdWordsSession
import com.google.api.ads.common.lib.auth.OfflineCredentials
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api
import com.google.api.client.auth.oauth2.Credential
import com.typesafe.config.ConfigFactory
import com.websudos.phantom.connectors.KeySpace
import com.websudos.phantom.dsl._

/**
  * Created by dmitry pavlov on 12/14/15.
  */
trait CassandraCluster {
  def cluster: Cluster
}

trait ConfigCassandraCluster extends CassandraCluster {
  //def system: ActorSystem

  lazy val cluster: Cluster =
    Cluster.builder()
      .addContactPoints(hosts: _*)
      .withCompression(ProtocolOptions.Compression.SNAPPY)
      .withPort(port)
      .build()

  import scala.collection.JavaConversions._
  private val cassandraConfig = config.getConfig("akka-cassandra.main.db.cassandra")
  private val port = cassandraConfig.getInt("port")
  private val hosts = cassandraConfig.getStringList("hosts").toList

  private def config = ConfigFactory.load() //system.settings.config
}

trait AdWordsConfig {

  private[adwords] object OAuth2Credential {
    lazy val session: AdWordsSession = new AdWordsSession.Builder().fromFile.withOAuth2Credential(oAuth2Credential).build
    val oAuth2Credential: Credential = new OfflineCredentials.Builder().forApi(Api.ADWORDS).fromFile.build.generateCredential
  }

}

trait DemoConnector extends ConfigCassandraCluster {
  implicit lazy val keySpace = KeySpace("demo")
  implicit lazy val session: Session = cluster.connect("demo")
}

trait AdwordsConnector extends ConfigCassandraCluster {
  implicit lazy val keySpace = KeySpace("adwords")
  implicit lazy val session: Session = cluster.connect("adwords")
}