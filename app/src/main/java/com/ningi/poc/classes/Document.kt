package com.ningi.poc.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Document(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val fileType: String,
    val needsSigning: Boolean,
) : Parcelable