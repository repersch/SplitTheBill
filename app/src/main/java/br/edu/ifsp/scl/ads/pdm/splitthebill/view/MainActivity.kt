package br.edu.ifsp.scl.ads.pdm.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import br.edu.ifsp.scl.ads.pdm.splitthebill.R
import br.edu.ifsp.scl.ads.pdm.splitthebill.adapter.BillAdapter
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val billList: MutableList<Bill> = mutableListOf()
    private lateinit var billAdapter: BillAdapter
    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillBillList()
        billAdapter = BillAdapter(this, billList)
        amb.billLv.adapter = billAdapter
    }

    private fun fillBillList() {
        billList.add(
            Bill(
                nome = "Berenice",
                valorPago = 14.55,
                itensComprados = "Sachê"
            )
        )
        billList.add(
            Bill(
                nome = "Jorge",
                valorPago = 37.20,
                itensComprados = "Peixe"
            )
        )
        billList.add(
            Bill(
                nome = "Frederico",
                valorPago = 54.0,
                itensComprados = "Ração"
            )
        )
        billList.add(
            Bill(
                nome = "Kali",
                valorPago = 13.47,
                itensComprados = "Frango"
            )
        )
    }
}