package project.lonelywheeler.ui.view.activity.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import project.lonelywheeler.databinding.FragmentMotorVehicleBinding
import project.lonelywheeler.model.domain.product.Condition
import project.lonelywheeler.model.domain.product.vehicle.motor.*
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
        binding.viewModel = viewModel
        populateSpinners()

        return binding.root
    }

    private fun populateSpinners() {
        binding.fragmentUpdateOfferSpnrCarBodyType.populateFrom(CarBodyType::class)
        binding.fragmentUpdateOfferSpnrCarEmissionStandard.populateFrom(EmissionStandard::class)
        binding.fragmentUpdateOfferSpnrCarFuelType.populateFrom(FuelType::class)
        binding.fragmentUpdateOfferSpnrCarDrivetrain.populateFrom(Drivetrain::class)
        binding.fragmentUpdateOfferSpnrCarGearbox.populateFrom(GearboxType::class)
        binding.fragmentUpdateOfferSpnrCarSteeringWheelType.populateFrom(SteeringWheelSide::class)
        binding.fragmentUpdateOfferProductBasicInfo.spnrProductCondition.populateFrom(Condition::class)
    }

}
