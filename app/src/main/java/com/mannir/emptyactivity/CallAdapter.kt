package com.mannir.emptyactivity

//import android.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList


class CallsAdapter// Constructor
    (// Context is not used here but may be required to
    // perform complex operations or call methods from outside
    private val context: Context
) : RecyclerView.Adapter<CallsAdapter.ViewHolder>() {

    private var callsFeed: List<Call> = ArrayList()

    fun setCallsFeed(callsFeed: List<Call>) {
        this.callsFeed = callsFeed
    }

    // Invoked by layout manager to replace the contents of the views
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val call = callsFeed[position]
        holder.showCallDetails(call)
    }

    override fun getItemCount(): Int {
        return callsFeed.size
    }

    // Invoked by layout manager to create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Attach layout for single cell
        val layout = R.layout.calls_feed_layout
        val v = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(v)
    }

    // Reference to the views for each items to display desired information
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val callerNameTextView: TextView
        private val callTimeTextView: TextView

        init {
            // Initiate view
            callerNameTextView = itemView.findViewById(R.id.callerName) as TextView
            callTimeTextView = itemView.findViewById(R.id.callTime) as TextView
        }


        fun showCallDetails(call: Call) {
            // Attach values for each item
            val callerName = call.callerName
            val callTime = call.callTime
            callerNameTextView.text = callerName
            callTimeTextView.text = callTime
        }
    }

}