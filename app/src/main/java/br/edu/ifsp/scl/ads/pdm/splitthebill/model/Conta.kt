package br.edu.ifsp.scl.ads.pdm.splitthebill.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Conta(
    var nome: String,
    var valorPago: Double,
    var itensComprados: ArrayList<String>
): Parcelable