package com.drackmord.chilahgenerator

import android.content.Context
import android.util.Log
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider.getUriForFile
import com.drackmord.chilahgenerator.presentation.model.FormModel
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URLConnection
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")

object ExcelGenerator {
    fun generate(formData: FormModel, context: Context) {
        val fileName = "${formData.clientName}-${LocalDateTime.now().format(FORMATTER)}.xlsx"

        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("Sheet1")
        val row = sheet.createRow(0)
        val cell = row.createCell(0)
        cell.setCellValue("First Cell")

        val file = File(context.getExternalFilesDir(null), fileName)
        var fileOutputStream: FileOutputStream? = null

        try {
            fileOutputStream = FileOutputStream(file)
            workbook.write(fileOutputStream)
            Log.e("MATTEO", "Writing file$file")
        } catch (e: IOException) {
            Log.e("MATTEO", "Error writing Exception: ", e)
        } catch (e: Exception) {
            Log.e("MATTEO", "Failed to save file due to Exception: ", e)
        } finally {
            try {
                fileOutputStream?.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        val shareUri = getUriForFile(context, "${BuildConfig.APPLICATION_ID}.fileprovider", file)

        ShareCompat.IntentBuilder(context)
            .setStream(shareUri)
            .setType(URLConnection.guessContentTypeFromName(file.name))
            .startChooser()
    }
}