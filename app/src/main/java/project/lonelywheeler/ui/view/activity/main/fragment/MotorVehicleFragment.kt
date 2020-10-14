package project.lonelywheeler.ui.view.activity.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentMotorVehicleBinding
import project.lonelywheeler.model.domain.product.vehicle.motor.CarBodyType
import project.lonelywheeler.model.domain.product.vehicle.motor.EmissionStandard
import project.lonelywheeler.model.domain.product.vehicle.motor.FuelType
import project.lonelywheeler.ui.viewmodel.main.MotorVehicleViewModel
import project.lonelywheeler.util.binding.adapter.populateFrom

@AndroidEntryPoint
class MotorVehicleFragment : Fragment() {

    val viewModel: MotorVehicleViewModel by viewModels()
    lateinit var binding: FragmentMotorVehicleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMotorVehicleBinding.inflate(inflater, container, false)
        binding.fragmentUpdateOfferSpnrCarBodyType.populateFrom(CarBodyType::class)
        binding.fragmentUpdateOfferSpnrCarEmissionStandard.populateFrom(EmissionStandard::class)
        binding.fragmentUpdateOfferSpnrCarFuelType.populateFrom(FuelType::class)
        binding.viewModel = viewModel

        return binding.root
    }

}
