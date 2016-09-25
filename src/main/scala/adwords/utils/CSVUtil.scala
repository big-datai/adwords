package adwords.utils

import adwords.domain.CSVLine

/**
 * Created by dmitry pavlov on 12/20/15.
 */
object CSVUtil {

  def csvTo(line: String) = {
    val Array(mpn, brand, bid, suggestion, lbound, ubound, url, adHeadline, ad1Line, adLine2, adDisplayUrl, adFinalUrl, campaignId, campaignBudget) = line.split(",").map(_.trim)

    CSVLine(mpn, brand, bid, suggestion, lbound, ubound, url, adHeadline, ad1Line, adLine2, adDisplayUrl, adFinalUrl, campaignId, campaignBudget)
  }
}
