package br.edu.ifsp.scl.ads.pdm.splitthebill.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.ActivitySplitBinding

class SplitActivity:AppCompatActivity() {

    private val asb: ActivitySplitBinding by lazy {
        ActivitySplitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(asb.root)
    }
}