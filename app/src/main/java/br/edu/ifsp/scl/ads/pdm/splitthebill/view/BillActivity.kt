package br.edu.ifsp.scl.ads.pdm.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.ActivityBillBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.EXTRA_BILL
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.VIEW_BILL
import kotlin.random.Random

class BillActivity: AppCompatActivity() {

    private val abb: ActivityBillBinding by lazy {
        ActivityBillBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(abb.root)

        // preenche os campos na tela se a conta não for nula
        val receivedBill = intent.getParcelableExtra<Bill>(EXTRA_BILL)
        receivedBill?.let { _receivedBill ->
            with(abb) {
                nameEt.setText(_receivedBill.name)
                valueEt.setText(_receivedBill.value.toString())
                itensEt.setText(_receivedBill.itens)
            }
        }

        // informa se permite edição ou não
        val viewBill = intent.getBooleanExtra(VIEW_BILL, false)
        if (viewBill) {
            abb.nameEt.isEnabled = false
            abb.valueEt.isEnabled = false
            abb.itensEt.isEnabled = false
            abb.saveBt.visibility = View.GONE
        }

        abb.saveBt.setOnClickListener {
            val bill = Bill(
                id = receivedBill?.id?: Random(System.currentTimeMillis()).nextInt(),
                name = abb.nameEt.text.toString(),
                value = abb.valueEt.text.toString().toDouble(),
                itens = abb.itensEt.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_BILL, bill)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }

}