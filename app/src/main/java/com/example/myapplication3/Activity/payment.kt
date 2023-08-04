package com.example.myapplication3.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication3.R
import com.example.myapplication3.databinding.ActivityMaindataBinding
import com.example.myapplication3.databinding.ActivityPaymentBinding
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.PaymentApp
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import dev.shreyaspatil.easyupipayment.model.TransactionStatus


class payment : AppCompatActivity(), PaymentStatusListener
{
    private lateinit var easyUpiPayment: EasyUpiPayment
    private lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val checkout = Checkout()
//        checkout.setKeyID("rzp_test_pOPHazql7o4Mbj")
//        Checkout.preload(applicationContext);



//            // Get the amount to be paid from your input field (e.g., editTextAmount)
//            val price = 10
//
//
//
//            val obj = JSONObject()
//            try {
//                obj.put("name", "tasmin")
//                obj.put("description", "Test Payment")
//                obj.put("currency", "INR")
//                obj.put("amount", price)
//                val robj = JSONObject()
//                robj.put("enabled", true)
//                robj.put("max_count", 4)
//                robj.put("retry", robj)
//                checkout.open(this@payment, obj)
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//
//
        val transactionId = "TID" + System.currentTimeMillis()
        binding.fieldTransactionId.setText(transactionId)
        binding.fieldTransactionRefId.setText(transactionId)

        // Setup click listener for Pay button
        binding.buttonPay.setOnClickListener { pay() }


    }

    private fun pay() {
        val payeeVpa = binding.fieldVpa.text.toString()
        val payeeName = binding.fieldName.text.toString()
        val transactionId = binding.fieldTransactionId.text.toString()
        val transactionRefId = binding.fieldTransactionRefId.text.toString()
        val payeeMerchantCode = binding.fieldPayeeMerchantCode.text.toString()
        val description = binding.fieldDescription.text.toString()
        val amount = binding.fieldAmount.text.toString()
        val paymentAppChoice = binding.radioAppChoice

        val paymentApp = when (paymentAppChoice.checkedRadioButtonId) {
            R.id.app_default -> PaymentApp.ALL
            R.id.app_amazonpay -> PaymentApp.AMAZON_PAY
            R.id.app_bhim_upi -> PaymentApp.BHIM_UPI
            R.id.app_google_pay -> PaymentApp.GOOGLE_PAY
            R.id.app_phonepe -> PaymentApp.PHONE_PE
            R.id.app_paytm -> PaymentApp.PAYTM
            else -> throw IllegalStateException("Unexpected value: " + paymentAppChoice.id)
        }

        try {
            // START PAYMENT INITIALIZATION
            easyUpiPayment = EasyUpiPayment(this) {
                this.paymentApp = paymentApp
                this.payeeVpa = payeeVpa
                this.payeeName = payeeName
                this.transactionId = transactionId
                this.transactionRefId = transactionRefId
                this.payeeMerchantCode = payeeMerchantCode
                this.description = description
                this.amount = amount
            }
            // END INITIALIZATION

            // Register Listener for Events
            easyUpiPayment.setPaymentStatusListener(this)

            // Start payment / transaction
            easyUpiPayment.startPayment()
        } catch (e: Exception) {
            e.printStackTrace()
           Toast.makeText(applicationContext,"Error: ${e.message}",Toast.LENGTH_LONG).show()
        }
    }


    override fun onTransactionCancelled() {
        Toast.makeText(applicationContext,"Cancelled by user",Toast.LENGTH_LONG).show()
        binding.imageView.setImageResource(androidx.core.R.drawable.ic_call_answer)
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Log.d("TransactionDetails", transactionDetails.toString())
        binding.textViewStatus.text = transactionDetails.toString()

        when (transactionDetails.transactionStatus) {
            TransactionStatus.SUCCESS -> onTransactionSuccess()
            TransactionStatus.FAILURE -> onTransactionFailed()
            TransactionStatus.SUBMITTED -> onTransactionSubmitted()
        }
    }

    private fun onTransactionSubmitted() {
        toast("submitt")
        binding.imageView.setImageResource(R.drawable.baseline_save_24)
    }

    private fun onTransactionFailed() {
        toast("Failed")
        binding.imageView.setImageResource(R.drawable.baseline_backspace_24)
    }

    private fun onTransactionSuccess() {
        toast("success")
        binding.imageView.setImageResource(R.drawable.baseline_check_24)
    }
    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Implement the onPaymentSuccess and onPaymentError methods from PaymentResultListener
//    override fun onPaymentSuccess(razorpayPaymentID: String?) {
//        // Payment successful, handle the success scenario here
//        Log.d("ONSUCCESS", "onPaymentSuccess: " )
//    }
//
//    override fun onPaymentError(errorCode: Int, response: String?) {
//        // Payment failed or was canceled, handle the failure scenario here
//        Log.d("ONERROR", "onPaymentError: ")
//    }
////    override fun onDestroy() {
////        super.onDestroy()
////        // Unregister the broadcast receiver to prevent the leak
////        checkout.onDestroy()
//    }

}