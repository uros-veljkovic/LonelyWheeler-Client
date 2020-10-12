package project.lonelywheeler.db.service

import project.lonelywheeler.db.service.api.PedestrianVehicleApi
import javax.inject.Inject

class PedestrianVehicleService
@Inject
constructor(
    private val pedestrianVehicleApi: PedestrianVehicleApi
) {
}