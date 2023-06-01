import org.jsoup.Jsoup


object SiteScraper {

    fun get_site_information(clinic_id:Int) : SiteInformation{
        val url = "https://${clinic_id}.portal.athenahealth.com"
        val connection = Jsoup.connect(url).execute()
        val httpCode = connection.statusCode()
        val siteDoc = connection.parse()
        val title = siteDoc.title()
        val h1s = siteDoc.select("h1").last()?.text()?:"Unknown"
        return SiteInformation(clinic_id, httpCode, title, h1s)
    }

}