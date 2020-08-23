package com.omersakalli.veripark.presentation.imkbdetail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.omersakalli.veripark.R
import com.omersakalli.veripark.data.model.IMKB.DetailRequest
import com.omersakalli.veripark.data.model.IMKB.DetailResponse
import com.omersakalli.veripark.databinding.FragmentImkbDetailBinding
import com.omersakalli.veripark.presentation.di.core.NetModule
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModel
import com.omersakalli.veripark.util.AES_Functions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ImkbDetailFragment : Fragment() {

    lateinit var binding: FragmentImkbDetailBinding
    private val handshakeViewModel: HandshakeViewModel by activityViewModels()
    private lateinit var detail: LiveData<DetailResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imkb_detail, container, false)
        val view: View = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val entries: ArrayList<Entry> = ArrayList<Entry>()
        handshakeViewModel.getHandshake().observe(viewLifecycleOwner, {
            CoroutineScope(Dispatchers.Main).launch {
                val data = NetModule().provideImkbService()
                    .requestDetail(
                        it.authorization,
                        DetailRequest(
                            AES_Functions.encrypt(
                                requireArguments().getString("detailID")!!,
                                it.aesKey,
                                it.aesIV
                            )
                        )
                    )
                    .body()!!
                data.graphicData.forEach { data ->
                    entries.add(Entry(data.day.toFloat(), data.value.toFloat()))
                }

                val vl = LineDataSet(entries, "Fiyat")
                vl.setDrawValues(true)
                vl.setDrawFilled(true)
                vl.lineWidth = 3f
                //vl.fillColor = Color.LTGRAY
                //vl.fillAlpha = Color.RED

                binding.apply {
                    lineChart.apply {
                        xAxis.labelRotationAngle = 0f
                        lineChart.data = LineData(vl)
                        axisRight.isEnabled = false
                        setTouchEnabled(true)
                        setPinchZoom(true)
                        description.text = ""
                        setNoDataText("Grafik datası alınamadı")
                        animateX(1800, Easing.EaseInExpo)
                    }
                }

                binding.apply {
                    data.apply {
                        //"% Değişim: $channge" //There is a typo in change and it isn't used in the screenshot?
                        detailCount.text = "Adet: $count"
                        detailDifference.text = "% Fark: $difference"
                        detailHighest.text = "Günlük Yüksek:$highest"
                        detailLowest.text = "Günlük Düşük: $lowest"
                        detailMaximum.text = "Tavan: $maximum"
                        detailMinimum.text = "Taban: $minimum"
                        detailOffer.text = "Satış: $offer"
                        detailBid.text = "Alış: $bid"
                        detailPrice.text = "Fiyat: $price"
                        detailVolume.text = "Hacim: $volume"

                        detailSymbol.text =
                            "Sembol: " + AES_Functions.decrypt(symbol, it.aesKey, it.aesIV)

                        if (isUp) {
                            detailChange.text = "▲"
                            detailChange.setTextColor(Color.GREEN)
                        } else if (isDown) {
                            detailChange.text = "▼"
                            detailChange.setTextColor(Color.RED)
                        } else {
                            detailChange.text = "━"
                            detailChange.setTextColor(Color.parseColor("#FFA500"))
                        }
                    }
                }
            }

        })


    }

}