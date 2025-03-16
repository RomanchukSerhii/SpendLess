package com.serhiiromanchuk.core.database.mappers

import android.util.Base64
import com.serhiiromanchuk.core.database.entity.TransactionEntity
import com.serhiiromanchuk.core.database.security.Crypto
import com.serhiiromanchuk.core.domain.entity.RepeatType
import com.serhiiromanchuk.core.domain.entity.Transaction

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = id,
        userId = userId,
        title = Crypto.encrypt(title.toByteArray()).toBase64(),
        amount = Crypto.encrypt(amount.toString().toByteArray()).toBase64(),
        repeatType = Crypto.encrypt(repeatType.name.toByteArray()).toBase64(),
        transactionType = Crypto.encrypt(transactionType.toString().toByteArray()).toBase64(),
        note = note?.let { Crypto.encrypt(it.toByteArray()).toBase64() },
        transactionDate = Crypto.encrypt(transactionDate.toString().toByteArray()).toBase64()
    )
}

fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        id = id,
        userId = userId,
        title = Crypto.decrypt(title.fromBase64()).toString(Charsets.UTF_8),
        amount = Crypto.decrypt(amount.fromBase64()).toString(Charsets.UTF_8).toFloat(),
        repeatType = RepeatType.valueOf(Crypto.decrypt(repeatType.fromBase64()).toString(Charsets.UTF_8)),
        transactionType = TransactionConverter.toTransactionType(
            Crypto.decrypt(transactionType.fromBase64()).toString(Charsets.UTF_8)
        ),
        note = note?.let { Crypto.decrypt(it.fromBase64()).toString(Charsets.UTF_8) },
        transactionDate = Crypto.decrypt(transactionDate.fromBase64()).toString(Charsets.UTF_8).toLong()
    )
}

fun List<TransactionEntity>.toDomain(): List<Transaction> = map { it.toDomain() }

private fun ByteArray.toBase64(): String = Base64.encodeToString(this, Base64.DEFAULT)

private fun String.fromBase64(): ByteArray = Base64.decode(this, Base64.DEFAULT)