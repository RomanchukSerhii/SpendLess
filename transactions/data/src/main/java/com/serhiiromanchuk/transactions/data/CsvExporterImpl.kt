package com.serhiiromanchuk.transactions.data

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.serhiiromanchuk.core.domain.entity.Expense
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.core.domain.entity.Transaction
import com.serhiiromanchuk.core.domain.entity.TransactionType
import com.serhiiromanchuk.core.domain.util.InstantFormatter
import com.serhiiromanchuk.transactions.domain.CsvExporter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CsvExporterImpl(
    private val context: Context,
) : CsvExporter {
    override fun exportToCsv(fileName: String, transactions: List<Transaction>) {
        val csvData = convertToCsv(transactions)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                put(MediaStore.Downloads.MIME_TYPE, "text/csv")
            }

            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(csvData.toByteArray())
                }
            }
        } else {
            val downloadsDirectory = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
            try {
                FileOutputStream(downloadsDirectory).use { outputStream ->
                    outputStream.write(csvData.toByteArray())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun convertToCsv(transactions: List<Transaction>): String {
        return buildString {
            append("ID,Title,Amount,Type,Category,Repeat Type,Note,Transaction Date\n")

            transactions.forEach { transaction ->
                append(
                    "${transaction.id}," +
                            "\"${transaction.title}\"," +
                            "${transaction.amount}," +
                            "${getTransactionType(transaction.transactionType)}," +
                            "${getTransactionCategory(transaction.transactionType)}," +
                            "${getRepeatType(transaction.repeatType)}," +
                            "\"${transaction.note ?: ""}\"," +
                            "${InstantFormatter.formatDateTimeString(transaction.transactionDate)}\n"
                )
            }
        }
    }

    private fun getTransactionType(type: TransactionType): String {
        return if (type is Expense) "Expense" else "Income"
    }

    private fun getTransactionCategory(type: TransactionType): String {
        return if (type is Expense) {
            type.name
                .lowercase()
                .replaceFirstChar { it.uppercase() }
        } else "Income"
    }

    private fun getRepeatType(type: RepeatType): String {
        return type.name
            .lowercase()
            .replaceFirstChar { it.uppercase() }
    }
}