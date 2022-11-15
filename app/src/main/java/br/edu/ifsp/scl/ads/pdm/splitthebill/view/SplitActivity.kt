package br.edu.ifsp.scl.ads.pdm.splitthebill.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.splitthebill.adapter.SplitAdapter
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.ActivitySplitBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.LIST

class SplitActivity:AppCompatActivity() {

    private val asb: ActivitySplitBinding by lazy {
        ActivitySplitBinding.inflate(layoutInflater)
    }
    private lateinit var splitAdapter: SplitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)

        val receivedList = intent.getParcelableArrayListExtra<Bill>(LIST)
        splitAdapter = SplitAdapter(this, receivedList as ArrayList<Bill>)
        asb.splitLv.adapter = splitAdapter
    }
}