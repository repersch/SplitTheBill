package br.edu.ifsp.scl.ads.pdm.splitthebill.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bill(
    var nome: String,
    var valorPago: Double,
    var itensComprados: String
): Parcelable