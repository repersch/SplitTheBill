package br.edu.ifsp.scl.ads.pdm.splitthebill.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bill(
    val id: Int,
    var name: String,
    var value: Double,
    var itens: String
): Parcelable