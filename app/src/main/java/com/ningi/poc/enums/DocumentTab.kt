package com.ningi.poc.enums

import androidx.annotation.StringRes
import com.ningi.poc.R

enum class DocumentTab(
    @StringRes val text: Int,
) {

    MyDocuments(text = R.string.my_documents),
    ToSign(text = R.string.to_sign)
}