package ramizbek.aliyev.typedialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_dialog.*
import kotlinx.android.synthetic.main.item_dialog.view.*
import ramizbek.aliyev.typedialogs.databinding.ActivityMainBinding
import ramizbek.aliyev.typedialogs.databinding.ItemDialogBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Alert Dialog
        binding.btnAlert.setOnClickListener {
            Toast.makeText(this, "Alert Dialog", Toast.LENGTH_SHORT).show()
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Title")
            alertDialog.setMessage("Massage")
            alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "N0", Toast.LENGTH_SHORT).show()
                }
            })
            alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
                }
            })
            alertDialog.setNeutralButton("cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    Toast.makeText(this@MainActivity, "Cancel", Toast.LENGTH_SHORT).show()
                }
            })
            alertDialog.show()
        }


        //Custom dialog
        binding.btnCustom.setOnClickListener {
            Toast.makeText(this, "Custom dialog", Toast.LENGTH_SHORT).show()
            val view = View.inflate(this@MainActivity, R.layout.item_dialog, null)

            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)

            view.btn_okay.setOnClickListener {
                Toast.makeText(this, "Okay", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            view.btn_cancel.setOnClickListener {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                dialog.dismiss()

            }
        }

        // Fragment Dialog
        binding.btnFragment.setOnClickListener {
            Toast.makeText(this, "Fragment Dialog", Toast.LENGTH_SHORT).show()
            val dialog = MyDialogFragment()
            dialog.show(supportFragmentManager, "nimadur")
        }

        //DataPicker Dialog
        binding.btnDatPicker.setOnClickListener {
            val datePicker = DatePickerDialog(this)
            Toast.makeText(this, "DatePicker Dialog", Toast.LENGTH_SHORT).show()
            //tanlangan bolib chiqishi uchun
            datePicker.updateDate(2005, 5, 5)
            datePicker.setOnDateSetListener { p0, p1, p2, p3 ->
                Toast.makeText(this, "$p1.$p2.$p3", Toast.LENGTH_SHORT).show()
            }
            datePicker.show()
        }

        //TimePicker Dialog
        binding.btnTimePicker.setOnClickListener {
            Toast.makeText(this, "TimePicker Dialog", Toast.LENGTH_SHORT).show()
            val timePicker = TimePickerDialog(
                this,
                object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        Toast.makeText(this@MainActivity, "$p1:$p2", Toast.LENGTH_SHORT).show()
                    }
                },
                9,
                41,
                true
            )
            timePicker.show()
        }

        //BottomSheet Dialog
        binding.btnBottomSheet.setOnClickListener {
            val button = BottomSheetDialog(this)
            button.setContentView(ItemDialogBinding.inflate(layoutInflater).root)
            button.btn_okay.setOnClickListener {
                Toast.makeText(this, "Okay", Toast.LENGTH_SHORT).show()
                button.dismiss()
            }
            button.btn_cancel.setOnClickListener {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                button.dismiss()
            }
            button.show()
            Toast.makeText(this, "BottomSheetDialog", Toast.LENGTH_SHORT).show()
        }

        //Snackbar Dialog
        binding.btnSncackbar.setOnClickListener {
            val snackbar = Snackbar.make(it, "UNDO", Snackbar.LENGTH_LONG)
            snackbar.show()
            Toast.makeText(this, "Snackbar Dialog", Toast.LENGTH_SHORT).show()
        }
    }
}