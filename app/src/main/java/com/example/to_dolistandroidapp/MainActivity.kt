package com.example.to_dolistandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var itemList = ArrayList<String>()
        var adapterItem = ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, itemList)
        val addButton : Button = findViewById(R.id.add)
        val addEditText : EditText = findViewById(R.id.editText)
        val listView : ListView = findViewById(R.id.listView)
        val deleteButton : Button = findViewById(R.id.delete)
        val clearButton : Button = findViewById(R.id.clear)
        listView.adapter = adapterItem

        addButton.setOnClickListener {
            val text = addEditText.text.toString()
            itemList.add(text)
            adapterItem.notifyDataSetChanged()
            addEditText.text.clear()
        }
        deleteButton.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    itemList.removeAt(item) // Use removeAt() para remover o item pelo índice
                }
                item--
            }
            position.clear()
            adapterItem.notifyDataSetChanged()
        }
        clearButton.setOnClickListener {
            itemList.clear()
            adapterItem.notifyDataSetChanged()
        }
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "Você selecionou o item -> "+ itemList.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}
