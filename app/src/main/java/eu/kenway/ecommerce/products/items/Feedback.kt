package eu.kenway.ecommerce.products.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.FragmentFeedbackBinding

class Feedback : Fragment(), RatingBar.OnRatingBarChangeListener {

    private lateinit var bindingFeedbackBinding: FragmentFeedbackBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingFeedbackBinding= FragmentFeedbackBinding.inflate(inflater,container,false)

        (activity as AppCompatActivity).supportActionBar?.title = "Rate Us"
        bindingFeedbackBinding.buttonsubmit.setOnClickListener {

            if(bindingFeedbackBinding.editTextTextMultiLine.text.toString().isNotEmpty())
            {
                Toast.makeText(requireContext(),"Feedback submitted Sucessfully",Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(requireContext(),"Kindly fill the credentials",Toast.LENGTH_LONG).show()
            }

        }
    bindingFeedbackBinding.ratingBar.onRatingBarChangeListener=this

        return bindingFeedbackBinding.root
    }

    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {

        var rating: Int = p1.toInt()
        var message=""

        when(rating)
        {
            1->  message="Sorry to hear that :("

            2->  message="You always accept Suggestions."

            3->  message="Good Enough."

            4->  message="Great! Thank You."

            5->  message="Awesome! You are the best."

            else -> message="Click something"

        }

        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()

    }


}