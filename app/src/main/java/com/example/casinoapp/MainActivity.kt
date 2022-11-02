package com.example.casinoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val slot1: TextView = findViewById(R.id.slot1)
        val slot2: TextView = findViewById(R.id.slot2)
        val slot3: TextView = findViewById(R.id.slot3)

        val textWinOrLose: TextView = findViewById(R.id.textWinOrLose)
        val balanceText: TextView = findViewById(R.id.balance)
        val stavka: TextView = findViewById(R.id.stavkaInput)

        var numSlot1 = 0
        var numSlot2 = 0
        var numSlot3 = 0
        val startButton: Button = findViewById(R.id.startBt)
        startButton.setOnClickListener {
            var balance: Int = balanceText.text.toString().toInt()
            if (balance == 0 ){
                balanceText.text = "0"

            }
            if(balanceText.text != "0" && balance >= stavka.text.toString().toInt()){
                numSlot1 = randomNum()
                numSlot2 = randomNum()
                numSlot3 = randomNum()
                slot1.text = numSlot1.toString()
                slot2.text = numSlot2.toString()
                slot3.text = numSlot3.toString()


                balanceText.text = spin(numSlot1,numSlot2,numSlot3, balanceText.text.toString(),stavka.text.toString())
                textWinOrLose.text = errorSpin(numSlot1,numSlot2,numSlot3)
            }
            else{
                textWinOrLose.text = "ПОПОЛНИТЕ БАЛАНС"
            }


        }


        val payButton: Button = findViewById(R.id.payBt)
        payButton.setOnClickListener {

            balanceText.text = payBalance(balanceText.text as String)
        }

    }

    fun randomNum(): Int{
        val rand = (0..9).random();
        return rand;
    }

    fun payBalance(bal: String): String{
        val balance = 1000;
        if(bal == "0"){
            return balance.toString()
        }
        return bal
    }

    fun spin(sl1: Int, sl2:Int, sl3: Int, balance: String, stavka: String): String{
        var comb: String = sl1.toString()+sl2.toString()+sl3.toString()
        var scoreBalance: Int = balance.toInt()
        var scoreStavka: Int = stavka.toInt()

        when(comb){
            "111","222","333","444","555","666","777","888","999" -> scoreBalance += scoreStavka * 100
            "123" -> scoreBalance += scoreStavka * 5
            "543" -> scoreBalance += scoreStavka * 6
            "248" -> scoreBalance += scoreStavka * 3
            "303" -> scoreBalance += scoreStavka * 2
            "404" -> scoreBalance += scoreStavka * 2
            else -> scoreBalance -= scoreStavka
        }

        return scoreBalance.toString()
    }

    fun errorSpin(sl1: Int, sl2:Int, sl3: Int): String{
        var comb: String = sl1.toString()+sl2.toString()+sl3.toString()
        when(comb){
            "111","222","333","444","555","666","777","888","999" -> return "JACKPOT x100"
            "123" -> return "WIN x5"
            "543" -> return "WIN x6"
            "248" -> return "WIN x3"
            "303" -> return "WIN x2"
            else -> return "YOU LOSE"
        }
    }
}