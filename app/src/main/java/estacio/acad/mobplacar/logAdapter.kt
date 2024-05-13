package estacio.acad.mobplacar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import estacio.acad.mobplacar.model.Log

class logAdapter internal constructor(logs: List<Log>) :
    RecyclerView.Adapter<estacio.acad.mobplacar.logAdapter.LogViewHolder>() {
    private val logs: List<Log>

    init {
        this.logs = logs
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): estacio.acad.mobplacar.logAdapter.LogViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout, parent, false
        )
        return estacio.acad.mobplacar.logAdapter.LogViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: estacio.acad.mobplacar.logAdapter.LogViewHolder,
        position: Int
    ) {
        val log: Log = logs[position]
        holder.bind(log)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    public class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timea: TextView
        var timeb: TextView
        var tempodepartida: TextView
        var time_a_pontos: TextView
        var time_b_pontos: TextView

        init {
            timea = itemView.findViewById<TextView>(R.id.timea)
            timeb = itemView.findViewById<TextView>(R.id.timeb)
            tempodepartida = itemView.findViewById<TextView>(R.id.tempo_de_partida)
            time_a_pontos = itemView.findViewById<TextView>(R.id.time_a_pontos)
            time_b_pontos = itemView.findViewById<TextView>(R.id.time_b_pontos)
        }

        fun bind(log: Log) {
            timea.setText(log.getUser())
            timeb.setText(log.getDate())
            tempodepartida.setText(log.getPreview())
            time_a_pontos.setText(log.getSala())
            time_b_pontos.setText(log.getHorario())
        }
    }
}
