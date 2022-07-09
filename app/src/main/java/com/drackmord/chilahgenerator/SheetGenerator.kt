package com.drackmord.chilahgenerator

import android.content.Context
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider.getUriForFile
import com.drackmord.chilahgenerator.presentation.model.FormModel
import com.drackmord.chilahgenerator.presentation.model.LeftRight
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File
import java.net.URLConnection
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


val FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")

object SheetGenerator {
    fun generate(formData: FormModel, context: Context) {
        val fileName = "${formData.clientName}-${LocalDateTime.now().format(FORMATTER)}.csv"
        val file = File(context.getExternalFilesDir(null), fileName)
        createCsv2(file, formData)

        val shareUri = getUriForFile(context, "${BuildConfig.APPLICATION_ID}.fileprovider", file)

        ShareCompat.IntentBuilder(context)
            .setStream(shareUri)
            .setType(URLConnection.guessContentTypeFromName(file.name))
            .startChooser()
    }

    private fun createCsv(file: File, formData: FormModel) = csvWriter().open(file) {
        writeRow(listOf("Client:", formData.clientName))
        writeRow(listOf("Weight:", formData.weight))
        writeRow(listOf("Height:", formData.height))
        writeRow(listOf("Fat %:", formData.fatPercent))
        writeRow(listOf("Visceral Fat:", formData.visceralFat))
        writeRow(listOf("Posture:", Constants.postures[formData.posture]))
        writeRow()
        writeRow(listOf("Overhead Squat"))
        writeRow(listOf("Feet Cave in:", formData.feetCaveIn.getDesc()))
        writeRow(listOf("Knee cave in:", formData.squatKneeCaveIn.getDesc()))
        writeRow(listOf("Butwink:", formData.butwink.checkmark()))
        writeRow(listOf("Hyperextension:", formData.hyperextension.checkmark()))
        writeRow(listOf("Forward Lean:", formData.forwardLean.checkmark()))
        writeRow()
        writeRow(listOf("Step Up"))
        writeRow(listOf("Knee cave in:", formData.stepUpKneeCaveIn.getDesc()))
        writeRow(listOf("Hip shift:", formData.hipShift.getDesc()))
        writeRow()
        writeRow(listOf("Hip Hinge:", formData.hipHinge.checkmark()))
        writeRow()
        writeRow(listOf("Wall Slide:", formData.wallSlide.checkmark()))
        writeRow()
        writeRow(listOf("Push"))
        writeRow(listOf("Shrugged:", formData.pushShrugged.getDesc()))
        writeRow(listOf("Retraction:", formData.pushRetraction.checkmark()))
        writeRow(listOf("Hyperextension:", formData.pushHyperextension.checkmark()))
        writeRow()
        writeRow(listOf("Pull"))
        writeRow(listOf("Shrugged:", formData.pullShrugged.getDesc()))
        writeRow(listOf("Retraction:", formData.pullRetraction.checkmark()))
        writeRow(listOf("Hyperextension:", formData.pullHyperextension.checkmark()))
    }

    private fun createCsv2(file: File, formData: FormModel) = csvWriter().open(file) {
        writeRow(listOf("Client:", "", "", "", formData.clientName))
        writeRow(listOf("Weight:", "", "", "", formData.weight))
        writeRow(listOf("Height:", "", "", "", formData.height))
        writeRow(listOf("Fat %:", "", "", "", formData.fatPercent))
        writeRow(listOf("Visceral Fat:", "", "", "", formData.visceralFat))
        writeRow(listOf("Posture:", "", "", "", Constants.postures[formData.posture]))
        writeRow("", "Left", "Both", "Right")
        writeRow(listOf("Overhead Squat"))
        writeRow(listOf("Feet Cave in:") + formData.feetCaveIn.getRow())
        writeRow(listOf("Feet Cave in:") + formData.squatKneeCaveIn.getRow())
        writeRow(listOf("Butwink:") + formData.butwink.getRow())
        writeRow(listOf("Hyperextension:") + formData.hyperextension.getRow())
        writeRow(listOf("Forward Lean:") + formData.forwardLean.getRow())
        writeRow()
        writeRow(listOf("Step Up"))
        writeRow(listOf("Knee cave in:") + formData.stepUpKneeCaveIn.getRow())
        writeRow(listOf("Hip shift:") + formData.hipShift.getRow())
        writeRow()
        writeRow(listOf("Hip Hinge:") + formData.hipHinge.getRow())
        writeRow()
        writeRow(listOf("Wall Slide:") + formData.wallSlide.getRow())
        writeRow()
        writeRow(listOf("Push"))
        writeRow(listOf("Shrugged:") + formData.pushShrugged.getRow())
        writeRow(listOf("Retraction:") + formData.pushRetraction.getRow())
        writeRow(listOf("Hyperextension:") + formData.pushHyperextension.getRow())
        writeRow()
        writeRow(listOf("Pull"))
        writeRow(listOf("Shrugged:") + formData.pullShrugged.getRow())
        writeRow(listOf("Retraction:") + formData.pullRetraction.getRow())
        writeRow(listOf("Hyperextension:") + formData.pullHyperextension.getRow())
    }

    private fun Boolean.checkmark() = if (this) Constants.CHECK else ""

    private fun Boolean.getRow() = listOf("", checkmark(), "")

    private fun LeftRight.getRow() = listOf(left.checkmark(), "", right.checkmark())

    private fun LeftRight.getDesc() = StringBuilder().apply {
        if (left) {
            append("Left")
            if (right) append(",")
        }
        if (right) append("Right")
    }.toString()

    private object Constants {
        const val CHECK = "\u2713"
        val postures = listOf(
            "Ideal alignment",
            "Kyphotic-lordotic posture",
            "Flat-back posture",
            "Sway-back posture"
        )
    }
}