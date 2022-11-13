package br.edu.ifsp.scl.ads.pdm.splitthebill.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.pdm.splitthebill.R
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.TileBillBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill

class BillAdapter(
    context: Context,
    private val billList: MutableList<Bill>
): ArrayAdapter<Bill>(context, R.layout.tile_bill, billList) {

    private data class TileBillHolder(val nomeTv: TextView, val valorPagoTv: TextView)
    private lateinit var tbb: TileBillBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bill = billList[position]
        var billTileView = convertView

        if (billTileView == null) {
            tbb = TileBillBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            )

            billTileView = tbb.root
            val tileBillHolder = TileBillHolder(tbb.nomeTv, tbb.valorPagoTv)
            billTileView.tag = tileBillHolder
        }

        with (billTileView.tag as TileBillHolder) {
            nomeTv.text = bill.nome
            valorPagoTv.text = bill.valorPago.toString()
        }

        return billTileView
    }
}