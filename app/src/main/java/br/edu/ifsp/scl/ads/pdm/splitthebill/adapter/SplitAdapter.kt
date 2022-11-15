package br.edu.ifsp.scl.ads.pdm.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.splitthebill.R
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.TileSplitBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill

class SplitAdapter(
    context: Context,
    private val billList: ArrayList<Bill>
): ArrayAdapter<Bill>(context, R.layout.tile_split, billList) {

    private data class TileSplitHolder(val nameTv: TextView, val splitValueTv: TextView)
    private lateinit var tsb: TileSplitBinding

    private var totalValue: Double = 0.0
    private var splitNumber: Int = 0
    private var splitValue: Double = 0.0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        totalValue = billList.sumOf { bill -> bill.value }
        splitNumber = billList.size
        splitValue = totalValue / splitNumber

        println("Valor total: $totalValue")
        println("Quantidade de pessoas: $splitNumber")
        println("Valor individual: $splitValue")

        val bill = billList[position]
        var splitTileView = convertView

        if (splitTileView == null) {
            tsb = TileSplitBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            splitTileView = tsb.root
            val tileSplitHolder = TileSplitHolder(tsb.nameTv, tsb.valueResultTv)
            splitTileView.tag = tileSplitHolder
        }

        with (splitTileView.tag as TileSplitHolder) {
            nameTv.text = bill.name
            splitValueTv.text = (bill.value - splitValue).toString()
        }

        return splitTileView
    }
}