package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.security.KeyStore.TrustedCertificateEntry

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    fun evaluate(str: String): Double {
        var aa:Boolean = true
        val ops = arrayOf("(",")","+","-","*","/")
        if (str.substring(0, str.length - 1) in ops){
            aa = false
        }

        data class Data(val rest: List<Char>, val value: Double)

        return object : Any() {

            fun parse(chars: List<Char>): Double {
                return getExpression(chars.filter { it != ' ' })
                    .also { if (it.rest.isNotEmpty()) throw RuntimeException("Unexpected character: ${it.rest.first()}") }
                    .value
            }

            private fun getExpression(chars: List<Char>): Data {
                var (rest, carry) = getTerm(chars)
                while (true) {
                    when {
                        rest.firstOrNull() == '+' -> rest = getTerm(rest.drop(1)).also { carry += it.value }.rest
                        rest.firstOrNull() == '-' -> rest = getTerm(rest.drop(1)).also { carry -= it.value }.rest
                        else                      -> return Data(rest, carry)
                    }
                }
            }

            private fun getTerm(chars: List<Char>): Data {
                var (rest, carry) = getFactor(chars)
                while (true) {
                    when {
                        rest.firstOrNull() == '*' -> rest = getTerm(rest.drop(1)).also { carry *= it.value }.rest
                        rest.firstOrNull() == '/' -> rest = getTerm(rest.drop(1)).also { carry /= it.value }.rest
                        else                      -> return Data(rest, carry)
                    }
                }
            }

            private fun getFactor(chars: List<Char>): Data {
                return when (val char = chars.firstOrNull()) {
                    '+'              -> getFactor(chars.drop(1)).let { Data(it.rest, +it.value) }
                    '-'              -> getFactor(chars.drop(1)).let { Data(it.rest, -it.value) }
                    '('              -> getParenthesizedExpression(chars.drop(1))
                    in '0'..'9', ',' -> getNumber(chars)
                    else             -> throw RuntimeException("Unexpected character: $char")
                }
            }

            private fun getParenthesizedExpression(chars: List<Char>): Data {
                return getExpression(chars)
                    .also { if (it.rest.firstOrNull() != ')') throw RuntimeException("Missing closing parenthesis") }
                    .let { Data(it.rest.drop(1), it.value) }
            }

            private fun getNumber(chars: List<Char>): Data {
                val s = chars.takeWhile { it.isDigit() || it == '.' }.joinToString("")
                return Data(chars.drop(s.length), s.toDouble())
            }

        }.parse(str.toList())

    }

    val expression = "((4 * 7) / 2) - 7"

//    val result = evaluate(expression)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bt1.setOnClickListener{ binding.inputt.append("1") }
        binding.bt2.setOnClickListener{ binding.inputt.append("2") }
        binding.bt3.setOnClickListener{ binding.inputt.append("3") }
        binding.bt4.setOnClickListener{ binding.inputt.append("4") }
        binding.bt5.setOnClickListener{ binding.inputt.append("5") }
        binding.bt6.setOnClickListener{ binding.inputt.append("6") }
        binding.bt7.setOnClickListener{ binding.inputt.append("7") }
        binding.bt8.setOnClickListener{ binding.inputt.append("8") }
        binding.bt9.setOnClickListener{ binding.inputt.append("9") }
        binding.bt0.setOnClickListener{ binding.inputt.append("0") }
        binding.btDiv.setOnClickListener{ binding.inputt.append("/") }
        binding.btMul.setOnClickListener{ binding.inputt.append("*") }
        binding.btSub.setOnClickListener{ binding.inputt.append("-") }
        binding.btAdd.setOnClickListener{binding.inputt.append("+") }
        binding.btOpen.setOnClickListener { binding.inputt.append("(") }
        binding.btClose.setOnClickListener { binding.inputt.append(")") }
        binding.btClear.setOnClickListener{binding.inputt.setText("") }
        binding.btEql.setOnClickListener{
            val result = evaluate(binding.inputt.getText().toString())
            binding.inputt.setText(result.toString())


//            val input: String = binding.inputt.getText().toString()
//            val one: Int = Integer.parseInt(input.slice(listOf(0)))
////            binding.inputt.setText(one.toString())
//            val two: String = input.slice(listOf(1))
//            val three: Int = Integer.parseInt(input.slice(listOf(2)))
//            if (two=="+"){
//                binding.inputt.setText((one+three).toString())
//            }
//            if (two=="-"){
//                binding.inputt.setText((one-three).toString())
//            }
//            if (two=="X"){
//                binding.inputt.setText((one*three).toString())
//            }
//            if (two=="/"){
//                binding.inputt.setText((one/three).toString())
//            }
        }

    }
}