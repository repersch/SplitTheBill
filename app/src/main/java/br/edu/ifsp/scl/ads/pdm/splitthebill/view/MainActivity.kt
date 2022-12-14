package br.edu.ifsp.scl.ads.pdm.splitthebill.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.ads.pdm.splitthebill.R
import br.edu.ifsp.scl.ads.pdm.splitthebill.adapter.BillAdapter
import br.edu.ifsp.scl.ads.pdm.splitthebill.adapter.SplitAdapter
import br.edu.ifsp.scl.ads.pdm.splitthebill.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Bill
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.EXTRA_BILL
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.LIST
import br.edu.ifsp.scl.ads.pdm.splitthebill.model.Constant.VIEW_BILL

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val billList: MutableList<Bill> = mutableListOf()
    private lateinit var billAdapter: BillAdapter
    private lateinit var carl: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillBillList()
        billAdapter = BillAdapter(this, billList)
        amb.billLv.adapter = billAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val bill = result.data?.getParcelableExtra<Bill>(EXTRA_BILL)
                bill?.let { _bill ->
                    if (billList.any { it.id == _bill.id }) {
                        val position = billList.indexOfFirst { it.id == _bill.id }
                        billList[position] = _bill
                    } else {
                        billList.add(_bill)
                    }
                    billAdapter.notifyDataSetChanged()
                }
            }
        }

        // indica onde o menu de contexto vai aparecer
        registerForContextMenu(amb.billLv)

        amb.billLv.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val bill = billList[position]
                val billIntent = Intent(this@MainActivity, BillActivity::class.java)
                billIntent.putExtra(EXTRA_BILL, bill)
                billIntent.putExtra(VIEW_BILL, true)
                startActivity(billIntent)
            }
        }
    }

    // menu de cria????o de novos itens
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // abre a tela de bill quando clica no bot??o add ou a tela de split no bot??o $
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addContaMi -> {
                carl.launch(Intent(this, BillActivity::class.java))
                true
            }
            R.id.splitBillMi -> {
                // envia a billList para a tela que faz a divis??o dos valores
                val splitIntent = Intent(this, SplitActivity::class.java)
                splitIntent.putParcelableArrayListExtra(LIST, ArrayList(billList))
                carl.launch(splitIntent)
                true
            }
            else -> {
                false
            }
        }
    }

    // menu de contexto (manter item pressionado) - editar e apagar
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterContextMenuInfo).position
        return when(item.itemId) {
            R.id.removeBillMi -> {
                billList.removeAt(position)
                billAdapter.notifyDataSetChanged()
                true
            }
            R.id.editBillMi -> {
                val bill = billList[position]
                val billIntent = Intent(this, BillActivity::class.java)
                billIntent.putExtra(EXTRA_BILL, bill)
                billIntent.putExtra(VIEW_BILL, false)
                carl.launch(billIntent)
                true
            }
            else -> { false }
        }
    }

    private fun fillBillList() {
        billList.add(
            Bill(
                id = 1,
                name = "Berenice",
                value = 10.0,
                itens = "Sach??"
            )
        )
        billList.add(
            Bill(
                id = 2,
                name = "Jorge",
                value = 20.0,
                itens = "Peixe"
            )
        )
        billList.add(
            Bill(
                id = 3,
                name = "Frederico",
                value = 30.0,
                itens = "Ra????o"
            )
        )
        billList.add(
            Bill(
                id = 4,
                name = "Kali",
                value = 20.0,
                itens = "Frango"
            )
        )
        billList.add(
            Bill(
                id = 5,
                name = "Romeu",
                value = 35.21,
                itens = "Torta"
            )
        )
        billList.add(
            Bill(
                id = 6,
                name = "Floki",
                value = 0.0,
                itens = ""
            )
        )
        billList.add(
            Bill(
                id = 7,
                name = "Linda",
                value = 13.55,
                itens = "Macarr??o"
            )
        )
        billList.add(
            Bill(
                id = 8,
                name = "Ramon",
                value = 5.31,
                itens = "Copos"
            )
        )
    }
}

