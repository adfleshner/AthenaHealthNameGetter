import java.io.OutputStream

fun OutputStream.writeCsv(siteInformationList: List<SiteInformation>) {
    val writer = bufferedWriter()
    writer.write(""""clinicId", "clinicName"""")
    writer.newLine()
    siteInformationList.forEach {
        writer.write("${it.clinicId}, ${it.clincName}")
        writer.newLine()
    }
    writer.flush()
}