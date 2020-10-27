package com.example.architectureexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*


class AddEditNoteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "com.example.architectureexample.EXTRA_ID"
        const val EXTRA_TITLE = "com.example.architectureexample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.architectureexample.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.architectureexample.EXTRA_PRIORITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        val intent = intent

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            edit_text_title.setText(intent.getStringExtra(EXTRA_TITLE))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else title = "Add Note"

    }

    private fun saveNote() {

        val title = edit_text_title.text.toString()
        val description = edit_text_description.text.toString()
        val priority = number_picker_priority.value

        val data = Intent()

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_LONG).show()
            setResult(Activity.RESULT_CANCELED, data)
        } else {
            data.putExtra(EXTRA_TITLE, title)
            data.putExtra(EXTRA_DESCRIPTION, description)
            data.putExtra(EXTRA_PRIORITY, priority)

            val id = intent.getIntExtra(EXTRA_ID, -1)
            if (id != -1) {
                data.putExtra(EXTRA_ID, id)
            }

            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

