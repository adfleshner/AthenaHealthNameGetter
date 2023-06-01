import kotlinx.coroutines.*
import org.jsoup.Jsoup
import java.io.FileOutputStream
import java.io.OutputStream

// I wrote A kotlin version of this tutorial that uses coroutines to check the sites in parallel.
// https://www.youtube.com/watch?v=awoEELnQzVg&t=1603s&ab_channel=MakeDataUseful

fun main(args: Array<String>) {
    runBlocking {
        launch {
            val range = (10000.. 13000)
                val sites = range.pmap { clinicId ->
                    runCatching {
                        SiteScraper.get_site_information(clinicId)
                    }.onFailure {
                        println("Clinic_id $clinicId is bad")
                    }.getOrNull()
                }.filterNotNull()
                    .filterNot { it.clincName == "Payment Confirmation"}
                    .associateBy {
                    it.clinicId
                }.toSortedMap()
            println(sites.toList().joinToString(separator = "\n") { it.toString() })
            FileOutputStream("filename.csv").apply { writeCsv(sites.values.toList()) }
        }
    }
}






